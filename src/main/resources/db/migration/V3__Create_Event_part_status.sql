CREATE TABLE IF NOT EXISTS participant_statuses (
    id SERIAL PRIMARY KEY,
    status VARCHAR(20) UNIQUE
);