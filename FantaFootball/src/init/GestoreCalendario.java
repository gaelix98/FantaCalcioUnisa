package init;

import gestoreLega.Partita;
import gestoreSquadra.Squadra;

public class GestoreCalendario {
	
	
	public Partita[] creaCalendario(Squadra[] squadre){
		
	    int numero_squadre = squadre.length;
	    int giornate = numero_squadre - 1;
	    Partita[] partite = new Partita[giornate];
	    
	    /* crea gli array per le due liste in casa e fuori */
	    Squadra[] casa = new Squadra[numero_squadre /2];
	    Squadra[] trasferta = new Squadra[numero_squadre /2];
	 
	    for (int i = 0; i < numero_squadre /2; i++) {
	        casa [i] = squadre[i]; 
	        trasferta[i] = squadre[numero_squadre - 1 - i]; 
	    }
	 
	    for (int i = 0; i < giornate; i++) {
	        /* stampa le partite di questa giornata */
	        System.out.printf("%d^ Giornata\n",i+1);
	 
	        /* alterna le partite in casa e fuori */
	        if (i % 2 == 0) {
	            for (int j = 0; j < numero_squadre /2 ; j++)
	                 partite[i] = new Partita(trasferta[j],casa[j],i+1);
	        }
	        else {
	            for (int j = 0; j < numero_squadre /2 ; j++) 
	            	 partite[i] = new Partita(casa[j],trasferta[j],i+1);
	        }
	 
	        // Ruota in gli elementi delle liste, tenendo fisso il primo elemento
	        // Salva l'elemento fisso
	        Squadra pivot = casa [0];
	 
	        /* sposta in avanti gli elementi di "trasferta" inserendo 
	           all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto" */
			   
	 		Squadra riporto = trasferta[trasferta.length - 1];
			trasferta = shiftRight(trasferta, casa[1]);

	        /* sposta a sinistra gli elementi di "casa" inserendo all'ultimo 
	           posto l'elemento "riporto" */
			   
	        casa = shiftLeft(casa, riporto);
	 
	        // ripristina l'elemento fisso
	        casa[0] = pivot ;
	    } 
	    return partite;
	}
	 
	 private Squadra[] shiftLeft(Squadra[] data, Squadra add) {
			Squadra[] temp = new Squadra[data.length];
			for (int i = 0; i < data.length-1; i++) {
				temp[i] = data[i + 1];
			}
			temp[data.length - 1] = add;
			return temp;
		}

		private Squadra[] shiftRight(Squadra[] data, Squadra add) {
			Squadra[] temp = new Squadra[data.length];
			for (int i = 1; i < data.length; i++) {
				temp[i] = data[i - 1];
			}
			temp[0] = add;
			return temp;
		}
}
