/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  student
 * Created: 22-nov-2016
 */

drop table Onkosten;
drop table Rekening;
drop table Kredieten;
drop table Werknemer;


create table Werknemer
    (wnr integer,
    bnr integer,
    paswd varchar2(50),
    typ integer,
    primary key(wnr));

create table Kredieten
    (knr integer,
    saldo integer,
    typ integer,
    manager integer,
    primary key (knr),
    foreign key(manager) references Werknemer(wnr));

create Table Rekening
    (wnr integer,
    knr integer,
    primary key(wnr,knr),
    foreign key(wnr) references Werknemer(wnr),
    foreign key(knr) references Kredieten(knr));

create table Onkosten
    (onr integer,
    knr integer,
    datum date,
    bedrag integer,
    status integer,
    primary key (onr),
    foreign key(knr) references Kredieten(knr));

insert into Werknemer values (1,1, 'man',2);
insert into Werknemer values (2,1, 'boek',1);
insert into Werknemer values (3,1, 'werk',0);
insert into Werknemer values (4,1, 'werk',0);
insert into Werknemer values (5,1, 'werk',0);