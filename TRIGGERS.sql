USE `BookstorCZ`;

-- TRIGGER PRZED WSTAWIENIEM KLIENTA CZY JUZ NIE ISTNIEJE
DROP TRIGGER IF EXISTS sprawdzCzyNowyCzłowiek;
DELIMITER //
CREATE TRIGGER sprawdzCzyNowyCzłowiek
BEFORE INSERT ON Ludzie
FOR EACH ROW 
BEGIN
IF EXISTS(  SELECT Ludzie.ID
			FROM Ludzie
			WHERE Ludzie.imię = NEW.imię) THEN
			
            IF EXISTS(  SELECT Ludzie.ID
						FROM Ludzie
						WHERE Ludzie.nazwisko = NEW.nazwisko) THEN
			CALL `'Podany człowiek istnieje'`;
			END IF;
END IF;
END//
DELIMITER ;

-- TRIGGER PRZED WSTAWIENIEM KSIĄŻKI CZY JUŻ NIE ISTNIEJE
DROP TRIGGER IF EXISTS sprawdzCzyNowaKsiazka;
DELIMITER //
CREATE TRIGGER sprawdzCzyNowaKsiazka
BEFORE INSERT ON Książki
FOR EACH ROW 
BEGIN
IF EXISTS(  SELECT Książki.ISBN
			FROM Książki
			WHERE Książki.ISBN = NEW.ISBN) THEN
			
	CALL `'Podana książka istnieje'`;
			
END IF;
END//
DELIMITER ;

-- TRIGGER PRZED WSTAWIENIEM DOSTAWCY CZY JUZ NIE ISTNIEJE
DROP TRIGGER IF EXISTS sprawdzCzyNowyDostawca;
DELIMITER //
CREATE TRIGGER sprawdzCzyNowyDostawca
BEFORE INSERT ON Dostawcy
FOR EACH ROW 
BEGIN
IF EXISTS(  SELECT Dostawcy.NIP
			FROM Dostawcy
			WHERE Dostawcy.nazwaFirmy = NEW.nazwaFirmy) THEN
			
	CALL `'Podany dostawca istnieje'`;
END IF;
END//
DELIMITER ;

-- TRIGGER PO USUNIECIU UŻYTKOWNIKA USUN JEGO ADRES
DROP TRIGGER IF EXISTS usunAdres;
DELIMITER //
CREATE TRIGGER usunAdres
AFTER DELETE ON Ludzie
FOR EACH ROW
BEGIN
	DELETE FROM Adresy WHERE Adresy.ID = OLD.Ludzie.ID;
END;//
DELIMITER ;


-- TRIGGER PO USUNIECIU DOSTAWCY USUN JEGO ADRES
DELIMITER ;
DROP TRIGGER IF EXISTS usunAdresDostawcy;
DELIMITER //
CREATE TRIGGER usunAdresDostawcy
AFTER DELETE ON Dostawcy
FOR EACH ROW
BEGIN
	DELETE FROM AdresyDostawcy WHERE AdresyDostawcy.ID = OLD.Dostawcy.ID;
END;//
DELIMITER ;

-- TRIGGER PRZED WSTAWIENIEM Autora CZY JUZ NIE ISTNIEJE
DROP TRIGGER IF EXISTS sprawdzCzyNowyAutor;
DELIMITER //
CREATE TRIGGER sprawdzCzyNowyAutor
BEFORE INSERT ON Autorzy
FOR EACH ROW 
BEGIN
IF EXISTS(  SELECT Autorzy.ID
			FROM Autorzy
			WHERE Autorzy.imię = NEW.imię) THEN
			
            IF EXISTS(  SELECT Autorzy.ID
						FROM Autorzy
						WHERE Autorzy.nazwisko = NEW.nazwisko) THEN
			CALL `'Podany Autor istnieje'`;
			END IF;
END IF;
END//
DELIMITER ;

