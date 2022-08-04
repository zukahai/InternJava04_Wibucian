CREATE FUNCTION [dbo].getUniqueIdWithPrefix1(
    @id int,
    @prefix varchar(10)
)
RETURNS nvarchar(15) WITH SCHEMABINDING
AS
BEGIN
RETURN (@prefix + RIGHT ('00000' + CAST (@id AS VARCHAR (5)), 5))
END;
go

use WIBUCIAN
go
---
CREATE TABLE Invoice
(
    id           int IDENTITY(1,1),
    idInvoice AS [dbo].getUniqueIdWithPrefix1(id, 'Invoice') PERSISTED primary key,
    idEmployee   nvarchar (15),
    idGroupTable nvarchar (15),
    customerName nvarchar (50) not null,
    dateTime     datetime
)
-------------------------------
create table DetailInvoice
(
    id        int IDENTITY(1,1),
    idDetailInvoice AS [dbo].getUniqueIdWithPrefix1(id, 'DTInvoice') PERSISTED primary key,
    idInvoice nvarchar (15),
    idProduct nvarchar (15),
    quantity  int
)
    go
------------------------------
create table Account
(
    id         int IDENTITY(1,1),
    idAccount AS [dbo].getUniqueIdWithPrefix1(id, 'Account') PERSISTED primary key,
    idEmployee nvarchar (15),
    password   nvarchar(50),
    role       int
)
    go
------------------------------
create table Employee
(
    id                 int IDENTITY(1,1),
    idEmployee AS [dbo].getUniqueIdWithPrefix1(id, 'Employee') PERSISTED primary key,
    name               nvarchar(100) not null,
    address            nvarchar(100) not null,
    srcEmployee        nvarchar (100) not null,
    email              nvarchar(100) not null,
    phoneNumber        nvarchar(20) not null,
    gender             nvarchar(20) not null,
    birthDay           datetime,
    maritalStatus      nvarchar(50) not null,
    CoefficientsSalary float
)
--------------------------------
create table Shift
(
    id               int IDENTITY(1,1),
    idShift AS [dbo].getUniqueIdWithPrefix1(id, 'Shift') PERSISTED primary key,
    idEmployee       nvarchar (15),
    idEmployeeChange nvarchar (15),
    timeStart        datetime,
    timeEnd          datetime
)
---------------------------------
create table Product
(
    id            int IDENTITY(1,1),
    idProduct AS [dbo].getUniqueIdWithPrefix1(id, 'Product') PERSISTED primary key,
    idSale        nvarchar (15),
    idProductType nvarchar (15),
    describe      nvarchar( max),
    srcImage      nvarchar(100) not null,
    productName   nvarchar (100) not null,
)
----------------------------------
create table TypeProduct
(
    id          int IDENTITY(1,1),
    idTypeProduct AS [dbo].getUniqueIdWithPrefix1(id, 'TypeProduct') PERSISTED primary key,
    productName nvarchar (100) not null,
)
-----------------------------------
create table DetailProduct
(
    id           int IDENTITY(1,1),
    idDetailProduct AS [dbo].getUniqueIdWithPrefix1(id, 'DTProduct') PERSISTED primary key,
    idProduct    nvarchar (15),
    idIngredient nvarchar (15),
    quantity     int
)
------------------------------------
create table Sale
(
    id    int IDENTITY(1,1),
    idSale AS [dbo].getUniqueIdWithPrefix1(id, 'Sale') PERSISTED primary key,
    pcent nvarchar(50) not null,
    price float
)
-------------------------------------
create table ImportGoods
(
    id         int IDENTITY(1,1),
    idImportGoods AS [dbo].getUniqueIdWithPrefix1(id, 'ImGoods') PERSISTED primary key,
    idEmployee nvarchar (15),
    timeImport datetime
)
----------------------------------------
create table DetailImportGoods
(
    id            int IDENTITY(1,1),
    idDetailImportGoods AS [dbo].getUniqueIdWithPrefix1(id, 'DTimGoods') PERSISTED primary key,
    idIngredient  nvarchar (15),
    idImportGoods nvarchar (15),
    quantity      int
)
--------------------------------------
create table Ingredient
(
    id               int IDENTITY(1,1),
    idIngredient AS [dbo].getUniqueIdWithPrefix1(id, 'Ingredient') PERSISTED primary key,
    ingredientName   nvarchar(100) not null,
    expiryIngredient datetime,
    price            float not null,
    origin           nvarchar (100) not null,
    unit             nvarchar (100) not null,
)
--------------------------------------
create table Tablecf
(
    id          int IDENTITY(1,1),
    idTablecf AS [dbo].getUniqueIdWithPrefix1(id, 'Tablecf') PERSISTED primary key,
    idTypeTable nvarchar (15),
    describe    nvarchar( max),
    capacity    int
)
--------------------------------------
create table GroupTable
(
    id          int IDENTITY(1,1),
    idGroupTable AS [dbo].getUniqueIdWithPrefix1(id, 'GTable') PERSISTED primary key,
    groupName   nvarchar(50),
    foundedTime datetime
)
--------------------------------------
create table TypeTable
(
    id       int IDENTITY(1,1),
    idTypeTable AS [dbo].getUniqueIdWithPrefix1(id, 'TypeTable') PERSISTED primary key,
    typeName nvarchar (100) not null,
    price    float
)
---------------------------------------
create table DetailGroupTable
(
    id           int IDENTITY(1,1),
    idDetailGroupTable AS [dbo].getUniqueIdWithPrefix1(id, 'DTGTable') PERSISTED primary key,
    idGroupTable nvarchar (15),
    idTablecf    nvarchar (15),
    groupTime    datetime
)
---------------------------------------
create table Ordercf
(
    id           int IDENTITY(1,1),
    idOrdercf AS [dbo].getUniqueIdWithPrefix1(id, 'Ordercf') PERSISTED primary key,
    idGroupTable nvarchar (15),
    idProduct    nvarchar (15),
    quantity     int,
    timeOrder    datetime
)
----------- ADD FOREIGN KEY
--------
alter table Invoice
    add FOREIGN KEY (idEmployee) REFERENCES Employee (idEmployee);
