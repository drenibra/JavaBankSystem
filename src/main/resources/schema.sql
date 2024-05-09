CREATE TABLE Account (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);