-- TRIGGER PRZED WSTAWIENIEM Działu CZY JUZ NIE ISTNIEJE
DROP TRIGGER IF EXISTS sprawdzCzyNowyDzial;
DELIMITER //
CREATE TRIGGER sprawdzCzyNowyDzial
BEFORE INSERT ON Działy
FOR EACH ROW 
BEGIN
IF EXISTS(  SELECT Działy.ID
			FROM Działy
			WHERE Działy.nazwa = NEW.nazwa) THEN
			CALL `'Podany Dział istnieje'`;
END IF;
END//
DELIMITER ;

-- ----------------------------------------------------
-- TO DO / FIX

-- TRIGGER DODAJACY AUTORA GDY POJAWIA SIE KSIAZKA
DROP TRIGGER IF EXISTS dodajAutoraZksiazkami;
DELIMITER //
CREATE TRIGGER dodajAutoraZksiazkami
AFTER INSERT ON Książki
FOR EACH ROW 
BEGIN
-- PORÓWNUJ PO IMIĘ NAZWISKO W AUTOR A NIE ID W KSIĄŻKI
-- POWYŻEJ MASZ W WYWALAJ AUTORA BEZ KSIĄŻEK MNIEJ WIECEJ TO
IF (select count(*) from Książki where Książki.autor = @autor) != 0 then
		IF NOT EXISTS(select id from Autorzy where Autorzy.id = @autor) then
        INSERT INTO `Autorzy` VALUES (0,imie,nazwisko);
        END IF;
END IF;
END//
DELIMITER ;

-- TRIGGER ZMNIEJSZAJĄCY LICZBĘ KSIĄŻEK NA PODSTAWIE ZAMOWIONYCH KSIĄŻEK
DROP TRIGGER IF EXISTS liczbaKsiążek1;
DELIMITER //
CREATE TRIGGER liczbaKsiążek1
AFTER INSERT ON ZamówioneKsiążki
FOR EACH ROW 
BEGIN
	-- UŻWAAJ ISTNIEJĄCEGO KODU
    -- MASZ PRZECIEŻ updateKomórka
	UPDATE Książki(ISBN, autor, tytuł, dział, (OLD.liczba - @liczba), wydawnictwo, rokWydania, cena, opis)
END//
DELIMITER ;	

-- TRIGGER ZWIĘKSZAJĄCY LICZBĘ KSIĄŻEK NA PODSTAWIE DOTOWAROWANIA
DROP TRIGGER IF EXISTS liczbaKsiążek2;
DELIMITER //
CREATE TRIGGER liczbaKsiążek2
AFTER INSERT ON Dotowarowanie
FOR EACH ROW 
BEGIN
-- to samo co powyżej używaj update który istnieje po to isnieje właśnie
-- miełąo Ci to ułatwić robotę a nie utrudnić
UPDATE Książki(ISBN, autor, tytuł, dział, (OLD.liczba + @liczba), wydawnictwo, rokWydania, cena, opis)

END//
DELIMITER ;	

-- TRIGGER WYWALAJACY AUTORA BEZ KSIAZEK
DROP TRIGGER IF EXISTS usunAutoraBezKsiazek;
DELIMITER //
CREATE TRIGGER usunAutoraBezKsiazek
AFTER DELETE ON Książki
FOR EACH ROW 
BEGIN
DECLARE IDpom INT;
IF (select count(*) from Książki where Książki.nazwisko = @nazwisko) = 0 then
		-- tutaj tak samo if na imię
        
        -- jakoś wiciągasz id z tego wiersza
        IDpom=(SELECT ID FROM Autorzy Where OLD.nazwisko=@nazwisko);
        -- używasz istniejącego wywalacza wiersza
		CALL usunWiersz(ID, `Autorzy`);
    END IF;
END//
DELIMITER ;

-- TRGGER USUWAJĄCY DZIAŁ DO KTÓREGO NIE NALEŻĄ ŻADNE KSIĄŻKI

-- TRIGGER DODAJACY DZIAŁ JESLI NIE ISTNIEJE
