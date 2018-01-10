-- DATABASE
DROP DATABASE IF EXISTS `BookstorCZ`;
CREATE DATABASE IF NOT EXISTS `BookstorCZ`;
USE `BookstorCZ`;

-- AUTORZY
CREATE TABLE IF NOT EXISTS `Autorzy` (
  `ID` INT AUTO_INCREMENT,
  `imie` VARCHAR(30),
  `nazwisko` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- KSIĄŻKI
CREATE TABLE IF NOT EXISTS `Ksiazki` (
  `ISBN` VARCHAR(30),
  `tytul` VARCHAR(30),
  `autor` INT,
  `dzial` VARCHAR(30),
  `liczba` INT,
  `wydawnictwo` VARCHAR(30),
  `rokWydania` INT,
  `cena` INT,
  `opis` VARCHAR(30),
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (`autor`) REFERENCES `Autorzy` (`ID`)
);

-- DZIAŁY
CREATE TABLE IF NOT EXISTS `Dzialy` (
  `ID` INT AUTO_INCREMENT,
  `nazwa` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- DZIAŁYPOM
CREATE TABLE IF NOT EXISTS `DzialyPom` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(30),
  `IDdzial` INT,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ISBN`) REFERENCES `Ksiazki` (`ISBN`),
  FOREIGN KEY (`IDdzial`) REFERENCES `Dzialy` (`ID`)
);

-- ADRESY
CREATE TABLE IF NOT EXISTS `Adresy` (
  `ID` INT AUTO_INCREMENT,
  `ulica` VARCHAR(30),
  `numerLokalu` VARCHAR(30),
  `kodPocztowy` VARCHAR(30),
  `miejscowosc` VARCHAR(30),
  `wojewodztwo` VARCHAR(30),
  `kraj` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- LUDZIE
CREATE TABLE IF NOT EXISTS `Ludzie` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(30),
  `nazwisko` VARCHAR(30),
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `login` VARCHAR(30),
  `haslo` VARCHAR(30),
  `stanowisko` enum('Klient', 'Pracownik', 'Admin'), -- stanowisko = stanowisko pracownika lub klient
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`adres`) REFERENCES `Adresy` (`ID`)
);

-- ZAMÓWIENIA
CREATE TABLE IF NOT EXISTS `Zamowienia` (
  `ID` INT AUTO_INCREMENT,
  `IDzamawiajacego` INT,
  `dataZamowienia` DATE,
  `statusZamowienia` ENUM('Zlozone', 'Wyslane', 'Dostarczone'),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDzamawiajacego`) REFERENCES `Ludzie` (`ID`)
);

-- ZAMÓWIONE KSIĄŻKI
CREATE TABLE IF NOT EXISTS `ZamowioneKsiazki`(
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(30),
  `IDzamowienia` INT,
  `liczba` INT,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDzamowienia`) REFERENCES `Zamowienia` (`ID`),
  FOREIGN KEY (`ISBN`) REFERENCES `Ksiazki` (`ISBN`)
);

-- DOSTAWCY
CREATE TABLE IF NOT EXISTS `Dostawcy` (
  `NIP` VARCHAR(30),
  `nazwaFirmy` VARCHAR(30),
  `imieWlasciciela` VARCHAR(30),
  `nazwiskoWlasciciela` VARCHAR(30),
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `nrKonta` VARCHAR(30),
  PRIMARY KEY (`NIP`),
  FOREIGN KEY (`adres`) REFERENCES `Adresy` (`ID`)
);

-- DOSTAWY
CREATE TABLE IF NOT EXISTS `Dostawy` (
  `ID` INT AUTO_INCREMENT,
  `NIP` VARCHAR(30),
  `nrFaktury` VARCHAR(30),
  `dataDostawy` DATE,
  `status` ENUM('Zlozono', 'Wyslano', 'Dostarczono'),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`NIP`) REFERENCES `Dostawcy` (`NIP`)
);

-- DOTOWAROWANIE
CREATE TABLE IF NOT EXISTS `Dotowarowanie` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(30),
  `IDdostawy` INT,
  `liczba` INT,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ISBN`) REFERENCES `Ksiazki` (`ISBN`),
  FOREIGN KEY (`IDdostawy`) REFERENCES `Dostawy` (`ID`)
);