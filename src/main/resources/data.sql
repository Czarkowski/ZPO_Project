-- Wstawianie ofert
INSERT INTO oferta (nazwa, data_utworzenia)
VALUES ('Oferta 1', CURRENT_TIMESTAMP),
       ('Oferta 2', CURRENT_TIMESTAMP),
       ('Oferta 3', CURRENT_TIMESTAMP),
       ('Oferta 4', CURRENT_TIMESTAMP),
       ('Oferta 5', CURRENT_TIMESTAMP),
       ('Oferta 6', CURRENT_TIMESTAMP),
       ('Oferta 7', CURRENT_TIMESTAMP),
       ('Oferta 8', CURRENT_TIMESTAMP),
       ('Oferta 9', CURRENT_TIMESTAMP),
       ('Oferta 10', CURRENT_TIMESTAMP);

-- Wstawianie cen dla ofert
INSERT INTO cena (wartosc, oferta_id, data_utworzenia)
VALUES (10.5, 1, CURRENT_TIMESTAMP),
       (15.0, 1, CURRENT_TIMESTAMP),
       (20.3, 2, CURRENT_TIMESTAMP),
       (12.7, 3, CURRENT_TIMESTAMP),
       (18.2, 4, CURRENT_TIMESTAMP),
       (14.8, 5, CURRENT_TIMESTAMP),
       (22.1, 6, CURRENT_TIMESTAMP),
       (17.3, 7, CURRENT_TIMESTAMP),
       (13.9, 8, CURRENT_TIMESTAMP),
       (19.6, 9, CURRENT_TIMESTAMP),
       (16.4, 10, CURRENT_TIMESTAMP);

-- Wstawianie opisów dla ofert
INSERT INTO opis (text, oferta_id, data_utworzenia)
VALUES ('Opis Oferty 1', 1, CURRENT_TIMESTAMP),
       ('Opis Oferty 2', 2, CURRENT_TIMESTAMP),
       ('Opis Oferty 3', 3, CURRENT_TIMESTAMP),
       ('Opis Oferty 4', 4, CURRENT_TIMESTAMP),
       ('Opis Oferty 5', 5, CURRENT_TIMESTAMP),
       ('Opis Oferty 6', 6, CURRENT_TIMESTAMP),
       ('Opis Oferty 7', 7, CURRENT_TIMESTAMP),
       ('Opis Oferty 8', 8, CURRENT_TIMESTAMP),
       ('Opis Oferty 9', 9, CURRENT_TIMESTAMP),
       ('Opis Oferty 10', 10, CURRENT_TIMESTAMP);

-- Inserting Pytania
INSERT INTO pytanie (tresc, uzytkownik, tytul, data_utworzenia) VALUES
('Pytanie 1', 'UzytkownikA', 'Tytul 1', CURRENT_TIMESTAMP),
('Pytanie 2', 'UzytkownikB', 'Tytul 2', CURRENT_TIMESTAMP),
('Pytanie 3', 'UzytkownikC', 'Tytul 3', CURRENT_TIMESTAMP),
('Pytanie 4', 'UzytkownikD', 'Tytul 4', CURRENT_TIMESTAMP),
('Pytanie 5', 'UzytkownikE', 'Tytul 5', CURRENT_TIMESTAMP);

-- Inserting Odpowiedzi
-- Odpowiedzi dla Pytania 1
INSERT INTO odpowiedz (tresc, uzytkownik, pytanie_id, data_utworzenia)
VALUES ('Odpowiedz 1-1', 'UzytkownikF', 1, CURRENT_TIMESTAMP),
       ('Odpowiedz 1-2', 'UzytkownikG', 1, CURRENT_TIMESTAMP),
       ('Odpowiedz 1-3', 'UzytkownikH', 1, CURRENT_TIMESTAMP);

-- Odpowiedzi dla Pytania 2
INSERT INTO odpowiedz (tresc, uzytkownik, pytanie_id, data_utworzenia)
VALUES ('Odpowiedz 2-1', 'UzytkownikI', 2, CURRENT_TIMESTAMP),
       ('Odpowiedz 2-2', 'UzytkownikJ', 2, CURRENT_TIMESTAMP);

-- Odpowiedzi dla Pytania 3
INSERT INTO odpowiedz (tresc, uzytkownik, pytanie_id, data_utworzenia)
VALUES ('Odpowiedz 3-1', 'UzytkownikK', 3, CURRENT_TIMESTAMP);

-- Odpowiedzi dla Pytania 4
INSERT INTO odpowiedz (tresc, uzytkownik, pytanie_id, data_utworzenia)
VALUES ('Odpowiedz 4-1', 'UzytkownikL', 4, CURRENT_TIMESTAMP),
       ('Odpowiedz 4-2', 'UzytkownikM', 4, CURRENT_TIMESTAMP),
       ('Odpowiedz 4-3', 'UzytkownikN', 4, CURRENT_TIMESTAMP);

-- Odpowiedzi dla Pytania 5
INSERT INTO odpowiedz (tresc, uzytkownik, pytanie_id, data_utworzenia)
VALUES ('Odpowiedz 5-1', 'UzytkownikO', 5, CURRENT_TIMESTAMP);

INSERT INTO zamowienie (imie, nazwisko, ulica, miejscowosc, kod_pocztowy, email, tel, data_utworzenia)
VALUES ('Jan', 'Kowalski', 'ul. Prosta 1', 'Warszawa', '00-001', 'jan.kowalski@example.com', '123456789', CURRENT_TIMESTAMP);

INSERT INTO pozycja (oferta_id, cena_id, opis_id, name, ilosc, zamowienie_id)
VALUES
    (1, 1, 1, 'Oferta 1', 1, LAST_INSERT_ID()),
    (2, 2, 2, 'Oferta 2', 3, LAST_INSERT_ID());
