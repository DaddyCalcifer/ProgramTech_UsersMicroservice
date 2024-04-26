CREATE TABLE IF NOT EXISTS events (
    id UUID PRIMARY KEY,
    name varchar(100) NOT NULL,
    equipment varchar(300),
    seats INTEGER NOT NULL DEFAULT 1,
    organizer UUID NOT NULL,
    place varchar(125)
)