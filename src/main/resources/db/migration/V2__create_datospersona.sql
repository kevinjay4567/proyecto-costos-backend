CREATE TABLE datos_persona
(
    id     bigserial NOT NULL,
    sede varchar   NOT NULL,
    vinculacion varchar NOT NULL,
    actividad varchar NOT NULL,
    facultad varchar NOT NULL,
    programa varchar NOT NULL,
    promedio FLOAT NOT NULL,
    nivel varchar NOT NULL,
    CONSTRAINT datos_persona_pk PRIMARY KEY (id)
);