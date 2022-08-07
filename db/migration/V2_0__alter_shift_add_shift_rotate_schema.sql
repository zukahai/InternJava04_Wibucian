use
wibucian
go
---

alter table shift
    add
        isRequestShift bit default 1,
    isOvertimeRequest bit default 0,
    requestTime datetime
go
---

create table shiftRotate
(
    id int IDENTITY(1,1),
    idShiftRotate AS [dbo].getUniqueIdWithPrefix1(id, 'ShiftRotate') PERSISTED primary key,
    idShift nvarchar(15) foreign key references Shift (idShift),
    idEmployee nvarchar(15) foreign key references Employee(idEmployee)
)
