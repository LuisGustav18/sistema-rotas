CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE rota (
                      id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                      titulo VARCHAR(255) NOT NULL UNIQUE,
                      decricao VARCHAR(2000),
                      longitude NUMERIC(9, 6),
                      latitude NUMERIC(9, 6),
                      projeto_id UUID,
                      data DATE,
                      data_cricao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (projeto_id) REFERENCES projeto(id)
);