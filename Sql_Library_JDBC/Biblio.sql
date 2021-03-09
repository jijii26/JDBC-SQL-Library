use master
go
drop database biblio
go
create database biblio
go
use biblio
go

create table Edition (
no_edition int identity primary key,
ordre int not null,
exemplaire int not null)
go

create table livre
(id_livre int identity primary key,
 no_isbn  int unique not null,
 no_edition int  not null,
 titre    varchar(25) not null,
 prix     money not null,
 no_livre int unique, no_librairie int )
 go

 alter table livre
 add constraint fk_no_edition foreign key(no_edition) references Edition(no_edition)
 go

 insert into Edition values
 (1,2),
 (2,1),
 (3,1),
 (1,2),
 (2,3)
 go

insert into livre values
(1234567891,1,'un Livre',35,1,1),
(1234567892,2,'Les indésirables',30,2,1),
(1234567893,3,'Aurienthé Obget',45,3,2),
(1234567894,1,'Gean-Chochua halal',35,4,2),
(1234567895,3,'Illétré',35,5,1)
go


create table auteur
(no_auteur   int identity(1000,10) primary key,
 nom_auteur  varchar(30) not null,
 cote        int not null check (cote in (1,2)))
 go

 insert into auteur values 
 ('Benameurlaine',1),
 ('Tagmouti',2),
 ('Ghali',2)
 go


  create table AuteurLivre
 (id_livre  int not null,
  no_auteur int not null,
  constraint pk_AuteurLivre primary key (id_livre,no_auteur))
  go

 insert into AuteurLivre values
(1,1020),
(2,1010),
(3,1000),
(4,1010),(4,1020),
(5,1000),(5,1010),(5,1020)
go

create table librairie(
no_librairie int primary key,
nom varchar(50),
adresse varchar(50))
go

insert into librairie values
(1, 'Librairie vraiment Cher', '9595 TrèLoin'),
(2, 'SansNom', '1010 UnChemin')
go

 -----------fonctionalités---------------------

  --fonctionalité 1 
select l.titre, e.exemplaire 
from livre l inner join edition e on l.no_edition = e.no_edition 
where e.no_edition = 2

 --fonctionalité 2
select e.no_edition as 'numero edition',e.exemplaire as 'nombre d''exemplaire',e.ordre as 'l''ordre'
from Edition e inner join livre l on l.no_edition = e.no_edition
where l.id_livre = 2
go

--fonctionnalité 3 
select l.titre,e.exemplaire
from Edition e inner join livre l on e.no_edition=l.no_edition
where e.no_edition = 1
go


--fonctionalité 4
select l.no_isbn, l.no_edition, l.titre, l.prix,l.no_livre  
from livre l inner join librairie li on li.no_librairie = l.no_librairie 
where li.no_librairie = 1
go









