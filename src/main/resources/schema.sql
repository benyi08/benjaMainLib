CREATE TABLE IF NOT EXISTS  person (
    id SERIAL PRIMARY KEY,
    name TEXT,
    password TEXT,
    role TEXT
);

CREATE TABLE IF NOT EXISTS borrow (
    id SERIAL PRIMARY KEY,
    name TEXT,
    startDate TIMESTAMP,
    duration TIME
);

CREATE TABLE IF NOT EXISTS book (
    id SERIAL PRIMARY KEY,
    title TEXT,
    author TEXT,
    releaseDate TEXT,
    pages INTEGER,
    isBorrowed BOOLEAN
);