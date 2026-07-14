package esercizioCellulare;

public class Cellulare {
	private double creditoDisponibile, tariffa;
	private int chiamateEffettuate;
	
	public void ricarica(int importo) {
		if (importo == 5 || importo == 10 || importo == 25) {
			this.creditoDisponibile += importo;
			System.out.println("[NOTIFICA SMS] Ricarica eseguita con successo! Nuovo credito: " + this.creditoDisponibile + " €" );
		} else {
			System.out.println("Importo ricarica non valido. Scegli tra 5, 10 o 25€");
		}
	
	}
	
	public void impostaTariffa(int centesimiAlMinuto) {
		if (centesimiAlMinuto > 0) {
			this.tariffa = centesimiAlMinuto / 100.0;
			System.out.println("[NOTIFICA SMS]  piano tariffario aggiornato a: " + centesimiAlMinuto + " cent/min (" + this.tariffa + " €/min.");
		} else {
			System.out.println("Tariffa non valida.");
		}
	}
	
	public void effettuaChiamata(String numero, int minutiDesiderati) {
		
	    if (this.tariffa <= 0) {
	        System.out.println("[ERRORE] Impossibile chiamare: imposta prima una tariffa valida!");
	        return;
	    }

	    if (this.creditoDisponibile <= 0) {
	        System.out.println("[ERRORE] Credito insufficiente per avviare la chiamata. Effettua una ricarica.");
	        return;
	    }
	    
	    System.out.println("In connessione con il numero " + numero + "...");
	    
	    double costoTeorico = minutiDesiderati * this.tariffa;

	    if (this.creditoDisponibile >= costoTeorico) {
	        this.creditoDisponibile -= costoTeorico;
	        this.chiamateEffettuate++;
	        System.out.println("Chiamata terminata regolarmente. Durata: " + minutiDesiderati + " min. Costo: " + costoTeorico + "€");
	    } else {
	        int minutiEffettivi = (int) (this.creditoDisponibile / this.tariffa);
	        this.creditoDisponibile = 0.0;
	        this.chiamateEffettuate++;
	        
	        System.out.println("[NOTIFICA SMS] Chiamata interrotta per esaurimento credito.");
	        System.out.println("Conversazione durata solo " + minutiEffettivi + " minuti rispetto ai " + minutiDesiderati + " richiesti.");
	    }
	}
	
	public double getCreditoDisponibile() {
		return this.creditoDisponibile;
	}
	
	public int getChiamateEffettuate() {
		return this.chiamateEffettuate;
	}

	public void azzeraContatoreChiamate() {
		this.chiamateEffettuate = 0;
		System.out.println("[NOTIFICA SMS]  Il registro delle chiamate effettuate è stato azzerato.");
	}
}
