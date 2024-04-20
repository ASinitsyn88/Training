CREATE SEQUENCE token_id_seq AS INTEGER;

CREATE TABLE IF NOT EXISTS token (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('token_id_seq'),
    expired BOOLEAN NOT NULL,
    revoked BOOLEAN NOT NULL,
    user_id INTEGER
		CONSTRAINT fkiblu4cjwvyntq3ugo31klp1c6 REFERENCES _user,
    token VARCHAR(255) UNIQUE,
    token_type VARCHAR(255)
        CONSTRAINT token_token_type_check CHECK ((token_type)::text = 'BEARER'::text)
);

ALTER SEQUENCE token_id_seq OWNED BY token.id;