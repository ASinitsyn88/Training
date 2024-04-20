CREATE SEQUENCE _user_id_seq AS INTEGER;

CREATE TABLE IF NOT EXISTS _user (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('_user_id_seq'),
    email VARCHAR(255) UNIQUE,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
        CONSTRAINT _user_role_check CHECK ((role)::text = ANY ((ARRAY ['USER'::character varying, 'ADMIN'::character varying])::text[]))
);

ALTER SEQUENCE _user_id_seq OWNED BY _user.id;