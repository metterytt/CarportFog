create table customersTest like carport.customers;
insert into customersTest (username, password, firstname, lastname, phonenumber) values ('lars@lars.dk', 'larsPW', 'lars', 'larsen', '12345678');
insert into customersTest (username, password, firstname, lastname, phonenumber) values ('bente@bente.dk', 'bentePW', 'bente', 'bentsen', '87654321');
insert into customersTest (username, password, firstname, lastname, phonenumber) values ('anna@anna.dk', 'annaPW', 'anna', 'jensen', '23456789');
create table customers like customersTest;

create table employeesTest like carport.employees;
insert into employeesTest (username, password, role) values ('testemp@testemp.dk', 'testPW', 'salesman');
insert into employeesTest (username, password, role) values ('testIT@testIT.dk', 'testITPW', 'IT');
create table employees like employeesTest;