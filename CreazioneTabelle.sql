create  database FantaCalcioUnisa;


CREATE TABLE `fantacalciounisa`.`allenatore` (
  `Nome` VARCHAR(50) NOT NULL,
  `Cognome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `username` VARCHAR(50) NOT NULL,
  `squadra` VARCHAR(45) NULL,
  FOREIGN KEY (squadra) references squadra(NomeSquadra)
  ON UPDATE CASCADE
  ON DELETE CASCADE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  PRIMARY KEY (`username`));
  
CREATE TABLE squadra (
    NomeSquadra VARCHAR(50) NOT NULL,
    Lega VARCHAR(50) NOT NULL,
    Logo VARCHAR(50) NOT NULL,
    Allenatore VARCHAR(50) NOT NULL,
    Punti INT NOT NULL,
    BudgetRimanente FLOAT NOT NULL,
    FOREIGN KEY (Allenatore)
        REFERENCES allenatore (Username)
        ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY (NomeSquadra , Lega)
);

create table lega (
NomeLega VARCHAR(50) NOT NULL,
Logo VARCHAR(50) NOT NULL,
MaxAllenatori int NOT NULL,
quotaMensile float NOT NULL,
budget int NOT NULL,
primoPosto int NOT NULL,
secondoPosto int NOT NULL,
terzoPosto int NOT NULL,
Presidente VARCHAR(50) NOT NULL,
##Partite 
)
