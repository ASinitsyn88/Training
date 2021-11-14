CREATE SCHEMA IF NOT EXISTS BOOKINGS;
create table airports_data (
    airport_code char(3) not null constraint airports_data_pkey primary key,
    airport_name text   not null,
    city         text   not null,
    coordinates  text   not null,
    timezone     text   not null
);
