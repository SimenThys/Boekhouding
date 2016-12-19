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
    wnr integer,
    datum date,
    bedrag integer,
    status integer,
    omschrijving varchar2(100),
    primary key (onr),
    foreign key(wnr) references Werknemer(wnr),
    foreign key(knr) references Kredieten(knr));

insert into Werknemer values (1,1, 'man',2);
insert into Werknemer values (2,1, 'boek',1);
insert into Werknemer values (3,1, 'werk',0);
insert into Werknemer values (4,1, 'werk',0);
insert into Werknemer values (5,1, 'werk',0);

insert into Kredieten values (1,100,0,1);
insert into Kredieten values (2,9900,0,1);
insert into Kredieten values (3,500,1,1);
insert into Kredieten values (4,10,1,1);

insert into Onkosten values (1,1,3,'22/03/2016',10,0,'eerste onkost');
insert into Onkosten values (2,1,3,'23/03/2016',20,1,'tweede onkost');
insert into Onkosten values (3,1,3,'24/03/2016',30,2,'derde onkost');

insert into Onkosten values (4,2,2,'25/03/2016',10,0,'eerste onkost boek');
insert into Onkosten values (5,2,2,'26/03/2016',20,1,'tweede onkost boek');
insert into Onkosten values (6,2,2,'27/03/2016',30,2,'derde onkost boek');
