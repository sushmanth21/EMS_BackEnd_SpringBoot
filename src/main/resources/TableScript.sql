drop database if exists spring_boot;
create database spring_boot;
use spring_boot;

create table employee(
    id int AUTO_INCREMENT primary key,
    name varchar(20),
    gender varchar(10),
    age int,
    state varchar(20)
);

insert into employee values (1, 'Gambhir', 'Male', 23, 'Delhi');
insert into employee values (2, 'Pandey', 'Male', 21, 'Maharashtra');
insert into employee values (3, 'Thakur', 'Male', 29, 'Gujarat');
insert into employee values (4, 'Rinku', 'Male', 22, 'Kolkata');
insert into employee values (5, 'Dusitha', 'FeMale', 24, 'Karnataka');

select * from employee;

commit;
