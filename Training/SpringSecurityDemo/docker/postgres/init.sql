CREATE SCHEMA springsecuritycourse;

CREATE TABLE springsecuritycourse.users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER' NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

INSERT INTO springsecuritycourse.users(id, email, first_name, last_name, password, role, status)
VALUES (1, 'admin@mail.com', 'Admin', 'Adminov', '$2a$12$8NiTAGNF.j9rXP8z/Ig9IO8VRdPCZf1kYTXmNP9Xq013BQVcVpzvm', 'ADMIN', 'ACTIVE');

INSERT INTO springsecuritycourse.users(id, email, first_name, last_name, password, role, status)
VALUES (2, 'user@mail.com', 'User', 'Userov', '$2a$12$ObtjMSMRNSJiKPw66W/Zyu/IIpTEizS27GGYHBpM0vBj6JpvTe1YG', 'USER', 'ACTIVE');