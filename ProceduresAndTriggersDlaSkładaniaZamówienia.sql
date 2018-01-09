


-- w tabeli zamówienie jest pole idzamawiajacego, nie wiem jak je pozyskać, w tej chwili wpisuje mu maila
-- bo skąd ma znać swoje id jak jest autoincrement xd
DROP PROCEDURE IF EXISTS złóżZamówienie;
DELIMITER //
CREATE PROCEDURE złóżZamówienie(email VARCHAR(30))
BEGIN
	-- zamiast tworzyc kolejne wiersze w tej tabeli można rozwiązać to tak, ze dodajemy nowe kolumny
    -- kazda kolumna bedzie reprezentowac kolejne zamowienie 
	INSERT INTO ZamówioneKsiążkiHistoria
	SELECT * FROM ZamówioneKsiążki
	WHERE ZamówioneKsiążki.liczba > 0
    AND Klienci.mail = email;
	
    INSERT Zamówienia VALUES (0, NIP, nrFaktury, NOW(), 'Złożono'); 
    
	UPDATE ZamówioneKsiążki
	SET ZamówioneKsiążki.liczba = 0
    -- nie wiem czy on to ogarnie, czy to dobrze napisane xd
    WHERE ZamówioneKsiążki.ID = (SELECT z.ID
								FROM Zamówienia as z JOIN Klienci as k
                                ON (z.IDzamawiającego=k.mail)
                                WHERE k.mail=email);
                                
                                
	UPDATE `Książki` 
		SET `Książki`.liczba = `Książki`.liczba + `ZamówioneKsiążkiHistoria`.liczba
		WHERE `Książki`.ISBN = `ZamówioneKsiążkiHistoria`.ISBN 
			AND `Dostawy`.ID = `ZamówioneKsiążkiHistoria`.ID;
END //
DELIMITER ;


-- to powinien móc zrobić tylko pracownik, zabraniamy klientowi
DROP PROCEDURE IF EXISTS zmieńStatusZamówienia;
DELIMITER //
CREATE PROCEDURE zmieńStatusZamówienia(ID int, stus varchar(30))
BEGIN
	IF(stus = 'Złożono' || stus = 'Wysłano' || stus = 'Dostarczono')
    THEN 
    UPDATE Zamówienia
    SET Zamówienia.status = stus
    WHERE Zamówienia.ID = ID;
    
    ELSE SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong status name';
    END IF;
END //
DELIMITER ;



-- nie wiem jak to ogarniemy potem, że przy kliknięciu w książkę zamówi, zakładam że weźmiemy ISBN
-- daje możliwość zamówienia klientowi większej liczby książek, można to wywalić i założyć ze zamawia 1
DROP PROCEDURE IF EXISTS wrzućKsiążkęDoKoszyka;
DELIMITER //
CREATE PROCEDURE wrzućKsiążkęDoKoszyka(ISBNt varchar (30), liczbat int)
BEGIN
	UPDATE Zamówienia 
    SET Zamówienia.liczba = Dotowarowanie.liczba + liczbat
    WHERE Zamówienia.ISBN = ISBNt;
END //
DELIMITER ;

-- ---------------------------------------------------------------------------------------------------
-- to samo dla innej tabeli

-- w tabeli zamówienie jest pole idzamawiajacego, nie wiem jak je pozyskać, w tej chwili wpisuje mu maila
-- bo skąd ma znać swoje id jak jest autoincrement xd
DROP PROCEDURE IF EXISTS złóżZamówienie;
DELIMITER //
CREATE PROCEDURE złóżZamówienie(email VARCHAR(30))
BEGIN
    -- zamiast tworzyc kolejne wiersze w tej tabeli można rozwiązać to tak, ze dodajemy nowe kolumny
    -- kazda kolumna bedzie reprezentowac kolejne zamowienie 
    INSERT INTO ZamówioneKsiążkiHistoria
    SELECT * FROM ZamówioneKsiążki
    WHERE ZamówioneKsiążki.liczba > 0
    AND Klienci.mail = email;
    
    INSERT Zamówienia VALUES (0, NIP, nrFaktury, NOW(), 'Złożono'); 
    
    UPDATE ZamówioneKsiążki
    SET ZamówioneKsiążki.liczba = 0
    -- nie wiem czy on to ogarnie, czy to dobrze napisane xd
    WHERE ZamówioneKsiążki.ID = (SELECT z.ID
                                FROM Zamówienia as z JOIN Klienci as k
                                ON (z.IDzamawiającego=k.mail)
                                WHERE k.mail=email);
                                
                                
    UPDATE `Książki` 
        SET `Książki`.liczba = `Książki`.liczba + `ZamówioneKsiążkiHistoria`.liczba
        WHERE `Książki`.ISBN = `ZamówioneKsiążkiHistoria`.ISBN 
            AND `Dostawy`.ID = `ZamówioneKsiążkiHistoria`.ID;
END //
DELIMITER ;


-- to powinien móc zrobić tylko pracownik, zabraniamy klientowi
DROP PROCEDURE IF EXISTS zmieńStatusZamówienia;
DELIMITER //
CREATE PROCEDURE zmieńStatusZamówienia(ID int, stus varchar(30))
BEGIN
    IF(stus = 'Złożono' || stus = 'Wysłano' || stus = 'Dostarczono')
    THEN 
    UPDATE Zamówienia
    SET Zamówienia.status = stus
    WHERE Zamówienia.ID = ID;
    
    ELSE SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong status name';
    END IF;
END //
DELIMITER ;

-- nie wiem jak to ogarniemy potem, że przy kliknięciu w książkę zamówi, zakładam że weźmiemy ISBN
-- daje możliwość zamówienia klientowi większej liczby książek, można to wywalić i założyć ze zamawia 1
DROP PROCEDURE IF EXISTS wrzućKsiążkęDoKoszyka;
DELIMITER //
CREATE PROCEDURE wrzućKsiążkęDoKoszyka(ISBNt varchar (30), liczbat int)
BEGIN
    UPDATE Zamówienia 
    SET Zamówienia.liczba = Dotowarowanie.liczba + liczbat
    WHERE Zamówienia.ISBN = ISBNt;
END //
DELIMITER ;



