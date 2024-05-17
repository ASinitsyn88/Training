CREATE SEQUENCE student_id_seq AS INTEGER;

CREATE TABLE IF NOT EXISTS student (
    id INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('student_id_seq'),
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
);

ALTER SEQUENCE student_id_seq OWNED BY student.id;