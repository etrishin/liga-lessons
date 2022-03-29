create table AUTHOR (
  id          VARCHAR2(36) not null,
  first_name  VARCHAR2(255), --имя
  last_name   VARCHAR2(255), --фамилия
  middle_name VARCHAR2(255)  --отчество
);
alter table AUTHOR add constraint AUTHOR_PK primary key (id);

create table BOOK (
  id          VARCHAR2(36) not null,
  title       VARCHAR2(255), --название
  year        number(4)      --год выпуска
);
alter table BOOK add constraint BOOK_PK primary key (id);

create table AUTHOR_BOOK_XREF (
  author_id VARCHAR2(36) not null,
  book_id   VARCHAR2(36) not null
);
alter table AUTHOR_BOOK_XREF add constraint AUTHOR_BOOK_XREF_PK primary key (author_id, book_id);

--drop table AUTHOR_BOOK_XREF cascade constraints;
--drop table BOOK cascade constraints;
--drop table AUTHOR cascade constraints;

insert into AUTHOR (id, first_name, last_name, middle_name) values ('cee99b66-de19-41a2-a3e1-d51d5b6cc50d', N'Дональд', N'Кнут', N'Эрвин');
insert into AUTHOR (id, first_name, last_name, middle_name) values ('e28d79cd-42b0-4c5d-a88a-652c40f7dedd', N'Рональд', N'Грэм', N'Льюис');
insert into AUTHOR (id, first_name, last_name, middle_name) values ('8afbb0a1-7b33-4643-b837-e41e4d5b1acc', N'Орен', N'Паташник', null);
insert into AUTHOR (id, first_name, last_name, middle_name) values ('77c61f27-c407-467b-b02d-c2134fc2a484', N'Джон', N'Толкин', N'Рональд Руэл');

insert into BOOK (id, title, year) values ('68046d8e-e318-4fad-a9b6-da277bbbf417', N'Искусство программирования, том 1', 1968);
insert into BOOK (id, title, year) values ('786cbc8a-e569-4590-879b-5268b18848ad', N'Искусство программирования, том 2', 1969);
insert into BOOK (id, title, year) values ('3b73dde7-c11b-4d1a-87f0-105de03d28ee', N'Искусство программирования, том 3', 1973);
insert into BOOK (id, title, year) values ('559f2729-ae7b-4940-b054-f585d8605a3e', N'Конкретная математика', 1994);
insert into BOOK (id, title, year) values ('43baeac6-40a0-4195-8ba2-931ca34cf4b3', N'Хоббит, или Туда и обратно', 1937);
insert into BOOK (id, title, year) values ('4d7f34cc-44be-4846-a844-6cbcff66354b', N'Властелин колец. Братство Кольца', 1954);
insert into BOOK (id, title, year) values ('8961615b-f230-4c0d-9026-dfacc7efd316', N'Властелин колец. Две крепости', 1954);
insert into BOOK (id, title, year) values ('2fa19e0a-d24d-4f26-8542-1aad8d40aac3', N'Властелин колец. Возвращение короля', 1955);

insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('cee99b66-de19-41a2-a3e1-d51d5b6cc50d', '68046d8e-e318-4fad-a9b6-da277bbbf417');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('cee99b66-de19-41a2-a3e1-d51d5b6cc50d', '786cbc8a-e569-4590-879b-5268b18848ad');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('cee99b66-de19-41a2-a3e1-d51d5b6cc50d', '3b73dde7-c11b-4d1a-87f0-105de03d28ee');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('cee99b66-de19-41a2-a3e1-d51d5b6cc50d', '559f2729-ae7b-4940-b054-f585d8605a3e');

insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('e28d79cd-42b0-4c5d-a88a-652c40f7dedd', '559f2729-ae7b-4940-b054-f585d8605a3e');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('8afbb0a1-7b33-4643-b837-e41e4d5b1acc', '559f2729-ae7b-4940-b054-f585d8605a3e');

insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('77c61f27-c407-467b-b02d-c2134fc2a484', '43baeac6-40a0-4195-8ba2-931ca34cf4b3');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('77c61f27-c407-467b-b02d-c2134fc2a484', '4d7f34cc-44be-4846-a844-6cbcff66354b');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('77c61f27-c407-467b-b02d-c2134fc2a484', '8961615b-f230-4c0d-9026-dfacc7efd316');
insert into AUTHOR_BOOK_XREF (author_id, book_id) values ('77c61f27-c407-467b-b02d-c2134fc2a484', '2fa19e0a-d24d-4f26-8542-1aad8d40aac3');

--
create table EVENTS (
  type           VARCHAR2(15) not null,
  result         VARCHAR2(5)  not null,
  transaction_id VARCHAR2(36) null,
  event_date     DATE         null,
  event_datetime TIMESTAMP    null
);
--drop table EVENTS cascade constraints;
