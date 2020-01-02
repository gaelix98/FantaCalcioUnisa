INSERT INTO allenatore(Nome,Cognome,email,password,username)
values ('Maria','Nattimo','maria.nattimo@gmail.com','pippo1','MariaN1');
INSERT INTO allenatore
values ('Gaetano','Castello','gaetano.castello@hotmail.it','Pippo11','Gaetanondorf');
INSERT INTO allenatore
values ('Mattia','Bella Sara','mattia.bellasara@gmail.com','PipPo111','Condor1234');
INSERT INTO allenatore
values ('Pasquale','Caramella','pasquale.caramella.22@gmail.com','PIPPO33','CARAMAo');
INSERT INTO allenatore
values ('Giovanni','Mucciaccia','artattack@hotmail.it','Capoo22','Artattack');
INSERT INTO allenatore
values ('Carlo','Conti','scossaaaa@gmail.com','tantantan1','Sc00S54');
INSERT INTO allenatore
values ('Gaetano','Casillo','gaelix@gmail.com','Condor1234--','Gaelix98');


INSERT INTO squadra(NomeSquadra,Lega,Logo,Allenatore,Punti,BudgetRimanente)
values ('PippoSquad','LegaNatalizia','C:\Media\Immagini\panda.png','CARAMAo',37,1);
INSERT INTO squadra
values ('FantaCola','Memeroni','C:\Media\Immagini\giovannino.png','Condor1234',100,0);
INSERT INTO squadra
values ('Zoe4Evah','Memeroni','C:\Media\Immagini\mandolino.png','Gaetanondorf',0,45);
INSERT INTO squadra
values ('Fiorellina','NotMemeroni','C:\Media\Immagini\inferno.png','MariaN1',66,6);
INSERT INTO squadra
values ('ScossaF.C.','LegaNatalizia','C:\Media\Immagini\fulmine.png','Sc00S54',44,22);
INSERT INTO squadra
values ('CavalDonato','LegaCheNonEsiste','C:\Media\Immagini\mucca.png','Artattack',33,23);




INSERT INTO lega(NomeLega,Logo,MaxAllenatori,quotaMensile,budget,primoPosto,secondoPosto,terzoPosto,Presidente)
values ('Memeroni','C:\Media\Immagini\italia.png',7,15,100,50,30,20,'MariaN1');
INSERT INTO lega
values ('NotMemeroni','C:\Media\Immagini\Campo.png',10,45,200,80,10,10,'Gaetanondorf');
INSERT INTO lega
values ('LegaDellaGiustizia','C:\Media\Immagini\jleague.png',5,3,120,40,35,25,'CARAMAo');
INSERT INTO lega 
values ('LegaNatalizia','C:\Media\Immagini\meryC.png',5,12,300,60,20,10,'Condor1234');
INSERT INTO lega
values ('LegaCheNonEsiste','C:\Media\Immagini\pandacorno.png',8,10,200,50,50,0,'MariaN1');




INSERT INTO invito(Allenatore,NomeLega,risposta)
values ('Gaetanondorf','Memeroni',1);
INSERT INTO invito
values ('MariaN1','NotMemeroni',1);
INSERT INTO invito
values ('CARAMAo','LegaCheNonEsiste',0);
INSERT INTO invito
values ('Condor1234','Memeroni',1);
INSERT INTO invito
values ('MariaN1','LegaCheNonEsiste',1);
INSERT INTO invito
values ('Gaetanondorf','LegaNatalizia',1);



INSERT INTO asta(DataInizio,NomeLega,Ora,DataFine)
values ('2019-12-25','LegaNatalizia','15:30:00','2020-02-01');
INSERT INTO asta
values ('2019-02-15','LegaNatalizia','09:00:00','2019-02-29');
INSERT INTO asta
values ('2019-10-10','Memeroni','13:00:00','2019-10-18');
INSERT INTO asta
values ('2019-10-15','NotMemeroni','16:00:00','2019-11-26');
INSERT INTO asta
values ('2019-07-01','LegaNatalizia','11:00:00','2019-07-08');
INSERT INTO asta
values ('2019-10-01','LegaCheNonEsiste','08:00:00','2019-10-15');




INSERT INTO partita(Squadra1,Squadra2,NomeLega,Goal1,Goal2,giornata)
values ('FantaCola','Zoe4Evah','Memeroni',10,0,3);
INSERT INTO partita
values ('PippoSquad','ScossaF.C.','LegaNatalizia',10,0,8);
INSERT INTO partita
values ('Zoe4Evah','FantaCola','Memeroni',5,2,21);
INSERT INTO partita
values ('ScossaF.C.','PippoSquad','LegaNatalizia',10,0,8);




INSERT INTO giocatore(Id,Nome,Cognome,Ruolo,SquadraReale,Presenze,VotoMedio,Goal,Assist,Ammonizioni,Espulsioni,RigoriSegnati,RigoriSbagliati,RigoriParati, prezzoBase)
values (1,'Cristiano','Ronaldo','Att','Juventus',15,8.0,10,8,0,0,7,3,0, 5);
INSERT INTO giocatore
values (2,'Lionel','Messi','Att','Barcellona',14,7.8,8,12,2,1,2,5,0,6);
INSERT INTO giocatore 
values (3,'Gianluigi','Buffon','Por','Juventus',11,8.8,0,0,0,0,0,0,27,10);
INSERT INTO giocatore
value (4,'Fabiano','Pecorelli','Att','UnisaF.C.',20,8.9,14,10,0,0,3,0,0,2);



