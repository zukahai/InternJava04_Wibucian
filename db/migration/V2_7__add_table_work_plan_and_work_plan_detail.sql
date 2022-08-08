use
wibucian
go

drop table if exists WorkPlan
drop table if exists WorkPlanDetail

create table WorkPlan
(
    id      int IDENTITY(1,1),
    idWorkPlan AS [dbo].getUniqueIdWithPrefix1(id, 'WorkPlan') PERSISTED primary key,
    planFor date
)

create table WorkPlanDetail
(
    id        int IDENTITY(1,1),
    idWorkPlanDetail AS [dbo].getUniqueIdWithPrefix1(id, 'WPDetail') PERSISTED primary key,
    shiftDate date,
    shiftCode int,
    work      bit
)