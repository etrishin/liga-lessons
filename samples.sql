--Извлечение данных
select * from AUTHOR
select last_name, first_name from AUTHOR
select * from BOOK
select * from AUTHOR_BOOK_XREF
--Сортировка
select * from AUTHOR order by last_name
select * from AUTHOR order by last_name desc, middle_name asc
select * from AUTHOR order by 1, 2
select * from AUTHOR order by 1 desc, 2
--Фильтрация
select * from AUTHOR where last_name = N'Кнут'
select * from AUTHOR where last_name = N'Кнут' AND first_name = N'Дональд'
select * from AUTHOR where last_name = N'Кнут' OR last_name = N'Грэм'
select * from AUTHOR where last_name in (N'Кнут', N'Грэм')
select * from AUTHOR where last_name not in (N'Кнут', N'Грэм')
--Функции
select last_name, UPPER(last_name) from AUTHOR
select last_name, LENGTH(last_name) from AUTHOR
select last_name, sysdate from AUTHOR
--select * from DUAL
select sysdate from DUAL
select sysdate, TRUNC(sysdate) from DUAL
select sysdate, to_date('2022.03.29 23:59:59', 'yyyy.MM.dd hh24:mi:ss') from DUAL

--Группировка
select * from events
select type, count(*) from events group by type
select type, count(*) from events group by type having count(*) > 500
select type, count(*) from events where type != 'BALANCE' group by type having count(*) > 500 order by 2
--select count(*) from events

--Подзапросы
select * from AUTHOR where last_name = N'Кнут'
select id from AUTHOR where last_name = N'Кнут'
select * from AUTHOR_BOOK_XREF where author_id in (select id from AUTHOR where last_name = N'Кнут')
select book_id from AUTHOR_BOOK_XREF where author_id in (select id from AUTHOR where last_name = N'Кнут')
select * from book where id in (select book_id from AUTHOR_BOOK_XREF where author_id in (select id from AUTHOR where last_name = N'Кнут'))

--Итоговые вычистелния (“Summarizing Data”)
select sum(counter), avg(counter) from (select type, count(*) counter from events group by type)

--Объединение таблиц (в т.ч. join)
select * from AUTHOR a, AUTHOR_BOOK_XREF b where a.id = b.author_id -- 2 таблицы из 3
select * from AUTHOR a, AUTHOR_BOOK_XREF ab, BOOK b where a.id = ab.author_id AND ab.book_id = b.id -- все таблицы
--select count(*) from AUTHOR--4
--select count(*) from AUTHOR_BOOK_XREF--10
--select count(*) from BOOK--8
select * from AUTHOR a, AUTHOR_BOOK_XREF b --декартово произведение
select * from AUTHOR a, AUTHOR_BOOK_XREF ab, BOOK b --декартово произведение

--Комбинированные запросы
select * from AUTHOR where last_name = N'Кнут'
union
--union all
select * from AUTHOR where last_name = N'Кнут'
union
--union all
select * from AUTHOR where last_name = N'Кнут'
union
--union all
select * from AUTHOR where last_name = N'Грэм'

--Добавление данных
insert into author(id, first_name, last_name, middle_name)
--values ('123', N'Иван', N'Иванов', N'Иванович')
values ('77c61f27-c407-467b-b02d-c2134fc2a484', N'Иван', N'Иванов', N'Иванович')

--Обновление данных
update author
set first_name = N'Пётр', middle_name = N'Пётрович'
where id = '123'

--Удаление данных
delete author where id = '123'
--delete events
--truncate table events

--Транзакции
commit
rollback

--Создание / обновление / удаление таблиц
alter table book add (
    cost      integer   null,
    available number(1) null
)
alter table book drop (
    cost,
    available
)

--Представления (“View”)
--Хранимые процедуры
--Курсоры
--Ограничения (первичные ключи, внешние ключи, уникальность, индексы, триггеры, безопасность)

