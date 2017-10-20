CREATE TABLE gasto (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  des_curta VARCHAR(30) NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  dt_ocorrencia DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO gasto (des_curta, valor, dt_ocorrencia) VALUES ('pão de queijo', 7.5, '2017-08-12');
INSERT INTO gasto (des_curta, valor, dt_ocorrencia) VALUES ('almoço madeiro', 45.00, '2017-09-05');
INSERT INTO gasto (des_curta, valor, dt_ocorrencia) VALUES ('chocolate', 7.5, '2017-10-12');