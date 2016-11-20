
-- Switchs to yearbook_app_db_test database
USE yearbook_app_db_test

-- Inserts group 
INSERT INTO yearbook_group (id, name) VALUES
(1, 'M2 FED 2015/2016'),
(2, 'M2 RO 2015/2016'),
(3, 'M2 IF 2015/2016'),
(4, 'M2 RIM 2015/2016'),
(5, 'M2 SIS 2015/2016'),
(6, 'M2 ISL 2015/2016'),
(7, 'M2 FSI 2015/2016'),
(8, 'M2 GL 2015/2016'),
(9, 'M2 SIR 2015/2016');



-- Inserts peoples
INSERT INTO yearbook_person (lastName, firstName, email, homepage, birthDate, pwd, idG) VALUES 
('KONDI', 'Marie', 'kondimarie@gmail.com', 'www.kondi_marie.fr', STR_TO_DATE('2015/06/22','%Y/%m/%d'), PASSWORD('marie'), 1),
('CAMARA', 'Moussa', 'camaramoussa@gmail.com', 'www.camara_moussa.fr', STR_TO_DATE('1963/11/01','%Y/%m/%d'), PASSWORD('moussa'), 1),
('DIALLO', 'Aboubacar Sidy', 'sidediallo@yahoo.fr', 'www.diallo_aboubacar.fr', STR_TO_DATE('2016/03/01','%Y/%m/%d'), PASSWORD('aboubacar'), 1),
('INMON', 'Bill', 'bill.inmon@gmail.com', 'www.inmoncif.com/home/', STR_TO_DATE('1963/11/01','%Y/%m/%d'), PASSWORD('inmon'), 2),
('KIMBALL', 'Ralph', 'ralph.kimball@gmail.com', 'www.kimballgroup.com', STR_TO_DATE('1944/01/01','%Y/%m/%d'), PASSWORD('ralph'), 2),
('KABORE', 'Eliane', 'eliane.kabore@gmail.com', NULL, STR_TO_DATE('1997/10/11','%Y/%m/%d'), PASSWORD('eliane'), 3),
('YARBANGA', 'W. Emmanuel', 'emmanuel.yarbanga@yahoo.fr', NULL, STR_TO_DATE('1980/12/09','%Y/%m/%d'), PASSWORD('emmanuel'), 3),
('SMITH', 'Page', 'page.smith@yahoo.fr', 'www.smith.com', STR_TO_DATE('1983/07/19','%Y/%m/%d'), PASSWORD('smith'), 7);
