use
wibucian
go
---
alter table Shift
drop
column if exists timeStart,
     column if exists timeEnd,
    column if exists shiftDate,
    column if exists shiftCode
go

alter table Shift
    add shiftDate date, shiftCode int

---
alter table Invoice
drop
column if exists toltalMoney,
    column if exists totalMoney
go

alter table Invoice
    add totalMoney float

---
alter table DetailInvoice
drop
column if exists dateTime,
    column if exists totalMoney

alter table DetailInvoice
    add dateTime datetime, totalMoney float

go
---
alter table Sale
drop
column if exists timeStart,
    column if exists timeEnd,
    column if exists price
go
---

alter table Sale
    add timeStart datetime,
    timeEnd datetime
