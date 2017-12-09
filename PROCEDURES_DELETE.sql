USE `BookstorCZ`;

-- USUŃ WIERSZ O PODANYM ID
DROP PROCEDURE IF EXISTS usunWiersz;
DELIMITER //
CREATE PROCEDURE usunWiersz(IDUsun INT, tabela CHAR(20))
BEGIN
  IF EXISTS(SELECT * 
			FROM information_schema.COLUMNS 
			WHERE TABLE_SCHEMA = 'BookstoreCZ' 
					AND TABLE_NAME = tabela) THEN
                     
						SET @str = CONCAT('DELETE FROM ',tabela,' WHERE ',tabela,'.ID = IDUsun;');
						PREPARE stmt FROM @str;
						EXECUTE stmt;
						DEALLOCATE PREPARE stmt;
  ELSE
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Niepoprawna kolumna';
  END IF;
END //
DELIMITER ;

