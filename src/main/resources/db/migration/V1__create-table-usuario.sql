CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE usuario (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         senha VARCHAR(255) NOT NULL,
                         data_cricao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);