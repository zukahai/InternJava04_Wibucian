use
wibucian
go

alter table ShiftRotate
    add idShiftExchange nvarchar(15) foreign key references Shift(idShift)