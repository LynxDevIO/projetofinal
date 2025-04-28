CREATE TABLE IF NOT EXISTS doador (
    id SERIAL PRIMARY KEY,
    nome_completo VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS entidade_assistencial (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(14) NOT NULL UNIQUE,
    tipo VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS doacao (
    id SERIAL PRIMARY KEY,
    doador_id INTEGER NOT NULL REFERENCES doador(id),
    entidade_id INTEGER NOT NULL REFERENCES entidade_assistencial(id),
    item VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL,
    data DATE NOT NULL
); 