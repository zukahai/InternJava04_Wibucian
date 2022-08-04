use wibucian
go
---
alter table ShiftRotate
add idEmployeeChange nvarchar(15) foreign key references Employee(idEmployee)