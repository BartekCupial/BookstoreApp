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
DROP PROCEDURE IF EXISTS dodajDotowanrowanie;
DELIMITER //
CREATE PROCEDURE dodajDotowarowanie(ISBN VARCHAR(30), ID INT, IDdostawy INT, liczba INT)
BEGIN
    INSERT INTO `Dotowarowanie` VALUES (0,ISBN, IDdostawy, liczba);
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
