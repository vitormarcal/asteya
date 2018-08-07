CREATE TABLE gasto (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  des_curta VARCHAR(30) NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  dt_ocorrencia DATE NOT NULL,
  num_parcelas INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO gasto (des_curta, valor, dt_ocorrencia, num_parcelas) VALUES ('pão de queijo', 7.5, '2017-08-12', 1);
INSERT INTO gasto (des_curta, valor, dt_ocorrencia, num_parcelas) VALUES ('almoço madeiro', 45.00, '2017-09-05', 1);
INSERT INTO gasto (des_curta, valor, dt_ocorrencia, num_parcelas) VALUES ('chocolate', 7.5, '2017-10-12', 1);