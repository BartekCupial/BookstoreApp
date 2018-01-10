DROP PROCEDURE IF EXISTS zróbDotowarowanie;
DELIMITER //
CREATE PROCEDURE zróbDotowarowanie(NIP VARCHAR(30), nrFaktury VARCHAR(30))
BEGIN
	-- zamiast tworzyc kolejne wiersze w tej tabeli można rozwiązać to tak, ze dodajemy nowe kolumny
    -- kazda kolumna bedzie reprezentowac kolejne zamowienie 
	INSERT INTO DotowarowanieHistoria
	SELECT * FROM Dotowarowanie
	WHERE Dotowarowanie.liczba > 0;
	
	UPDATE Dotowarowanie
	SET Dotowarowanie.liczba = 0;
	
	INSERT Dostawy VALUES (0, NIP, nrFaktury, NOW(), 'Złożono'); 
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS zmieńStatusDostawy;
DELIMITER //
CREATE PROCEDURE zmieńStatusDostawy(ID int, stus varchar(30))
BEGIN
	IF(stus = 'Złożono' || stus = 'Wysłano' || stus = 'Dostarczono')
    THEN 
    UPDATE Dostawy
    SET Dostawy.status = stus
    WHERE Dostawy.ID = ID;
    
    ELSE SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong status name';
    END IF;
END //
DELIMITER ;


-- uwaga na to zeby nie robic tego zanim nie dojdzie poprzednia dostawa, bo dopiero wtedy ilosc ksiazek w tabeli sie zupdatuje
-- można przypadkiem zamówić książki dwa razy (albo i więcej) 
-- jeśli masz ochotę możesz pokminić jak rozwiązac ten problem :)
DROP PROCEDURE IF EXISTS dotowarujBrakująceKsiążki;
DELIMITER //
CREATE PROCEDURE dotowarujBrakująceKsiążki()
BEGIN
	UPDATE Dotowarowanie 
    SET Dotowarowanie.liczba = Dotowarowanie.liczba - Książki.liczba -- minus i minus daje plus
    WHERE Dotowarowanie.ISBN = (SELECT ISBN FROM Książki WHERE Książki.liczba < 0);
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS dotowarujKsiążkęMającISBN;
DELIMITER //
CREATE PROCEDURE dotowarujKsiążkęMającISBN(ISBNt varchar (30), liczbat int)
BEGIN
	UPDATE Dotowarowanie 
    SET Dotowarowanie.liczba = Dotowarowanie.liczba + liczbat
    WHERE Dotowarowanie.ISBN = ISBNt;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS dotowarujKsiążkęMającTutułOrazAutora;
DELIMITER //
CREATE PROCEDURE dotowarujKsiążkęMającTutułOrazAutora(tutułt varchar (30), imięt varchar (30), nazwiskot varchar (30), liczbat int)
BEGIN

	-- to samo to procedura wyżej ale wyciąga ISBN z tytułu i autora
	UPDATE Dotowarowanie 
    SET Dotowarowanie.liczba = Dotowarowanie.liczba + liczbat
    WHERE Dotowarowanie.ISBN = (SELECT k.ISBN 
								FROM `Książki` as k JOIN `Autorzy` as a
                                ON (a.ID = k.autor)
                                WHERE a.imię = imięt
                                AND a.nazwisko = nazwiskot
                                AND k.tytuł = tytułt);
END //
DELIMITER ;

DROP TRIGGER IF EXISTS zmianaStatusuDostawyZmieniaLiczbeKsiążek;
DELIMITER //
CREATE TRIGGER zmianaStatusuDostawyZmieniaLiczbeKsiążek
AFTER UPDATE ON Dostawy
FOR EACH ROW 
BEGIN
	IF(NEW.status = 'Dostarczono')
    THEN 
    UPDATE `Książki` 
		SET `Książki`.liczba = `Książki`.liczba + `DotowarowanieHistoria`.liczba
		WHERE `Książki`.ISBN = `DotowarowanieHistoria`.ISBN 
			AND `Dostawy`.ID = `DotowarowanieHistoria`.ID;
	END IF;
END//
DELIMITER ;