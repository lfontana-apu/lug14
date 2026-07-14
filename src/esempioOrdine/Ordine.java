package esempioOrdine;

public class Ordine {

	// metodo con tipo di ritorno (non void) e argomento ricevuto di tipo Camicia
	public double aggiungiCamicia(Camicia laCamicia) {
			double prezzoTotale = laCamicia.prezzo*laCamicia.acquistate; // rigo 11 e rigo 12 di Camicia.java
			return prezzoTotale;
			// questo metodo è un pò come la cassa del supermercato: riceve l'oggetto di tipo Camicia e ne moltiplica due campi
		}
}
