CREATE TABLE invoice (
    id   int IDENTITY(1,1),
    idInvoice AS [dbo].getUniqueIdWithPrefix3(id, 'Invoice') PERSISTED primary key,
    name nvarchar(30) not null
                     )