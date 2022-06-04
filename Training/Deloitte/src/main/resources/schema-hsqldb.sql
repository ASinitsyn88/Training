SET DATABASE SQL SYNTAX PGS TRUE;

CREATE TABLE IF NOT EXISTS tasks (
    id              BIGINT IDENTITY PRIMARY KEY,
    description     VARCHAR(1024) NOT NULL,
    modifytimestamp TIMESTAMP DEFAULT now() NOT NULL,
    completed       BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS users (
    id              BIGINT IDENTITY PRIMARY KEY,
    login           VARCHAR(255) UNIQUE NOT NULL,
    first_name      VARCHAR(50) NOT NULL,
    last_name       VARCHAR(50) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    role            VARCHAR(20) DEFAULT 'USER' NOT NULL,
    status          VARCHAR(20) DEFAULT 'ACTIVE',
    task_id         BIGINT,
    FOREIGN KEY (task_id) REFERENCES tasks(id)
);