CREATE TABLE parcela (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  valor DECIMAL(10,2) NOT NULL,
  recorrencia BIGINT(20) NOT NULL,
  pago TINYINT(1) DEFAULT 0,
  id_divida BIGINT(20) NULL,
  FOREIGN KEY (id_divida) REFERENCES divida(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO parcela (recorrencia,valor, pago, id_divida) VALUES (3,1500.30, 1, 1);
INSERT INTO parcela (recorrencia,valor, pago, id_divida) VALUES (5,1010.89, 0, 2);
INSERT INTO parcela (recorrencia,valor, pago, id_divida) VALUES (10,400.49, 0, 3);
