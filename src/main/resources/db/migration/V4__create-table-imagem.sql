CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE imagem (
                        id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                        url VARCHAR(500) NOT NULL UNIQUE,
                        rota_id UUID,
                        data_cricao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        FOREIGN KEY (rota_id) REFERENCES rota(id)
);