-- DATABASE
DROP DATABASE IF EXISTS `BookstorCZ`;
CREATE DATABASE IF NOT EXISTS `BookstorCZ`;
USE `BookstorCZ`;

-- DZIAŁY
CREATE TABLE IF NOT EXISTS `Działy` (
  `ID` INT,
  `nazwa` VARCHAR(30),
  PRIMARY KEY (`ID`)
);

-- DZIAŁYPOM
CREATE TABLE IF NOT EXISTS `DziałyPom` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `IDdział` INT,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDdział`) REFERENCES `Działy` (`ID`)
);

-- AUTORZY
CREATE TABLE IF NOT EXISTS `Autorzy` (
  `ID` INT,
  `imię` VARCHAR(30),
  `nazwisko` VARCHAR(30),
  PRIMARY KEY (`ID`)
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
  `imię` VARCHAR(30),
  `nazwisko` VARCHAR(30), 
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `login` VARCHAR(30),
  `hasło` VARCHAR(30),
  PRIMARY KEY (`mail`), 
  FOREIGN KEY (`adres`) REFERENCES `AdresyKlienci` (`ID`)
);

-- ZAMÓWIENIA
CREATE TABLE IF NOT EXISTS `Zamówienia` (
  `ID` INT,
  `IDzamawiającego` varchar (30), 
  `dataZamówienia` DATE,
  `statusZamówienia` ENUM('Złożone', 'Wysłane', 'Dostarczone'),
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDzamawiającego`) REFERENCES `Klienci` (`mail`)
);

-- ZAMÓWIONE KSIĄŻKI
CREATE TABLE IF NOT EXISTS `ZamówioneKsiążki`(
  `ISBN` VARCHAR(30),
  `liczba` INT,
  PRIMARY KEY (`ISBN`)
);

CREATE TABLE IF NOT EXISTS `ZamówioneKsiążkiHistoria`(
  `ISBN` VARCHAR(30),
  `IDzamówienia` INT,
  `liczba` INT,
  FOREIGN KEY (`IDzamówienia`) REFERENCES `Zamówienia` (`ID`)
);


-- DOSTAWCY ADRESY
CREATE TABLE IF NOT EXISTS `AdresyDostawcy` (
  `ID` INT,
  `ulica` VARCHAR(30),
  `numerLokalu` VARCHAR(30),
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

-- DOTOWAROWANIE HISTORIA sluzy zobaczeniu ile książek było zamówionych przy jakiej dostawie
-- nie dajemy primary key ISBN bo to by uniemożliwiło wstawianie ksiązek wiele razy, a tego byśmy nie chcieli
CREATE TABLE IF NOT EXISTS `DotowarowanieHistoria` (
  `ISBN` VARCHAR(30),
  `IDdostawy` INT,
  `liczba` INT,
  FOREIGN KEY (`IDdostawy`) REFERENCES `Dostawy` (`ID`)
);

-- DOTOWAROWANIE jest tableja pomocnicza 
CREATE TABLE IF NOT EXISTS `Dotowarowanie` (
  `ISBN` VARCHAR(30),
  `liczba` INT,
  PRIMARY KEY (`ISBN`)
);

-- KSIĄŻKI
CREATE TABLE IF NOT EXISTS `Książki` (
  `ISBN` VARCHAR(30),
  `tytuł` VARCHAR(30),
  `autor` INT,
  `dział` int,
  `liczba` INT,
  `wydawnictwo` VARCHAR(30),
  `rokWydania` INT,
  `cena` INT,
  `opis` VARCHAR(30),
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (`autor`) REFERENCES `Autorzy` (`ID`),
  FOREIGN KEY (`ISBN`) references `Dotowarowanie` (`ISBN`),
  FOREIGN KEY (`ISBN`) references `ZamówioneKsiążki` (`ISBN`),
  FOREIGN KEY (`dział`) references `DziałyPom` (`ID`)
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
  `imię` VARCHAR(30),
  `nazwisko` VARCHAR(30),
  `stanowisko` VARCHAR(30), 
  `adres` INT,
  `telefon` VARCHAR(30),
  `mail` VARCHAR(30),
  `login` VARCHAR(30),
  `hasło` VARCHAR(30),
  PRIMARY KEY (`mail`), 
  FOREIGN KEY (`adres`) REFERENCES `AdresyPracownicy` (`ID`)
);



