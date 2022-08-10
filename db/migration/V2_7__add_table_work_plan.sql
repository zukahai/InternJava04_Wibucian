use
wibucian
go

drop table if exists WorkPlan

create table WorkPlan
(
    id        int IDENTITY(1,1),
    idWorkPlan AS [dbo].getUniqueIdWithPrefix1(id, 'WorkPlan') PERSISTED primary key,
    shiftDate date,
    shiftCode int,
    work      bit
)