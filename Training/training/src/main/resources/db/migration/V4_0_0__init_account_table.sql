CREATE TABLE IF NOT EXISTS t_account (
    id INT PRIMARY KEY,
    c_balance INT NOT NULL DEFAULT 0 CHECK (c_balance > -1)
);

INSERT INTO t_account VALUES (1, 500);