CREATE TABLE IF NOT EXISTS CARGO.PARCELS_TYPE
(
    ID SERIAL PRIMARY KEY,
    TITLE VARCHAR(100),
    CODE VARCHAR(15) UNIQUE NOT NULL,
    PARCEL VARCHAR,
    CREATED_DATE TIMESTAMP NOT NULL
)