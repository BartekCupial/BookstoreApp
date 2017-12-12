USE `BookstorCZ`;

-- DODAJ KLIENTA
DROP PROCEDURE IF EXISTS dodajKlienta;
DELIMITER //
CREATE PROCEDURE dodajKlienta(imie VARCHAR(30), nazwisko VARCHAR(30), adres INT, telefon VARCHAR(30), mail VARCHAR(30), login VARCHAR(30), haslo VARCHAR(30))
BEGIN
	INSERT INTO `Klienci` VALUES (0,imie,nazwisko,adres,telefon, mail, login, haslo);
END //
DELIMITER ;

-- DODAJ PRACOWNIKA
DROP PROCEDURE IF EXISTS dodajPracownika;
DELIMITER //
CREATE PROCEDURE dodajPracownika(imie VARCHAR(30), nazwisko VARCHAR(30), adres INT, telefon VARCHAR(30), mail VARCHAR(30), login VARCHAR(30), haslo VARCHAR(30))
BEGIN
	INSERT INTO `Pracownicy` VALUES (0,imie,nazwisko,adres,telefon, mail, login, haslo);
END //
DELIMITER ;

-- DODAJ DOSTAWCE
DROP PROCEDURE IF EXISTS dodajDostawce;
DELIMITER //
CREATE PROCEDURE dodajDostawce(NIP VARCHAR(30), nazwa VARCHAR(30), imieWlascicela varchar(30), nazwiskoWlasciciela VARCHAR(30), adres INT, telefon VARCHAR(30), mail VARCHAR(30), nrKonta VARCHAR(30))
BEGIN
	INSERT INTO `Dostawcy` VALUES (0,NIP,nazwa,imieWlasciciela,nazwiskoWlasciciela,adres,telefon, mail, nrKonta);
END //
DELIMITER ;

-- DODAJ ADRES
DROP PROCEDURE IF EXISTS dodajAdres;
DELIMITER //
CREATE PROCEDURE dodajAdres(ulica VARCHAR(30), numerLokalu varchar(30), kodPocztowy VARCHAR(30), miejscowosc VARCHAR(30), wojewodztwo VARCHAR(30))
BEGIN
    INSERT INTO `Adresy` VALUES (0,ulicaazwa,numerLokalu, kodPocztowy, miejscowosc, wojewodztwo);
END //
DELIMITER ;

-- DODAJ DOSTAWE
DROP PROCEDURE IF EXISTS dodajDostawe;
DELIMITER //
CREATE PROCEDURE dodajDostawe(NIP VARCHAR(30), nrFaktury VARCHAR(30), dataDostawy DATE, statusDostawy VARCHAR(30))
BEGIN
    INSERT INTO `Dostawy` VALUES (0,NIP, nrFaktury, dataDostawy, statusDostawy);
END //
DELIMITER ;

-- DODAJ ZAMOWIENIE
DROP PROCEDURE IF EXISTS dodajZamowienie;
DELIMITER //
CREATE PROCEDURE dodajZamowienie(ID INT, IDzamawiajacego INT, dataZamowienia DATE, statusZamowienia VARCHAR(30))
BEGIN
    INSERT INTO `Zamówienia` VALUES (ID,IDzamawiajacego, dataZamowienia, statusZamowienia);
END //
DELIMITER ;

-- DODAJ AUTORA
DROP PROCEDURE IF EXISTS dodajAutora;
DELIMITER //
CREATE PROCEDURE dodajAutora(imie VARCHAR(30), nazwisko VARCHAR(30))
BEGIN
	INSERT INTO `Autorzy` VALUES (0,imie,nazwisko);
END //
DELIMITER ;

-- DODAJ DZIAŁ
DROP PROCEDURE IF EXISTS dodajDzial;
DELIMITER //
CREATE PROCEDURE dodajDzial(nazwa VARCHAR(30))
BEGIN
	INSERT INTO `Działy` VALUES (0,nazwa);
END //
DELIMITER ;

-- DODAJ KSIĄŻKĘ
DROP PROCEDURE IF EXISTS dodajKsiążkę;
DELIMITER //
CREATE PROCEDURE dodajKsiążkę(ISBN varchar (30), tytuł varchar (30), autor int, dział varchar (30), liczba int, wydawnictwo varchar(30), rokWydania int, cena int, opis varchar (30))
BEGIN
	INSERT INTO `Książki` VALUES (ISBN, tytuł, autor, dział, liczba, wydawnictwo, rokWydania, cena, opis);
END //
DELIMITER ;




-- WYPEŁNIANIE TABELI ZAMOWIONE KSIAZKI
drop procedure if exists wypelnijZamowioneKsiazki;
DELIMITER $$
CREATE PROCEDURE wypelnijZamowioneKsiazki(in ISBN varchar(30), in liczba int, in IDzamówienia)
BEGIN
DECLARE EOS BOOLEAN DEFAULT FALSE;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET EOS = TRUE;

 -- tu on się wykona tylko raz ale zrobilem to tak, zeby cos bylo, potem wymyslimy jak to zrobic
 -- na razie jest jakis szablon
			cycle: LOOP
                IF EOS
				THEN LEAVE cycle;
				END IF;
                INSERT INTO `ZamówioneKsiążki` VALUES (0,ISBN, IDzamówienia, liczba);
			END LOOP cycle;
	   COMMIT;    
END$$
DELIMITER ;



drop procedure wypelnijDotowarowanie;
DELIMITER $$
CREATE PROCEDURE wypelnijDotowarowanie(in ISBN varchar(30), in liczba int, in IDdostawy int)
BEGIN
DECLARE EOS BOOLEAN DEFAULT FALSE;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET EOS = TRUE;

 -- tu on się wykona tylko raz ale zrobilem to tak, zeby cos bylo, potem wymyslimy jak to zrobic
 -- na razie jest jakis szablon
			cycle: LOOP
                IF EOS
				THEN LEAVE cycle;
				END IF;
                INSERT INTO `ZamówioneKsiążki` VALUES (0,ISBN, IDdostawy, liczba);
			END LOOP cycle;
	   COMMIT;    
END$$
DELIMITER ;

