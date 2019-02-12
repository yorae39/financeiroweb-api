CREATE TABLE pessoa(
	id BIGINT(20)PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50)NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(50) NOT NULL,
	numero VARCHAR(10) NOT NULL,
	complemento VARCHAR(20) NOT NULL,
	bairro VARCHAR(20) NOT NULL,
	cep VARCHAR(10) NOT NULL,
	cidade VARCHAR(30) NOT NULL,
	estado VARCHAR(20)  NOT NULL,
	info VARCHAR(100)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------------------------

INSERT INTO pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado, info) 
VALUES("Luiz Paulo", true, "Av. Joaquim de Oliveira", "4578", "casa 01", "Boa Vista", "24466-142", "São Gonçalo", "Rio de Janeiro", null);
INSERT INTO pessoa(nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado, info) 
VALUES("Andréa Aureliano", true, "Av. Joaquim de Oliveira", "4578","casa 01", "Boa Vista", "24466-142", "São Gonçalo", "Rio de Janeiro", null);

-- -------------------------------------------