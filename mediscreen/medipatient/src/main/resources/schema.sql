/* Setting up medipatient DB */
drop database if exists medipatient;
create database medipatient;
use medipatient;


create table patient(
ID bigint PRIMARY KEY AUTO_INCREMENT,
FIRSTNAME varchar(50),
LASTNAME varchar(50),
PHONE varchar(20),
SEX varchar(10),
DOB DATE,
ADDRESS varchar(250)
);

 commit;