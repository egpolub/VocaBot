CREATE TABLE IF NOT EXISTS users
(
    user_id bigint PRIMARY KEY,
    firstname VARCHAR(100),
    username VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE,
    created TIMESTAMP without time zone NOT NULL DEFAULT now(),
    updated TIMESTAMP without time zone NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS words
(
    word_id SERIAL PRIMARY KEY,
    dictionary_id INTEGER NOT NULL ,
    word VARCHAR(255) NOT NULL,
    translation VARCHAR(255) NOT NULL,
    created TIMESTAMP without time zone NOT NULL DEFAULT now(),
    updated TIMESTAMP without time zone NOT NULL DEFAULT now(),
    UNIQUE (word_id, word, translation)
);

CREATE TABLE IF NOT EXISTS dictionary
(
    dictionary_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50),
    user_id BIGINT NOT NULL,
    total INTEGER NOT NULL DEFAULT 0,
    total_limit INTEGER,
    UNIQUE (user_id, name)
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE
);

INSERT INTO roles (name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT,
    role_id INTEGER,
    CONSTRAINT fk_user_roles_roles
        FOREIGN KEY(role_id)
            REFERENCES roles(role_id),
    CONSTRAINT fk_user_roles_user
        FOREIGN KEY(user_id)
            REFERENCES public.users(user_id)
);

