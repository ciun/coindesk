CREATE TABLE coindesk_detail (
--    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(3) PRIMARY KEY,
    symbol VARCHAR(10) NOT NULL,
    rate VARCHAR(15) NOT NULL,
    description VARCHAR(200) NOT NULL,
    rate_float DECIMAL(15, 4) NOT NULL,
    update_date date
);

CREATE INDEX idx_code ON coindesk_detail (code);


CREATE TABLE coindesk (
    ccy_code VARCHAR(3) PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);


