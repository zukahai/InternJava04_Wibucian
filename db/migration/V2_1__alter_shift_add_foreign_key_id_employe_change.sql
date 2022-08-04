use wibucian
go
---
alter table Shift
    add foreign key (idEmployeeChange) REFERENCES Employee (idEmployee);