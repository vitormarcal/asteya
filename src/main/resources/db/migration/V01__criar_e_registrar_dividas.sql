CREATE TABLE divida (
  id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  des_curta VARCHAR(30) NOT NULL ,
  des_detalhada VARCHAR(300) NOT NULL ,
  dt_ini_ocorrencia DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO divida (des_curta, des_detalhada, dt_ini_ocorrencia)
VALUES ('celular', 'celular asus zenfone 3 max comprado no submarino', '2017-06-10');

INSERT INTO divida (des_curta, des_detalhada, dt_ini_ocorrencia)
VALUES ('cama box', 'cama box comprada pela mãe', '2017-09-08');

INSERT INTO divida (des_curta, des_detalhada, dt_ini_ocorrencia)
VALUES ('compras mercado', 'compras no mercado tia da silva', '2017-6-20');