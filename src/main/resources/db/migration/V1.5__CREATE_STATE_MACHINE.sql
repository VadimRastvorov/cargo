CREATE TABLE IF NOT EXISTS CARGO.STATE_MACHINE
(
    ID SERIAL PRIMARY KEY,
    CHAT_ID VARCHAR(255) NOT NULL,
    STATE VARCHAR(255) NOT NULL,
    CREATED_DATE TIMESTAMP NOT NULL
)