use
wibucian
go

drop table if exists RollCall

create table RollCall
(
    id           int IDENTITY(1,1),
    idInvoice AS [dbo].getUniqueIdWithPrefix1(id, 'Rollcall') PERSISTED primary key,
    idShift   nvarchar (15) foreign key references Shift(idShift),
    rollCallTime datetime
)