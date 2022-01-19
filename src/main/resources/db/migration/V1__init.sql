CREATE TABLE product_offering
(
    id          TEXT      NOT NULL PRIMARY KEY,
    last_update timestamptz NOT NULL,
    version     BIGINT    NOT NULL,
    body        JSONB     NOT NULL
);