-- Creation of producton database
CREATE DATABASE yearbook_app_db;
USE yearbook_app_db;

-- Creation of table group
CREATE TABLE yearbook_group(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) UNIQUE NOT NULL,
	CONSTRAINT yearbook_group_pk PRIMARY KEY (id)
) ENGINE = INNODB CHARACTER SET = utf8;


-- Creation of table person
CREATE TABLE yearbook_person (
	id BIGINT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR (30) NOT NULL,
	lastName VARCHAR(30) NOT NULL,
	email VARCHAR (30) UNIQUE NOT NULL,
	homePage VARCHAR(30) UNIQUE,
	birthDate DATE NOT NULL,
	pwd VARCHAR(70) NOT NULL,
	idG BIGINT NOT NULL,
	CONSTRAINT  pk_yearbook_person PRIMARY KEY (id)
)ENGINE = INNODB CHARACTER SET = utf8;

ALTER TABLE yearbook_person add CONSTRAINT fk_yearbook_person_group FOREIGN KEY (idG)
		REFERENCES yearbook_group(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE;


-- Creation of test database
CREATE DATABASE yearbook_app_db_test;
USE yearbook_app_db_test;

-- Creation of table group
CREATE TABLE yearbook_group(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(30) UNIQUE NOT NULL,
	CONSTRAINT yearbook_group_pk PRIMARY KEY (id)
) ENGINE = INNODB CHARACTER SET = utf8;


-- Creation of table person
CREATE TABLE yearbook_person (
	id BIGINT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR (30) NOT NULL,
	lastName VARCHAR(30) NOT NULL,
	email VARCHAR (30) UNIQUE NOT NULL,
	homePage VARCHAR(30) UNIQUE,
	birthDate DATE NOT NULL,
	pwd VARCHAR(70) NOT NULL,
	idG BIGINT NOT NULL,
	CONSTRAINT  pk_yearbook_person PRIMARY KEY (id)
)ENGINE = INNODB CHARACTER SET = utf8;

ALTER TABLE yearbook_person add CONSTRAINT fk_yearbook_person_group FOREIGN KEY (idG)
		REFERENCES yearbook_group(id)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- User creation
CREATE USER yearbook_app IDENTIFIED BY 'yearbook_app';
GRANT SELECT,INSERT,UPDATE,DELETE ON yearbook_app_db.* TO yearbook_app;
GRANT SELECT,INSERT,UPDATE,DELETE ON yearbook_app_db_test.* TO yearbook_app;