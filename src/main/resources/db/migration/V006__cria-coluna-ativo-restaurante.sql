ALTER TABLE restaurante
ADD COLUMN ativo TINYINT(1) NOT NULL;

SET SQL_SAFE_UPDATES = 0;

update restaurante set ativo = true;

SET SQL_SAFE_UPDATES = 1;