CREATE TABLE person (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name TEXT,
    password TEXT,
    role TEXT,
    PRIMARY KEY (id)
);

CREATE TABLE borrow (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name TEXT,
    startDate DATETIME,
    duration TIME,
    PRIMARY KEY (id)
);

CREATE TABLE book (
    id INTEGER NOT NULL AUTO_INCREMENT,
    title TEXT,
    author TEXT,
    releaseDate TEXT,
    pages INTEGER,
    isBorrowed BIT,
    PRIMARY KEY (id)
);