alter table Invoice
    add foreign key (idGroupTable) REFERENCES GroupTable (idGroupTable);
alter table DetailInvoice
    add foreign key (idInvoice) REFERENCES Invoice (idInvoice);
alter table DetailInvoice
    add foreign key (idProduct) REFERENCES Product (idProduct);
alter table Account
    add foreign key (idEmployee) REFERENCES Employee (idEmployee);
alter table Shift
    add foreign key (idEmployee) REFERENCES Employee (idEmployee);
alter table Product
    add foreign key (idSale) REFERENCES Sale (idSale);
alter table Product
    add foreign key (idProductType) REFERENCES TypeProduct (idTypeProduct);
alter table DetailProduct
    add foreign key (idProduct) REFERENCES Product (idProduct);
alter table DetailProduct
    add foreign key (idIngredient) REFERENCES Ingredient (idIngredient);
alter table ImportGoods
    add foreign key (idEmployee) REFERENCES Employee (idEmployee);
alter table DetailImportGoods
    add foreign key (idImportGoods) REFERENCES ImportGoods (idImportGoods);
alter table DetailImportGoods
    add foreign key (idIngredient) REFERENCES Ingredient (idIngredient);
alter table Tablecf
    add foreign key (idTypeTable) REFERENCES TypeTable (idTypeTable);
alter table DetailGroupTable
    add foreign key (idGroupTable) REFERENCES GroupTable (idGroupTable);
alter table DetailGroupTable
    add foreign key (idTablecf) REFERENCES Tablecf (idTablecf);
alter table Ordercf
    add foreign key (idGroupTable) REFERENCES GroupTable (idGroupTable);
alter table Ordercf
    add foreign key (idProduct) REFERENCES Product (idProduct);