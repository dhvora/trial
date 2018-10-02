DROP DATABASE library;


CREATE database library;

USE library;

CREATE TABLE books (
  b_id INT,
  title VARCHAR(50),
  author VARCHAR (25),
  available VARCHAR(1),
  price INT,
  copies INT,
  c_id INT,
  branch_id INT
);

CREATE TABLE employee (
  e_id INT,
  name VARCHAR(15),
  salary INT,
  address VARCHAR(100),
  contact INT,
  branch_id INT
);

CREATE TABLE branch (
  branch_id INT PRIMARY KEY,
  name VARCHAR(15),
  address VARCHAR (15),
  librarian VARCHAR(20)
);

CREATE TABLE customer (
  c_id INT PRIMARY KEY,
  name VARCHAR(15),
  contact INT,
  type VARCHAR(20),
  doj date
);

alter table  books
ADD foreign key (c_id) references customer(c_id),
ADD foreign key (branch_id) references branch(branch_id);

alter table  employee
ADD foreign key (branch_id) references branch(branch_id);

select * from customer;