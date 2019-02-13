CREATE TABLE lancamento(
	id BIGINT(20)PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50)NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	id_categoria BIGINT(20) NOT NULL,
	id_pessoa BIGINT(20)  NOT NULL,
	FOREIGN KEY (id_categoria)REFERENCES categoria (id),
	FOREIGN KEY (id_pessoa)REFERENCES pessoa (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------------------------
INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) 
VALUES("Salário", "2019-02-07", null, 5593.89, "Cast group", "RECEITA", 5, 1);

INSERT INTO lancamento(descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) 
VALUES("Enel", "2019-02-15", "2019-02-14", 356.85, "Conta de luz paga pelo aplicativo", "DESPESA", 5, 1);

-- -------------------------------------------