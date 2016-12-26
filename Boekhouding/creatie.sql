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
    paswd varchar(50),
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
    (wnr integer check ((select wnr from Werknemer w where w.typ = 3 OR w.typ = 2) = wnr),
    knr integer,
    primary key(wnr,knr),
    foreign key(wnr) references Werknemer(wnr),
    foreign key(knr) references Kredieten(knr));

create table Onkosten
    (onr integer,
    knr integer,
    wnr integer,
    datum date,
    bedrag integer,
    status integer,
    omschrijving varchar(100),
    primary key (onr),
    foreign key(wnr) references Werknemer(wnr),
    foreign key(knr) references Kredieten(knr));

insert into Werknemer values (1,1, 'man',3);
insert into Werknemer values (2,1, 'boek',1);
insert into Werknemer values (3,6, 'werk',0);
insert into Werknemer values (4,6, 'werk',0);
insert into Werknemer values (5,6, 'werk',0);
insert into Werknemer values (6,1, 'man',2);

insert into Kredieten values (1,100,0,1);
insert into Kredieten values (2,9900,0,1);
insert into Kredieten values (3,500,1,1);
insert into Kredieten values (4,10,1,1);
insert into Kredieten values (5,1,1,1);
insert into Kredieten values (6,1,0,1);

insert into Rekening values (1,1);
insert into Rekening values (1,2);
insert into Rekening values (1,3);
insert into Rekening values (1,4);
insert into Rekening values (1,5);
insert into Rekening values (1,6);

insert into Onkosten values (1,1,3,'2016-03-22',10,0,'eerste onkost');
insert into Onkosten values (2,1,3,'2016-03-23',20,1,'tweede onkost');
insert into Onkosten values (3,1,3,'2016-03-24',30,2,'derde onkost');

insert into Onkosten values (4,2,2,'2016-03-25',10,0,'eerste onkost boek');
insert into Onkosten values (5,2,2,'2016-03-26',20,1,'tweede onkost boek');
insert into Onkosten values (6,2,2,'2016-03-27',30,2,'derde onkost boek');