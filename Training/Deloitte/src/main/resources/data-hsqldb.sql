-- Used https://bcrypt-generator.com/ for password encryption
-- password: pwd123
INSERT INTO users(id, login, first_name, last_name, password, role, status)
VALUES (1, 'test', 'test', 'testov', '$2a$12$Cc4J/uZOmY/tdob8ivc.ZOdWjcE6cbuzHrUSEwnBwh90pVgf/Y7TG', 'ADMIN', 'ACTIVE');

-- password: pwd1234
INSERT INTO users(id, login, first_name, last_name, password, role, status)
VALUES (2, 'test2', 'test2', 'testov2', '$2a$12$BaN0XGCy5adDfIhwz4OA7ucOZKnfi5aTZXkuVJBwtpPJMR/OE9glW', 'ADMIN', 'ACTIVE');

-- password: pwd12345
INSERT INTO users(id, login, first_name, last_name, password, role, status)
VALUES (3, 'test3', 'test3', 'testov3', '$2a$12$u.LKgWD4Kl0rihiJifsatuA8srFXQFusTxCbeu1lbU9iePHU8Csf.', 'USER', 'ACTIVE');