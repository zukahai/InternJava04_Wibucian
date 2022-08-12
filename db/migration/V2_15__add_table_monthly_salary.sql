use
wibucian
go

drop table if exists Salary

create table MonthlySalary
(
    id           int IDENTITY(1,1),
    idMonthSalary AS [dbo].getUniqueIdWithPrefix1(id, 'MSalary') PERSISTED primary key,
    idEmployee nvarchar(15) foreign key references Employee(idEmployee),
    shiftWork int,
    hourWork float,
    total float
)