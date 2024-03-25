create table files
(
    id              bigserial primary key,
    name            varchar(100) not null,
    total_registers integer      not null
)