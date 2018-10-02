USE library;

ALTER table employee partition by RANGE(salary)
(
	PARTITION p1 VALUES LESS THAN (15000),
    PARTITION p2 VALUES LESS THAN MAXVALUE
);

select * from  employee partition(p1);
select * from  employee partition(p2);

ALTER table books partition by LIST(price)
(
	PARTITION p1 VALUES IN (200,250),
    PARTITION p2 VALUES IN(300, 350),
    PARTITION p3 VALUES IN(325)
);

select * from books partition (p1);
select * from books partition (p2);
select * from books partition (p3);

ALTER table books partition by KEY(b_id)

PARTITIONS 2;

select * from books partition(p0);
select * from books partition(p1);

ALTER table branch partition by HASH(branch_id)
PARTITIONS 2;
select * from branch partition(p0);
select * from branch partition(p1);

ALTER table books
PARTITION by range (copies)(
	PARTITION p0 VALUES LESS THAN (13),
    PARTITION p1 VALUES LESS THAN (MAXVALUE)
);
select * from books partition(p0);
select * from books partition(p1);

create view people
as select C.name, b_id, branch_id
from customer as C, books, branch;
select * from people;