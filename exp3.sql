USE library;

insert into branch values(1,'North', 'Andheri','ABC');
insert into branch values(2,'South', 'Churchgate','MNO');
insert into branch values(3,'Central', 'Ghatkopar','XYZ');

insert into customer values(1,'Dhruv', '98451235','Life-member','2012-01-01');
insert into customer values(2,'Kanksha', '845218648','Yearly','2017-01-01');
insert into customer values(3,'Pranav', '87452369','Monthly','2017-12-01');

insert into books values(1,'The Lost Hero','Rick Riordian', 'Y', 350, 1, 1,1);
insert into books values(2,'The Alchemist','Paulo Coelho', 'Y', 250, 2, 3,1);
insert into books values(3,'Eragon','Christopher Paolini', 'N', 300, 2, 1,2);
insert into books values(4,'Uncle Fred in the Springtime','P. G. Wodehouse', 'Y', 200, 3, 2,2);
insert into books values(5,'The Count of Monte Cristo','Alexander Dumas', 'Y', 325, 2, 2,3);

insert into employee values(1,'ABC',10000, 'Andheri', 123456789, 1);
insert into employee values(2,'DEF',15000, 'Parle', 458123698, 1);
insert into employee values(3,'GHI',20000, 'Churchgate', 412853482, 2);
insert into employee values(4,'MNO',25000, 'Colaba', 845213597, 2);
insert into employee values(5,'XYZ',30000, 'Ghatkopar', 923145762, 3);
/*
insert into books values(1,'The Lost Hero','Rick Riordian', 'Y', 350, 10);
insert into books values(2,'The Alchemist','Paulo Coelho', 'Y', 250, 20);
insert into books values(3,'Eragon','Christopher Paolini', 'N', 300, 3);
insert into books values(4,'Uncle Fred in the Springtime','P. G. Wodehouse', 'Y', 200, 12);
insert into books values(5,'The Count of Monte Cristo','Alexander Dumas', 'Y', 325, 17);

insert into employee values(1,'ABC',10000, 'Andheri', 123456789, 1);
insert into employee values(2,'DEF',15000, 'Parle', 458123698, 1);
insert into employee values(3,'GHI',20000, 'Churchgate', 412853482, 2);
insert into employee values(4,'MNO',25000, 'Colaba', 845213597, 2);
insert into employee values(5,'JKL',22000, 'Colaba', 835213597, 2);
insert into employee values(6,'XYZ',30000, 'Ghatkopar', 923145762, 3);
*/
select * from books;
select * from employee;
select * from branch;
select * from customer;
