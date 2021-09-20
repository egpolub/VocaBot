CREATE TABLE users
(
    user_id bigint PRIMARY KEY,
    firstname VARCHAR(100),
    username VARCHAR(100),
    email VARCHAR(255),
    created TIMESTAMP without time zone NOT NULL DEFAULT now(),
    updated TIMESTAMP without time zone NOT NULL DEFAULT now()
);

CREATE TABLE words
(
    word_id SERIAL PRIMARY KEY,
    dictionary_id INTEGER ,
    word VARCHAR(255),
    translation VARCHAR(255),
    created TIMESTAMP without time zone NOT NULL DEFAULT now(),
    updated TIMESTAMP without time zone NOT NULL DEFAULT now()
);

CREATE TABLE dictionary
(
    dictionary_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(50),
    user_id BIGINT,
    total INTEGER,
    total_limit INTEGER
);
