create table datos_personas
(
    id          bigserial primary key,
    sede        varchar(500) not null,
    vinculacion varchar(500) not null,
    actividad   varchar(500) not null,
    facultad    varchar(500) not null,
    programa    varchar(500) not null,
    promedio    DECIMAL       not null,
    nivel       varchar(500) not null,
    anho        integer      not null
)