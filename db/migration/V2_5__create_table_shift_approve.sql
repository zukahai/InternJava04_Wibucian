use
wibucian
go
---
drop table if exists ShiftApprove

CREATE TABLE ShiftApprove
(
    id          int IDENTITY(1,1),
    idShiftApprove AS [dbo].getUniqueIdWithPrefix1(id, 'SApprove') PERSISTED primary key,
    approveTime datetime,
    approveFor  date
)
