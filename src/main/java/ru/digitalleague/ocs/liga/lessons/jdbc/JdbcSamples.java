package ru.digitalleague.ocs.liga.lessons.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class JdbcSamples {
    public static final String ORA_URL_SYNTAX = "jdbc:oracle:thin:USER/PASSWORD@HOST:PORT/SERVICE_NAME";
    public static final String PG_URL_SYNTAX = "jdbc:postgresql://HOST:PORT/DB?user=USER&password=PASSWORD";

    public static final String ORA_URL = "jdbc:oracle:thin:USER/PASSWORD@HOST:PORT/SERVICE_NAME";
    public static final String PG_URL = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres123456";

    public static void main(String[] args) {
        simpleOracle();
        //simplePostgres();

        //testPrepareEventsFile();
    }

    private static void simpleOracle() {
        try (Connection conn = DriverManager.getConnection(ORA_URL)) {
            System.out.println("=====================");
            System.out.println("USING ORACLE");
            System.out.println("=====================");

            select(conn);
            //selectWithIndex(conn);
            //selectWithPreparedStatement(conn);
            //update(conn);
            //insert(conn);
            //insertBatch(conn);
            //сallableStatement(conn);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void simplePostgres() {
        try (Connection conn = DriverManager.getConnection(PG_URL)) {
            System.out.println("=====================");
            System.out.println("USING POSTGRES");
            System.out.println("=====================");
            select(conn);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    private static void select(Connection conn) throws SQLException {
        String sql = "select * from AUTHOR";
        //String sql = "select id, first_name, last_name, middle_name from AUTHOR";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                System.out.println(id + " | " + firstName + " | " + lastName + " | " + middleName);
            }
        }
    }

    private static void selectWithIndex(Connection conn) throws SQLException {
        String sql = "select * from AUTHOR";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                String id = rs.getString(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String middleName = rs.getString(4);
                System.out.println(id + " | " + firstName + " | " + lastName + " | " + middleName);
            }
        }
    }

    private static void selectWithPreparedStatement(Connection conn) throws SQLException {
        String sql = "select * from AUTHOR where last_name = ?";
        //String sql = "select * from AUTHOR where last_name = '" + name + "'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Кнут");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String middleName = rs.getString(4);
                    System.out.println(id + " | " + firstName + " | " + lastName + " | " + middleName);
                }
            }
        }
    }

    private static void update(Connection conn) throws SQLException {
        String sql = "update AUTHOR set middle_name = ? where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "ТЕСТОВОЕ");
            //ps.setString(1, null); //откат
            ps.setString(2, "8afbb0a1-7b33-4643-b837-e41e4d5b1acc");
            int affected = ps.executeUpdate();
            System.out.println("affected rows: " + affected);
        }
    }

    private static void insert(Connection conn) throws SQLException {
        long timer = System.currentTimeMillis();
        String sql = "insert into events (type, result, transaction_id, event_date, event_datetime) values (?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("events.txt"), "utf-8"));
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            String str;
            int lineCount = 0;
            while((str = br.readLine()) != null) {
                lineCount++;
                String[] parts = str.split(";");
                ps.setString(1, parts[0]);
                ps.setString(2, parts[1]);
                ps.setString(3, parts[2]);
                ps.setDate(4, null);
                ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                int affected = ps.executeUpdate();
                ps.clearParameters();
                if (lineCount % 100 == 0) {
                    System.out.println("processed " + lineCount);
                    conn.commit();
                }
                if (lineCount == 1000) break;
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            conn.rollback();
        }
        System.out.println("total time: " + (System.currentTimeMillis() - timer));
    }

    private static void insertBatch(Connection conn) throws SQLException {
        long timer = System.currentTimeMillis();
        String sql = "insert into events (type, result, transaction_id, event_date, event_datetime) values (?, ?, ?, ?, ?)";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("events.txt"), "utf-8"));
             PreparedStatement ps = conn.prepareStatement(sql)) {
            //conn.setAutoCommit(false);
            String str;
            int lineCount = 0;
            while((str = br.readLine()) != null) {
                lineCount++;
                String[] parts = str.split(";");
                ps.setString(1, parts[0]);
                ps.setString(2, parts[1]);
                ps.setString(3, parts[2]);
                ps.setDate(4, null);
                ps.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                ps.addBatch();
                if (lineCount % 100 == 0) {
                    System.out.println("processed " + lineCount);
                    int[] res = ps.executeBatch();
                    //conn.commit();
                    //System.out.println(Arrays.toString(res));
                    ps.clearBatch();
                }
                if (lineCount == 1000) break;
            }
            int[] res = ps.executeBatch();
            //System.out.println(Arrays.toString(res));
            //conn.commit();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        System.out.println("total time: " + (System.currentTimeMillis() - timer));
    }

    private static void сallableStatement(Connection conn) throws SQLException {
        String sql = "call sample_procedure(?)";
        //String sql = "? = call sample_procedure(?, ?, ?)";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, "param");
            //stmt.registerOutParameter(2, Types.VARCHAR);            
            boolean result = stmt.execute();
            System.out.println("result: " + result);
            //String resultCode = stmt.getString(1);
        }
    }

    private static void testPrepareEventsFile() {
        List<String> results = Arrays.asList("OK", "ERROR");
        List<String> types = Arrays.asList("BALANCE", "ADD_AUTHOR", "ADD_BOOK", "GIFT", "ADD_EDITION", "UPDATE_AUTHOR");
        Random resultsRandom = new Random();
        Random typesRandom = new Random();
        try (OutputStream os = new FileOutputStream("events.txt")) {
            for (int i = 0; i < 10000; i++) {
                String type = types.get(typesRandom.nextInt(types.size()));
                String result = results.get(resultsRandom.nextInt(results.size()));
                String trxId = UUID.randomUUID().toString();
                os.write((type + ";" + result + ";" + trxId).getBytes());
                os.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
