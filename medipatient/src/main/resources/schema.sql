/* Setting up medipatient DB */
drop database if exists medipatient;
create database medipatient;
use medipatient;


create table patient(
ID bigint PRIMARY KEY AUTO_INCREMENT,
PRENOM varchar(50),
NOM varchar(50),
DATENAISS date,
GENRE smallint default 1,
ADRESSEPOSTALE varchar(250),
NUMTEL varchar(20)
);
