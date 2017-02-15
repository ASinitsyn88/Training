CREATE SCHEMA inpas

  CREATE SEQUENCE inpas.sqn_users START WITH 1;

  CREATE TABLE inpas.users (
    id BIGINT DEFAULT NEXTVAL('inpas.sqn_users'),
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(20) NOT NULL,
    patronymic VARCHAR(20) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    login VARCHAR(20) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id)
  );

  CREATE UNIQUE INDEX users_login_uindex ON inpas.users (login);