INSERT INTO scambio(Giocatore1,Giocatore2,PrezzoOfferto,Squadra1,Squadra2,NomeLega)
values (1,2,50,'Zoe4Evah','FantaCola','Memeroni');
INSERT INTO scambio
values (1,4,80,'FantaCola','Zoe4Evah','Memeroni');
INSERT INTO scambio
values (4,2,70,'ScossaF.C.','PippoSquad','LegaNatalizia');
INSERT INTO scambio
values (4,1,110,'PippoSquad','ScossaF.C.','LegaNatalizia');




INSERT INTO formazione(Giornata,Schierata,Squadra,NomeLega)
values (6,1,'FantaCola','Memeroni');
INSERT INTO formazione 
values (6,0,'Zoe4Evah','Memeroni');
INSERT INTO formazione
values (3,1,'PippoSquad','LegaNatalizia');
INSERT INTO formazione
values (1,0,'Fiorellina','NotMemeroni');
INSERT INTO formazione
values (15,1,'ScossaF.C','LegaNatalizia');
INSERT INTO formazione
values (18,1,'CavalDonato','LegaCheNonEsiste');



INSERT INTO offerta(Squadra,DataInizio,NomeLega,Giocatore,somma)
values ('Zoe4Evah','2019-10-10','Memeroni',4,30);
INSERT INTO offerta
values ('PippoSquad','2019-02-15','LegaNatalizia',1,22);
INSERT INTO offerta
values ('Fiorellina','2019-10-15','NotMemeroni',2,12);
INSERT INTO offerta
values ('FantaCola','2019-10-10','Memeroni',2,23);
INSERT INTO offerta
values ('CavalDonato','2019-10-01','LegaCheNonEsiste',3,3);



INSERT INTO scout(Nome,Cognome,Username,email,pass)
values ('Paolo','Conte','LoScarso','nonhopiuidee@perlemail.com','bingobangobongo1');
INSERT INTO scout
values ('Angelo','Coralluzzo','LoScarso2','vogliofarejungler@mannaggiagaetano.it','imsohappyinthejungle1');
INSERT INTO scout
values ('Nardo','Nardiello','Narducci2000','mipiaceflammarti@tutto.it','Irefusetogooo1');





INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-12-24','COME GUADAGNARE',"primo post non so che scrivere quindi scrivero' del fatto che non so che scrivere scrivendo per l'appunto non so che scrivere",'LoScarso');
INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-12-25',"CHI PRENDE PIU' PUNTI QUEST'ANNO?","e io che ne so di chi prende piU punti non ho idea nemmeno delle regole del calcio tanto meno di quelle del fantacalcio quindi gioca e non chiedere a me",'LoScarso');
INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-12-01','6 PERSONAGGI IN CERCA DI ALLENATORE',"pinco, pallino, cip, ciop, trick, e ballak stanno cercando proprio te. Quindi comprateli che fai bene, un prezzo buono? 13 FM non farti scappare questa possibilitA",'Narducci2000');
INSERT INTO post(DataPubblicazione,Titolo,Testo,Scout)
values ('2019-11-01','FESTA SUPER COOL AD AVELLINO',"Vieni alla festa di GiorgioVanni alle ore 21:00 a via cavour n1 ci saranno tante bevande e si potranno vedere le partite dell'anno in HD al locale Lo SpaccaBicchiere",'LoScarso2');










INSERT INTO squadragiocatore(NomeSquadra,NomeLega,Id)
values ('PippoSquad','LegaNatalizia',3);
INSERT INTO squadragiocatore
values ('Fiorellina','NotMemeroni',3);
INSERT INTO squadragiocatore
values ('ScossaFC','LegaNatalizia',2);
INSERT INTO squadragiocatore
values ('Zoe4Evah','Memeroni',1);
INSERT INTO squadragiocatore
values ('FantaCola','Memeroni',4);
INSERT INTO squadragiocatore
values ('Zoe4Evah','Memeroni',2);







INSERT INTO giocatoreformazione(Giornata,NomeSquadra,NomeLega,Id,posizione)
values (12,'Zoe4Evah','Memeroni',3,1);
INSERT INTO giocatoreformazione
values (15,'Zoe4Eavh','Memeroni',2,9);
INSERT INTO giocatoreformazione
values (18,'FantaCola','Memeroni',3,1);
INSERT INTO giocatoreformazione
values (19,'FantaCola','Memeroni',4,11);
INSERT INTO giocatoreformazione
values (18,'Fiorellina','NotMemeroni',3,1);
INSERT INTO giocatoreformazione
values (18,'Fiorellina','NotMemeroni',2,10);
INSERT INTO giocatoreformazione
values (18,'Fiorellina','NotMemeroni',2,10);

















