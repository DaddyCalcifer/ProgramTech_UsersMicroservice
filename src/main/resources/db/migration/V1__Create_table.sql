CREATE TABLE IF NOT EXISTS accounts (
    id UUID PRIMARY KEY,
    first_name varchar(100) NOT NULL,
    surname varchar(100) NOT NULL,
    last_name varchar(100),
    email varchar(255) NOT NULL,
    password_hash varchar(255) NOT NULL,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role int NOT NULL DEFAULT 1,
    activity_status int NOT NULL DEFAULT 0,
    is_deleted boolean NOT NULL DEFAULT false,
    current_event integer not null default 0
)