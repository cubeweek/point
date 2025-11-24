CREATE TABLE MEMBER (
    member_id VARCHAR(20) PRIMARY KEY,
    email VARCHAR(100),
    signup_date DATE,
    withdrawal_date DATE
);

CREATE TABLE BUY(
     buy_srl BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
     member_id VARCHAR(20),
     pay_amt VARCHAR(100),
     buy_dt DATETIME,
     pay_dt DATETIME
);

CREATE TABLE POINT (
    point_srl BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(20),
    buy_srl BIGINT UNSIGNED,
    type VARCHAR(10), -- EARN / USE / USE_CANCEL
    amt INT,
    used_amt INT DEFAULT 0,
    expiration_date DATE,
    cancel_yn BOOLEAN DEFAULT FALSE,
    manual_yn BOOLEAN DEFAULT FALSE,
    create_by BIGINT UNSIGNED,
    create_dt DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_by BIGINT UNSIGNED,
    update_dt DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE POINT_POLICY (
    policy_srl BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    policy_name VARCHAR(20),
    description VARCHAR(200),
    amt INT UNSIGNED,
    expiration_days INT UNSIGNED CHECK(expiration_days >= 0 AND expiration_days <= 1825),
    expiration_date DATE
);

-- POINT_POLICY_PERSONAL
CREATE TABLE POINT_POLICY_PERSONAL (
    member_id VARCHAR(20) PRIMARY KEY,
    max_limit INT,
    create_dt DATETIME DEFAULT CURRENT_TIMESTAMP
);
