CREATE TABLE IF NOT EXISTS CARGO.TRANSACTION_LOG
(
    ID SERIAL PRIMARY KEY,
    RESPONSE VARCHAR,
    REQUEST VARCHAR,
    SOURCE VARCHAR(30),
    CREATED_DATE TIMESTAMP NOT NULL
)