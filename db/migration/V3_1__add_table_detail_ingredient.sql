CREATE TABLE DetailIngredient
(
    id           int IDENTITY(1,1),
    idDetailIngredient AS [dbo].getUniqueIdWithPrefix1(id, 'DTIngredient') PERSISTED primary key,
    idIngredient  nvarchar (15),
    valueChange float,
    content nvarchar (50) not null,
    dateTime     datetime
)

alter table DetailIngredient
    add foreign key (idIngredient) REFERENCES Ingredient (idIngredient);