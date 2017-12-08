-- DATABASE
DROP DATABASE IF EXISTS `BookstorCZ`;
CREATE DATABASE IF NOT EXISTS `BookstorCZ`;
USE `BookstorCZ`;

-- AUTORZY
CREATE TABLE IF NOT EXISTS `Autorzy` (
  `ID` INT,
  `imię` VARCHAR(30),
  `nazwisko` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- KSIĄŻKI
CREATE TABLE IF NOT EXISTS `Książki` (
  `ISBN` VARCHAR(30),
  `tytuł` VARCHAR(30),
  `autor` INT,
  `dział` VARCHAR(30),
  `liczba` INT,
  `wydawnictwo` VARCHAR(30),
  `rokWydania` INT,
  `cena` INT,
  `opis` VARCHAR(30),
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (`autor`) REFERENCES `Autorzy` (`ID`)
);

-- DZIAŁY
CREATE TABLE IF NOT EXISTS `Działy` (
  `ID` INT,
  `nazwa` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- DZIAŁYPOM
CREATE TABLE IF NOT EXISTS `DziałyPom` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(30),
  `IDdział` INT,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ISBN`) REFERENCES `Książki` (`ISBN`),
  FOREIGN KEY (`IDdział`) REFERENCES `Działy` (`ID`)
);

-- ADRESY KLIENCI
CREATE TABLE IF NOT EXISTS `AdresyKlienci` (
  `ID` INT,
  `ulica` VARCHAR(30),
  `numer lokalu` VARCHAR(30),
  `kodPocztowy` VARCHAR(30),
  `miejscowość` VARCHAR(30),
  `województwo` VARCHAR(30),
  `kraj` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- KLIENCI
CREATE TABLE IF NOT EXISTS `Klienci` (
  `ID` INT,
  `imię` VARCHAR(30),
  `nazwisko` VARCHAR(30), 
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `login` VARCHAR(30),
  `hasło` VARCHAR(30),
  PRIMARY KEY (`ID`), 
  FOREIGN KEY (`adres`) REFERENCES `AdresyKlienci` (`ID`)
);

-- ZAMÓWIENIA
CREATE TABLE IF NOT EXISTS `Zamówienia` (
  `ID` INT,
  `IDzamawiającego` INT, 
  `dataZamówienia` DATE,
  `status` ENUM('Złożone', 'Wysłane', 'Dostarczone'),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDzamawiającego`) REFERENCES `Klienci` (`ID`)
);

-- ZAMÓWIONE KSIĄŻKI
CREATE TABLE IF NOT EXISTS `ZamówioneKsiążki`(
  `ID` INT NOT NULL AUTO_INCREMENT,
  `ISBN` VARCHAR(30),
  `IDzamówienia` INT,
  `liczba` INT,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDzamówienia`) REFERENCES `Zamówienia` (`ID`),
  FOREIGN KEY (`ISBN`) REFERENCES `Książki` (`ISBN`)
);

-- ADRESY PRACOWNICY
CREATE TABLE IF NOT EXISTS `AdresyPracownicy` (
  `ID` INT,
  `ulica` VARCHAR(30),
  `numer lokalu` VARCHAR(30),
  `kodPocztowy` VARCHAR(30),
  `miejscowość` VARCHAR(30),
  `województwo` VARCHAR(30),
  `kraj` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- PRACOWNICY
CREATE TABLE IF NOT EXISTS `Pracownicy` (
  `ID` INT,
  `imię` VARCHAR(30),
  `nazwisko` VARCHAR(30), 
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `login` VARCHAR(30),
  `hasło` VARCHAR(30),
  PRIMARY KEY (`ID`), 
  FOREIGN KEY (`adres`) REFERENCES `AdresyPracownicy` (`ID`)
);

-- DOSTAWCY ADRESY
CREATE TABLE IF NOT EXISTS `AdresyDostawcy` (
  `ID` INT,
  `ulica` VARCHAR(30),
  `numer lokalu` VARCHAR(30),
  `kodPocztowy` VARCHAR(30),
  `miejscowość` VARCHAR(30),
  `województwo` VARCHAR(30),
  `kraj` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- DOSTAWCY
CREATE TABLE IF NOT EXISTS `Dostawcy` (
  `NIP` VARCHAR(30),
  `nazwaFirmy` VARCHAR(30),
  `imięWłaściciela` VARCHAR(30),
  `nazwiskoWłaściciela` VARCHAR(30), 
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `nrKonta` VARCHAR(30),
  PRIMARY KEY (`NIP`), 
  FOREIGN KEY (`adres`) REFERENCES `AdresyDostawcy` (`ID`)
);

-- DOSTAWY
CREATE TABLE IF NOT EXISTS `Dostawy` (
  `ID` INT,
  `NIP` VARCHAR(30),
  `nrFaktury` VARCHAR(30),
  `dataDostawy` DATE,
  `status` ENUM('Złożono', 'Wysłano', 'Dostarczono'),
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
  FOREIGN KEY (`ISBN`) REFERENCES `Książki` (`ISBN`),
  FOREIGN KEY (`IDdostawy`) REFERENCES `Dostawy` (`ID`)
);