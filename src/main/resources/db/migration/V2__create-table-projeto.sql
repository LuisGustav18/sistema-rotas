CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE projeto (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         titulo VARCHAR(255) NOT NULL,
                         data DATE,
                         usuario_id UUID,
                         data_cricao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);