INSERT INTO MEMBER
    (MEMBER_ID, EMAIL, SIGNUP_DATE, WITHDRAWAL_DATE)
VALUES
    ('adidaslover', 'adidaslover@gmail.com', '2022-02-22', null),
    ('adidashater', 'adidashater@gmail.com', '2022-11-01', '2025-11-24'),
    ('nikelover', 'nikelover@gmail.com', '2024-07-07', null),
    ('nikehater', 'nikehater@gmail.com', '2024-11-25', null);

INSERT INTO POINT
    (MEMBER_ID, TYPE, AMT, EXPIRATION_DATE, CANCEL_YN, MANUAL_YN, BUY_SRL, used_amt, update_by)
VALUES
    ('adidaslover', 'EARN', 1000, '2025-12-31', false, false, null, 1000, 4),
    ('adidaslover', 'EARN', 5000, '2025-11-01', false, false, null, 4000, 5),
    ('adidaslover', 'EARN', 2000, '2025-11-01', false, true, null, 2000, 5),
    ('adidaslover', 'USE', 1000, null, false, false, 1, 0, null),
    ('adidaslover', 'USE', 6000, null, false, false, 2, 0, null),
    ('adidaslover', 'EARN', 10000, '2025-12-31', false, true, null, 0, null),
    ('nikehater', 'EARN', 50000, '2025-12-31', false, true, null, 0, null),
    ('nikehater', 'EARN', 13640, '2026-06-20', false, true, null, 0, null),
    ('nikelover', 'EARN', 5000, '2025-11-30', false, true, null, 0, null);


INSERT INTO POINT_POLICY
    (POLICY_NAME, DESCRIPTION, AMT, EXPIRATION_DAYS)
VALUES
    ('ONE_TIME_MAX_LIMIT', '최대 1회 적립가능 포인트', 100000, 365),
    ('FREE_POINT_PERSONAL', '개인 별 최대 보유 포인트(기본값)', 1000000, 0);

INSERT INTO BUY
    (MEMBER_ID, PAY_AMT, BUY_DT, PAY_DT)
VALUES
    ('adidaslover', 10000, '2025-11-01 12:34:56', '2025-11-01 12:34:56'),
    ('adidaslover', 78570, '2025-11-01 17:00:56', '2025-11-01 20:00:56');

INSERT INTO POINT_POLICY_PERSONAL
    (MEMBER_ID, MAX_LIMIT)
VALUES
    ('adidaslover', 300000),
    ('nikehater', 50000);