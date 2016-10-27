-- Création de la table qui gère les information sur les groupes de personnes
CREATE TABLE JEE_Group (
	idG INTEGER NOT NULL AUTO_INCREMENT,
	groupName VARCHAR(30) UNIQUE NOT NULL,
	CONSTRAINT pk_JEE_Group_idG PRIMARY KEY (idG),
	CONSTRAINT nn_JEE_Group_groupName CHECK (groupName IS NOT NULL)
);

-- Création de la table qui gère les informations sur une personne
CREATE TABLE JEE_Person (
	idP INTEGER NOT NULL AUTO_INCREMENT,
	lastName VARCHAR(20) NOT NULL,
	firstName VARCHAR (20) NOT NULL,
	email VARCHAR (30) UNIQUE NOT NULL,
	homePage VARCHAR(30) UNIQUE,
	birthDate DATE NOT NULL,
	passWordG VARCHAR(70) NOT NULL,
	idG INTEGER,
	CONSTRAINT pk_JEE_Person_idP PRIMARY KEY (idP),
	CONSTRAINT fk_JEE_Person_idG_JEE_Group FOREIGN KEY (idG)
		REFERENCES JEE_Group (idG)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);