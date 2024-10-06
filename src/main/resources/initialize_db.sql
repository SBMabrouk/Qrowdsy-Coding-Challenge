
-- Create the database
CREATE DATABASE librarydb;

-- Connect to the newly created database
\c librarydb;

-- Create a user with a password
CREATE USER postgres WITH PASSWORD 'admin';

-- Grant all privileges on the new database to the user
GRANT ALL PRIVILEGES ON DATABASE librarydb TO postgres;

-- Optionally, create tables needed
CREATE TABLE library (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    town VARCHAR(255),
    postcode VARCHAR(50),
    telephone VARCHAR(20),
    email VARCHAR(255)
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    author VARCHAR(255),
    genre VARCHAR(50),
    release_date DATE
);

CREATE TABLE library_book (
    id SERIAL PRIMARY KEY,
    library_id INT REFERENCES libraries(id),
    book_id INT REFERENCES books(id),
    quantity INT
);
