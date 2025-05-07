--liquibase formatted sql
--changeset dockup:user-table
--validCheckSum: 9:7d89c093b93dad63604da6b253e0fa21

CREATE TABLE users (
    id TEXT PRIMARY KEY,
    username TEXT NOT NULL,
    display_name TEXT NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UC_Username UNIQUE (username)
);
