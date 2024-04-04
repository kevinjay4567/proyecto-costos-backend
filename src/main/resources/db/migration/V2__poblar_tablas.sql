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
    agrupacion  bigserial NOT NULL,
    CONSTRAINT subcuenta_pk PRIMARY KEY (id),
    CONSTRAINT subcuenta_agrupacion_fk FOREIGN KEY (agrupacion) REFERENCES agrupacion (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE balance
(
    id        bigserial NOT NULL,
    anho      numeric   NOT NULL,
    valor     int8      NOT NULL,
    concepto  bigserial NOT NULL,
    subcuenta bigserial NOT NULL,
    sede      bigserial NOT NULL,
    empresa   bigserial NOT NULL,
    CONSTRAINT balance_pk PRIMARY KEY (id),
    CONSTRAINT balance_concepto_fk FOREIGN KEY (concepto) REFERENCES concepto (id),
    CONSTRAINT balance_empresa_fk FOREIGN KEY (empresa) REFERENCES empresa (id),
    CONSTRAINT balance_sede_fk FOREIGN KEY (sede) REFERENCES sede (id),
    CONSTRAINT balance_subcuenta_fk FOREIGN KEY (subcuenta) REFERENCES subcuenta (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO sede
    (id, nombre)
VALUES (20, 'BOGOTA');

INSERT INTO empresa
    (id, nombre)
VALUES (2001, 'NIVEL CENTRAL SEDE BOGOTA');

INSERT INTO agrupacion
    (id, nombre)
VALUES (nextval('agrupacion_id_seq'::regclass), 'GASTOS PERSONAL');

INSERT INTO subcuenta
    (id, descripcion)
VALUES (51010101, 'SUELDOS DEL PERSONAL');

INSERT INTO concepto
    (id, nombre)
VALUES (nextval('concepto_id_seq'::regclass), 'OTROS COSTOS');

INSERT INTO balance
    (id, anho, valor, concepto, subcuenta, sede, empresa)
VALUES (1, 2020, 45863292555, 1, 51010101, 20, 2001);