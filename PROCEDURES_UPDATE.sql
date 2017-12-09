USE `BookstorCZ`;

-- USUŃ WIERSZ O PODANYM ID
DROP PROCEDURE IF EXISTS updateKomorka;
DELIMITER //
CREATE PROCEDURE updateKomorka(tabela CHAR(20), kolumna CHAR(20), IDWiersza INT, parametr VARCHAR(20))
BEGIN
  IF EXISTS(SELECT * 
			FROM information_schema.COLUMNS 
			WHERE TABLE_SCHEMA = 'BookstoreCZ' 
					AND TABLE_NAME = tabela
                    AND COLUMN_NAME = kolumna) THEN
			
	UPDATE tabela
	SET tabela.kolumna=parametr
	WHERE tabela.ID=IDWiersz;
  ELSE
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Niepoprawny odnośnik';
  END IF;
END //
DELIMITER ;