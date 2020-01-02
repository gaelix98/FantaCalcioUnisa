use fantacalciounisaTest;
delete from allenatore;
delete from invito;
delete from lega;
delete from squadra;
delete from giocatore;
delete from squadragiocatore;
delete from asta;
delete from partita;
delete from scout;
delete from post;
delete from formazione;
delete from offerta;
delete from scambio;
delete from giocatoreformazione;
delete from allenatore where username="Gaelix98";
INSERT INTO allenatore
values ('Pasquale','Caramante','pasquale@gmail.com','pasquale12','pasquale98');
INSERT INTO allenatore
values ('Giovanni','Mucciaccia','artattack@hotmail.it','Capoo22','Artattack');
INSERT INTO allenatore
values ('Carlo','Conti','scossaaaa@gmail.com','tantantan1','Sc00S54');


INSERT INTO squadra(NomeSquadra,Lega,Logo,Allenatore,Punti,BudgetRimanente)
values ('PippoSquad','NotMemeroni','C:\Media\Immagini\panda.png','pasquale98',37,1);
INSERT INTO squadra
values ('FantaCola','MemeroniX','C:\Media\Immagini\giovannino.png','pasquale98',100,0);
INSERT INTO squadra
values ('Zoe4Evah','MemeroniX','C:\Media\Immagini\mandolino.png','Artattack',0,45);
INSERT INTO squadra
values ('Fiorellina','NotMemeroni','C:\Media\Immagini\inferno.png','Sc00S54',66,6);

INSERT INTO lega(NomeLega,Logo,MaxAllenatori,quotaMensile,budget,primoPosto,secondoPosto,terzoPosto,Presidente)
values ('MemeroniX','C:\Media\Immagini\italia.png',7,15,100,50,30,20,'pasquale98');
INSERT INTO lega
values ('NotMemeroni','C:\Media\Immagini\Campo.png',10,45,200,80,10,10,'pasquale98');

INSERT INTO invito(Allenatore,NomeLega,risposta)
values ('ArtAttack','MemeroniX',1);
INSERT INTO invito
values ('Sc00S54','NotMemeroni',1);

INSERT INTO asta(DataInizio,NomeLega,Ora,DataFine)
values ('2019-12-25','MemeroniX','15:30:00','2020-02-01');
INSERT INTO asta
values ('2019-02-15','MemeroniX','09:00:00','2019-02-29');

INSERT INTO partita(Squadra1,Squadra2,NomeLega,Goal1,Goal2,giornata)
values ('FantaCola','Zoe4Evah','MemeroniX',10,0,3);
INSERT INTO partita(Squadra1,Squadra2,NomeLega,Goal1,Goal2,giornata)
values ('FantaCola','Zoe4Evah','MemeroniX',10,0,2);


INSERT INTO giocatore(Id,Nome,Cognome,Ruolo,SquadraReale,Presenze,VotoMedio,Goal,Assist,Ammonizioni,Espulsioni,RigoriSegnati,RigoriSbagliati,RigoriParati)
values (1,'Cristiano','Ronaldo','Att','Juventus',15,8.0,10,8,0,0,7,3,0);
INSERT INTO giocatore
values (2,'Lionel','Messi','Att','Barcellona',14,7.8,8,12,2,1,2,5,0);
INSERT INTO giocatore 
values (3,'Gianluigi','Buffon','Por','Juventus',11,8.8,0,0,0,0,0,0,27);
INSERT INTO giocatore
value (4,'Fabiano','Pecorelli','Att','UnisaF.C.',20,8.9,14,10,0,0,3,0,0);

INSERT INTO scambio(Giocatore1,Giocatore2,PrezzoOfferto,Squadra1,Squadra2,NomeLega)
values (1,2,50,'Zoe4Evah','FantaCola','MemeroniX');
INSERT INTO scambio
values (1,4,80,'FantaCola','Zoe4Evah','MemeroniX');

INSERT INTO formazione 
values (1,0,'Zoe4Evah','MemeroniX');
INSERT INTO formazione
values (1,0,'Fiorellina','NotMemeroni');

INSERT INTO offerta(Squadra,DataInizio,NomeLega,Giocatore,somma)
values ('Zoe4Evah','2019-12-25','MemeroniX',4,30);
INSERT INTO offerta
values ('FantaCola','2019-12-25','MemeroniX',2,23);
INSERT INTO offerta
values ('Zoe4Evah','2019-12-25','MemeroniX',3,23);

INSERT INTO squadragiocatore
values ('FantaCola','MemeroniX',2);
INSERT INTO squadragiocatore
values ('Zoe4Evah','MemeroniX',3);
INSERT INTO squadragiocatore
values ('Zoe4Evah','MemeroniX',4);

INSERT INTO giocatoreformazione(Giornata,NomeSquadra,NomeLega,Id,posizione)
values (1,'Zoe4Evah','MemeroniX',3,1);
INSERT INTO giocatoreformazione(Giornata,NomeSquadra,NomeLega,Id,posizione)
values (1,'Zoe4Evah','MemeroniX',4,1);

INSERT INTO scout(Nome,Cognome,Username,email,pass)
values ('Paolo','Conte','LoScarso','nonhopiuidee@perlemail.com','bingobangobongo1');
INSERT INTO scout
values ('Angelo','Coralluzzo','LoScarso2','vogliofarejungler@mannaggiagaetano.it','imsohappyinthejungle1');
INSERT INTO scout
values ('Nardo','Nardiello','Narducci2000','mipiaceflammarti@tutto.it','Irefusetogooo1');

INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-12-24','COME GUADAGNARE',"primo post",'LoScarso');
INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-12-25',"CHI PRENDE PIU' PUNTI QUEST'ANNO?","secondo post",'LoScarso');
INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-12-01','6 PERSONAGGI IN CERCA DI ALLENATORE',"pinco, pallino",'Narducci2000');