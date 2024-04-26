CREATE TABLE IF NOT EXISTS event_participants (
    event_id UUID,
    account_id UUID,
    status INTEGER NOT NULL DEFAULT 1,
    PRIMARY KEY (event_id, account_id),
    CONSTRAINT fk_event_id FOREIGN KEY (event_id) REFERENCES events(id),
    CONSTRAINT fk_user_id FOREIGN KEY (account_id) REFERENCES accounts(id),
    CONSTRAINT fk_status_id FOREIGN KEY (status) REFERENCES participant_statuses(id)
);
