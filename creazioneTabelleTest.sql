DROP DATABASE IF EXISTS fantacalciounisaTest;
CREATE DATABASE fantacalciounisaTest;
use fantacalciounisaTest;



CREATE TABLE allenatore (
  Nome VARCHAR(50) NOT NULL,
  Cognome VARCHAR(50) NOT NULL,
  email VARCHAR(45) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`));
  
CREATE TABLE squadra (
    NomeSquadra VARCHAR(50) NOT NULL,
    Lega VARCHAR(50) NOT NULL,
    Logo VARCHAR(50) NOT NULL,
    Allenatore VARCHAR(50) NOT NULL,
    Punti INT NOT NULL,
    BudgetRimanente int NOT NULL,
    FOREIGN KEY (Allenatore)
        REFERENCES allenatore (Username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (NomeSquadra , Lega)
);

CREATE TABLE lega (
    NomeLega VARCHAR(50) NOT NULL,
    Logo VARCHAR(50) NOT NULL,
    MaxAllenatori INT NOT NULL,
    quotaMensile INT NOT NULL,
    budget INT NOT NULL,
    primoPosto INT NOT NULL,
    secondoPosto INT NOT NULL,
    terzoPosto INT NOT NULL,
    Presidente VARCHAR(50) NOT NULL,
    FOREIGN KEY (Presidente)
        REFERENCES allenatore (Username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (NomeLega)
);

CREATE TABLE invito (
    Allenatore VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    risposta BOOLEAN,
    FOREIGN KEY (Allenatore)
        REFERENCES allenatore (Username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (NomeLega)
        REFERENCES lega (NomeLega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (Allenatore , NomeLega)
);

CREATE TABLE asta (
    DataInizio VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    Ora VARCHAR(10) NOT NULL,
    DataFine VARCHAR(50) NOT NULL,
    FOREIGN KEY (NomeLega)
        REFERENCES lega (Nomelega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (DataInizio , NomeLega)
);

CREATE TABLE partita (
    Squadra1 VARCHAR(50) NOT NULL,
    Squadra2 VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    Goal1 INT,
    Goal2 INT,
    giornata INT NOT NULL,
    FOREIGN KEY (Squadra1 , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Squadra2 , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (NomeLega , Squadra1 , Squadra2 , Giornata)
);



CREATE TABLE giocatore (
    Id INT AUTO_INCREMENT NOT NULL,
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Ruolo VARCHAR(10) NOT NULL,
    SquadraReale VARCHAR(10) NOT NULL,
    Presenze INT NOT NULL,
    VotoMedio FLOAT NOT NULL,
    Goal INT NOT NULL,
    Assist INT NOT NULL,
    Ammonizioni INT NOT NULL,
    Espulsioni INT NOT NULL,
    RigoriSegnati INT NOT NULL,
    RigoriSbagliati INT NOT NULL,
    RigoriParati INT NOT NULL,
    prezzoBase INT NOT NULL,
    PRIMARY KEY (Id)
);


CREATE TABLE scambio (
    Giocatore1 INT NOT NULL,
    Giocatore2 INT NOT NULL,
    PrezzoOfferto FLOAT NOT NULL,
    Squadra1 VARCHAR(50) NOT NULL,
    Squadra2 VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    FOREIGN KEY (Squadra1 , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Squadra2 , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Giocatore1)
        REFERENCES giocatore (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Giocatore1)
        REFERENCES giocatore (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (NomeLega , Giocatore1 , Giocatore2 , Squadra1 , Squadra2)
);

CREATE TABLE formazione (
    Giornata INT NOT NULL,
    Schierata BOOLEAN NOT NULL,
    Squadra VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    FOREIGN KEY (Squadra , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (Giornata , Squadra , NomeLega)
);

CREATE TABLE offerta (
    Squadra VARCHAR(50) NOT NULL,
    DataInizio VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    Giocatore INT NOT NULL,
    somma INT NOT NULL,
    FOREIGN KEY (Giocatore)
        REFERENCES giocatore (Id)
         ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Squadra , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (DataInizio , NomeLega)
        REFERENCES asta (DataInizio , NomeLega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (Squadra , DataInizio , NomeLega , Giocatore)
);

CREATE TABLE scout (
    Nome VARCHAR(50) NOT NULL,
    Cognome VARCHAR(50) NOT NULL,
    Username VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    pass VARCHAR(50) NOT NULL,
    PRIMARY KEY (Username)
);

CREATE TABLE post (
    idPost INT AUTO_INCREMENT NOT NULL,
    DataPubblicazione VARCHAR(50) NOT NULL,
    Titolo VARCHAR(50) NOT NULL,
    Testo TEXT NOT NULL,
    Scout VARCHAR(50) NOT NULL,
    FOREIGN KEY (Scout)
        REFERENCES scout (Username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (idPost)
);


CREATE TABLE squadragiocatore (
    NomeSquadra VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    Id INT NOT NULL,
    FOREIGN KEY (NomeSquadra , NomeLega)
        REFERENCES squadra (NomeSquadra , Lega)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Id)
        REFERENCES giocatore (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (NomeSquadra , NomeLega , Id)
);

CREATE TABLE giocatoreformazione (
    Giornata INT NOT NULL,
    NomeSquadra VARCHAR(50) NOT NULL,
    NomeLega VARCHAR(50) NOT NULL,
    FOREIGN KEY ( Giornata,NomeSquadra , NomeLega)
        REFERENCES formazione (Giornata , Squadra , NomeLega)
        ON UPDATE CASCADE ON DELETE CASCADE,
	Id INT NOT NULL,
    posizione INT NOT NULL,
     FOREIGN KEY (Id)
        REFERENCES giocatore (Id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (Giornata , NomeSquadra , NomeLega,Id)
);


