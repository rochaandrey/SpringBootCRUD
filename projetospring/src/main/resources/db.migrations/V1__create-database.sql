CREATE TABLE cliente(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE produto(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL CHECK (preco >= 0)
);

CREATE TABLE pedido(
    id BIGSERIAL PRIMARY KEY,
    data_pedido TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(100) NOT NULL DEFAULT 'PENDENTE',
    cliente_id BIGINT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CHECK (status IN ('PENDENTE', 'PROCESSANDO', 'ENVIADO', 'ENTREGUE', 'CANCELADO'))
);

CREATE TABLE item_pedido(
    id BIGSERIAL PRIMARY KEY,
    quantidade INTEGER NOT NULL CHECK (quantidade >= 0),
    preco_unitario DECIMAL(10,2) NOT NULL CHECK (preco_unitario >= 0),
    pedido_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produto(id),
    UNIQUE (pedido_id, produto_id)
);