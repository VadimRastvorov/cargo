CREATE TABLE IF NOT EXISTS CARGO.CARCASE_TYPE
(
    ID SERIAL PRIMARY KEY,
    TITLE VARCHAR(100),
    CODE VARCHAR(15) UNIQUE NOT NULL,
    WIDTH INTEGER,
    HEIGHT INTEGER,
    CREATED_DATE TIMESTAMP NOT NULL
)