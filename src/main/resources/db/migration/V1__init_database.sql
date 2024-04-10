CREATE TABLE agrupacion
(
    id     bigserial NOT NULL,
    nombre varchar   NOT NULL,
    CONSTRAINT agrupacion_pk PRIMARY KEY (id)
);

CREATE TABLE concepto
(
    id     bigserial NOT NULL,
    nombre varchar   NOT NULL,
    CONSTRAINT concepto_pk PRIMARY KEY (id)
);

CREATE TABLE empresa
(
    id     bigserial NOT NULL,
    nombre varchar   NOT NULL,
    CONSTRAINT empresa_pk PRIMARY KEY (id)
);

CREATE TABLE sede
(
    id     bigserial NOT NULL,
    nombre varchar   NOT NULL,
    CONSTRAINT newtable_pk PRIMARY KEY (id)
);

CREATE TABLE subcuenta
(
    id          bigserial NOT NULL,
    descripcion varchar   NOT NULL,
    agrupacion_id  bigserial NOT NULL,
    CONSTRAINT subcuenta_pk PRIMARY KEY (id),
    CONSTRAINT subcuenta_agrupacion_fk FOREIGN KEY (agrupacion_id) REFERENCES agrupacion (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE balance
(
    id        bigserial NOT NULL,
    anho      numeric   NOT NULL,
    valor     int8      NOT NULL,
    concepto_id  bigserial NOT NULL,
    subcuenta_id bigserial NOT NULL,
    sede_id      bigserial NOT NULL,
    empresa_id   bigserial NOT NULL,
    CONSTRAINT balance_pk PRIMARY KEY (id),
    CONSTRAINT balance_concepto_fk FOREIGN KEY (concepto_id) REFERENCES concepto (id),
    CONSTRAINT balance_empresa_fk FOREIGN KEY (empresa_id) REFERENCES empresa (id),
    CONSTRAINT balance_sede_fk FOREIGN KEY (sede_id) REFERENCES sede (id),
    CONSTRAINT balance_subcuenta_fk FOREIGN KEY (subcuenta_id) REFERENCES subcuenta (id) ON DELETE CASCADE ON UPDATE CASCADE
);

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

CREATE TABLE pta
(
    id     bigserial NOT NULL,
    categoria_docente varchar   NOT NULL,
    item numeric NOT NULL,
    actividad_item varchar NOT NULL,
    sede varchar NOT NULL,
    tipo varchar NOT NULL,
    valor bigint NOT NULL,
    CONSTRAINT pta_pk PRIMARY KEY (id)
);

CREATE TABLE unimedios
(
    id     bigserial NOT NULL,
    agrupacion varchar   NOT NULL,
    valor bigint NOT NULL,
    anho numeric NOT NULL,
    CONSTRAINT unimedios_pk PRIMARY KEY (id)
);