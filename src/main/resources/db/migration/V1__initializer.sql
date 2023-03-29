CREATE SCHEMA IF NOT EXISTS test;

CREATE TABLE IF NOT EXISTS users (
     id INTEGER AUTO_INCREMENT,
     username VARCHAR(40),
     password VARCHAR(60),
     PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
     id INTEGER AUTO_INCREMENT,
     userfrom INTEGER,
     userto INTEGER,
     text VARCHAR(255),
     status VARCHAR(15),
     PRIMARY KEY (id)
)