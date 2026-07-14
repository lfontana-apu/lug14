package esempioOrdine;

public class OrdineTest {

	public static void main(String[] args) {
		Ordine mioOrdine = new Ordine(); // dichiaro e creo un oggetto di tipo Ordine
		Camicia camicia1 = new Camicia(), camicia2 = new Camicia(), camicia3 = new Camicia();
		double costoTotale = 0; // qui effettuo le somme
		
		// inizializzazione campi dei tre oggetti Camicia
		camicia1.prezzo = 23.90;
		camicia2.prezzo = 20.0;
		camicia3.prezzo = 52.99;
		camicia1.acquistate = 2;
		camicia2.acquistate = 5;
		camicia3.acquistate = 1;
		
		int camicieTotali = camicia1.acquistate + camicia2.acquistate + camicia3.acquistate; // somma delle quantità
		
		//                     23.90*2 + 20.0*5 + 52.99*1
		costoTotale = mioOrdine.aggiungiCamicia(camicia1) + mioOrdine.aggiungiCamicia(camicia2) + mioOrdine.aggiungiCamicia(camicia3);
		
		System.out.println("Il costo totale della merce è di: " + costoTotale + " euro, per " + camicieTotali + " articolo");
	}

}
