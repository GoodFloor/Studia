--zad 1
CREATE DATABASE praca;
USE praca;
CREATE TABLE Ludzie(
    czlowiek_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    PESEL char(11) UNIQUE,
    imie varchar(30) NOT NULL,
    nazwisko varchar(30) NOT NULL,
    data_urodzenia date NOT NULL,
    plec enum('K', 'M') NOT NULL,
    CONSTRAINT pesel_numeric CHECK(
        (0 <= SUBSTRING(PESEL, 1, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 2, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 3, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 4, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 5, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 6, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 7, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 8, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 9, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 10, 1) <= 9) AND
        (0 <= SUBSTRING(PESEL, 11, 1) <= 9)
    ),
    CONSTRAINT pesel_plec CHECK(
        (plec = 'K' AND (SUBSTRING(PESEL, 10, 1) % 2) = 0) OR
        (plec = 'M' AND (SUBSTRING(PESEL, 10, 1) % 2) = 1)
    ),
    CONSTRAINT pesel_checksum CHECK(
        ((10 - ((SUBSTRING(PESEL, 1, 1) * 1 + SUBSTRING(PESEL, 2, 1) * 3 
               + SUBSTRING(PESEL, 3, 1) * 7 + SUBSTRING(PESEL, 4, 1) * 9 
               + SUBSTRING(PESEL, 5, 1) * 1 + SUBSTRING(PESEL, 6, 1) * 3 
               + SUBSTRING(PESEL, 7, 1) * 7 + SUBSTRING(PESEL, 8, 1) * 9 
              + SUBSTRING(PESEL, 9, 1) * 1 + SUBSTRING(PESEL, 10, 1) * 3
            ) % 10)) % 10) = SUBSTRING(PESEL, 11, 1)
    ),
    CONSTRAINT pesel_year CHECK(
        SUBSTRING(PESEL, 1, 2) = (YEAR(data_urodzenia) % 100)
    ),
    CONSTRAINT pesel_month CHECK(
        (YEAR(data_urodzenia) >= 1800 AND YEAR(data_urodzenia) <= 1899 AND SUBSTRING(PESEL, 3, 2) = (MONTH(data_urodzenia) + 80)) OR 
        (YEAR(data_urodzenia) >= 1900 AND YEAR(data_urodzenia) <= 1999 AND SUBSTRING(PESEL, 3, 2) = (MONTH(data_urodzenia))) OR 
        (YEAR(data_urodzenia) >= 2000 AND YEAR(data_urodzenia) <= 2099 AND SUBSTRING(PESEL, 3, 2) = (MONTH(data_urodzenia) + 20)) OR 
        (YEAR(data_urodzenia) >= 2100 AND YEAR(data_urodzenia) <= 2199 AND SUBSTRING(PESEL, 3, 2) = (MONTH(data_urodzenia) + 40)) OR 
        (YEAR(data_urodzenia) >= 2200 AND YEAR(data_urodzenia) <= 2299 AND SUBSTRING(PESEL, 3, 2) = (MONTH(data_urodzenia) + 60))
    ),
    CONSTRAINT pesel_day CHECK(
        SUBSTRING(PESEL, 5, 2) = DAY(data_urodzenia)
    )
);
CREATE TABLE Zawody (
    zawod_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nazwa varchar(50) NOT NULL,
    pensja_min float CHECK(pensja_min >= 0),
    pensja_max float CHECK(pensja_max >= pensja_min)
);
CREATE TABLE Pracownicy (
    czlowiek_id int NOT NULL,
    zawod_id int NOT NULL,
    pensja float NOT NULL,
    FOREIGN KEY(czlowiek_id)
        REFERENCES Ludzie(czlowiek_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
    FOREIGN KEY(zawod_id)
        REFERENCES Zawody(zawod_id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

INSERT INTO Ludzie(PESEL, imie, nazwisko, data_urodzenia, plec) VALUES
	('11252160376', 'Krzysztof', 'Woźniak', '2011-05-21', 'M'), 
	('10210967110', 'Dominik', 'Nowak', '2010-01-09', 'M'), 
	('14220348778', 'Krzysztof', 'Kowalczyk', '2014-02-03', 'M'), 
	('00282106616', 'Dominik', 'Nowak', '2000-08-21', 'M'), 
	('63061054733', 'Dominik', 'Kowalczyk', '1963-06-10', 'M'), 
	('72090232018', 'Maciej', 'Machnik', '1972-09-02', 'M'), 
	('69082216631', 'Maciej', 'Kowalski', '1969-08-22', 'M'), 
	('77090532175', 'Krzysztof', 'Kowalski', '1977-09-05', 'M'), 
	('94042726255', 'Jan', 'Machnik', '1994-04-27', 'M'), 
	('90050216450', 'Maciej', 'Woźniak', '1990-05-02', 'M'), 
	('79031750330', 'Maciej', 'Woźniak', '1979-03-17', 'M'), 
	('91111528772', 'Dominik', 'Machnik', '1991-11-15', 'M'), 
	('80030755776', 'Maciej', 'Woźniak', '1980-03-07', 'M'), 
	('01222188736', 'Karol', 'Kowalczyk', '2001-02-21', 'M'), 
	('02260434717', 'Krzysztof', 'Machnik', '2002-06-04', 'M'), 
	('93092145478', 'Krzysztof', 'Kowalczyk', '1993-09-21', 'M'), 
	('00281067817', 'Maciej', 'Nowak', '2000-08-10', 'M'), 
	('96070534770', 'Dominik', 'Machnik', '1996-07-05', 'M'), 
	('90051150816', 'Krzysztof', 'Woźniak', '1990-05-11', 'M'), 
	('96091987135', 'Karol', 'Woźniak', '1996-09-19', 'M'), 
	('67060310850', 'Karol', 'Machnik', '1967-06-03', 'M'), 
	('99060306236', 'Maciej', 'Nowak', '1999-06-03', 'M'), 
	('68102512810', 'Karol', 'Machnik', '1968-10-25', 'M'), 
	('99101038638', 'Jan', 'Nowak', '1999-10-10', 'M'),
	('99032330234', 'Maciej', 'Krawczyk', '1999-03-23', 'M'),
	('37061911152', 'Dominik', 'Nowak', '1937-06-19', 'M'), 
	('55071418616', 'Karol', 'Kowalczyk', '1955-07-14', 'M'), 
	('13241867628', 'Julia', 'Roman', '2013-04-18', 'K'), 
	('07282740866', 'Julia', 'Roman', '2007-08-27', 'K'), 
	('66100761366', 'Karolina', 'Machnik', '1966-10-07', 'K'), 
	('98050752725', 'Michalina', 'Roman', '1998-05-07', 'K'), 
	('83092576569', 'Michalina', 'Roman', '1983-09-25', 'K'), 
	('63100844802', 'Anna', 'Machnik', '1963-10-08', 'K'), 
	('93100207143', 'Karolina', 'Roman', '1993-10-02', 'K'), 
	('93071414508', 'Michalina', 'Kowalska', '1993-07-14', 'K'), 
	('97012228106', 'Michalina', 'Nowak', '1997-01-22', 'K'), 
	('75031645108', 'Anna', 'Wiora', '1975-03-16', 'K'), 
	('76070882721', 'Zofia', 'Roman', '1976-07-08', 'K'), 
	('00222767161', 'Karolina', 'Wiora', '2000-02-27', 'K'), 
	('72062468005', 'Anna', 'Kowalska', '1972-06-24', 'K'), 
	('67102412328', 'Julia', 'Machnik', '1967-10-24', 'K'), 
	('94102368847', 'Zofia', 'Wiora', '1994-10-23', 'K'), 
	('00300243060', 'Zofia', 'Wiora', '2000-10-02', 'K'), 
	('90051312827', 'Anna', 'Machnik', '1990-05-13', 'K'), 
	('74082716548', 'Karolina', 'Wiora', '1974-08-27', 'K'), 
	('66032250143', 'Zofia', 'Kowalska', '1966-03-22', 'K'), 
	('72070821342', 'Zofia', 'Nowak', '1972-07-08', 'K'), 
	('74041888347', 'Michalina', 'Roman', '1974-04-18', 'K'), 
	('00220760449', 'Michalina', 'Nowak', '2000-02-07', 'K'), 
	('04282520544', 'Zofia', 'Nowak', '2004-08-25', 'K'), 
	('03262670066', 'Michalina', 'Kocurek', '2003-06-26', 'K'), 
	('75051247845', 'Aleksandra', 'Nowak', '1975-05-12', 'K'), 
	('61032253721', 'Michalina', 'Machnik', '1961-03-22', 'K'), 
	('33031522126', 'Anna', 'Kowalska', '1933-03-15', 'K'), 
	('34091336542', 'Michalina', 'Nowak', '1934-09-13', 'K');

INSERT INTO Zawody(nazwa, pensja_min, pensja_max) VALUES
    ('polityk', 3200, 50000),
    ('nauczyciel', 2700, 4300),
    ('lekarz', 3200, 23000),
    ('informatyk', 4100, 25000);

DELIMITER $$
CREATE OR REPLACE PROCEDURE giveJob ()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE id INT;
    DECLARE birthday DATE;
    DECLARE sex char(1);
    DECLARE job_id INT;
    DECLARE age FLOAT;
    DECLARE min_payment FLOAT;
    DECLARE max_payment FLOAT;
    DECLARE payment FLOAT;
    DECLARE people CURSOR FOR
        (SELECT czlowiek_id, data_urodzenia, plec FROM Ludzie);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN people;
    read_loop: LOOP
        FETCH people INTO id, birthday, sex;
        IF done 
        THEN 
            LEAVE read_loop;
        END IF;
        SELECT YEAR(CURDATE()) - YEAR(birthday) INTO age;
        IF age > 18
        THEN
            IF (age > 65 AND sex = 'M') OR (age > 60 AND sex = 'K')
            THEN
                SELECT zawod_id, pensja_min, pensja_max INTO job_id, min_payment, max_payment FROM Zawody WHERE nazwa<>'lekarz' ORDER BY RAND() LIMIT 1;
            ELSE
                SELECT zawod_id, pensja_min, pensja_max INTO job_id, min_payment, max_payment FROM Zawody ORDER BY RAND() LIMIT 1;
            END IF;
            SELECT FLOOR(RAND() * (max_payment * 100 - min_payment * 100 + 1) + min_payment * 100) / 100 INTO payment;
            INSERT INTO Pracownicy(czlowiek_id, zawod_id, pensja) VALUES (id, job_id, payment);
        END IF;
    END LOOP;
    CLOSE people;
END$$
DELIMITER ;
CALL giveJob;

--zad2
CREATE INDEX i_plec_imie ON Ludzie(plec, imie);
CREATE INDEX i_pensja ON Pracownicy(pensja);
SHOW INDEX FROM Ludzie;
SHOW INDEX FROM Pracownicy;
EXPLAIN SELECT imie, nazwisko FROM Ludzie 
    WHERE plec = 'K' AND imie LIKE '%A';
EXPLAIN SELECT imie, nazwisko FROM Ludzie 
    WHERE plec = 'K';
EXPLAIN SELECT imie, nazwisko FROM Ludzie 
    WHERE imie LIKE '%K';
EXPLAIN SELECT imie, nazwisko FROM Ludzie 
    JOIN Pracownicy ON Ludzie.czlowiek_id = Pracownicy.czlowiek_id 
    WHERE pensja < 2000;
EXPLAIN SELECT Ludzie.imie, Ludzie.nazwisko FROM Ludzie 
    JOIN Pracownicy ON Ludzie.czlowiek_id = Pracownicy.czlowiek_id 
    JOIN Zawody ON Pracownicy.zawod_id = Zawody.zawod_id
    WHERE Ludzie.plec = 'M' AND Pracownicy.pensja > 10000;

--zad3
DELIMITER $$
CREATE OR REPLACE PROCEDURE payRaise (job varchar(50))
BEGIN
    DECLARE id INT;
    DECLARE salary FLOAT;
    DECLARE max_payment FLOAT;
    DECLARE done INT DEFAULT FALSE;
    DECLARE paycheck CURSOR FOR
        (SELECT Pracownicy.pensja, Pracownicy.czlowiek_id, Zawody.pensja_max FROM Pracownicy 
        JOIN Zawody ON Pracownicy.zawod_id = Zawody.zawod_id
        WHERE Zawody.nazwa = job);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    SET autocommit = 0;
    START TRANSACTION;
    OPEN paycheck;
    read_loop: LOOP
        FETCH paycheck INTO salary, id, max_payment;
        IF done
        THEN
            LEAVE read_loop;
        END IF;
        IF salary * 1.05 > max_payment
        THEN
            ROLLBACK;
            SET done = TRUE;
        ELSE
            UPDATE Pracownicy SET pensja = salary * 1.05 WHERE czlowiek_id = id;
        END IF;
    END LOOP;
    COMMIT;
    CLOSE paycheck;
END$$
DELIMITER ;
CALL payRise('lekarz');

--zad5
$ mysqldump -u root praca > backup.sql
DROP DATABASE praca;
CREATE DATABASE praca;
$ mysql -u root praca < backup.sql

--zad6
A1_1_12:
SELECT * FROM employees WHERE last_name = "Smith" AND auth_tan = "3SL99A"; UPDATE employees SET Salary=99999 WHERE userid=37648;--"
A1_1_13
1'; DROP TABLE access_log;--







INSERT INTO Ludzie(PESEL, imie, nazwisko, data_urodzenia, plec) VALUES 
    ('91102052167', 'Jan', 'Kowalski', '2001-01-01', 'K');
SELECT * FROM Ludzie;
SELECT IF((UNIX_TIMESTAMP(CURDATE()) - UNIX_TIMESTAMP('2001-12-14')) > 567648000, 'Y', 'N');
SELECT CURDATE() - '0018-00-00';
((UNIX_TIMESTAMP(CURDATE()) - UNIX_TIMESTAMP(birthday)) / 31536000)
