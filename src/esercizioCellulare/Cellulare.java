package esercizioCellulare;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Cellulare {
	private double creditoDisponibile, tariffa;
	private int chiamateEffettuate;
	private ArrayList<Contatto> rubrica = new ArrayList<>();
	private ArrayList<String> registroNumeriChiamati = new ArrayList<>();
	private Random random = new Random();

	public void ricarica(int importo) {
		if (importo == 5 || importo == 10 || importo == 25) {
			this.creditoDisponibile += importo;
			System.out.println(
					"[NOTIFICA SMS] Ricarica eseguita con successo! Nuovo credito: " + this.creditoDisponibile + " €");
		} else {
			System.out.println("Importo ricarica non valido. Scegli tra 5, 10 o 25€");
		}

	}

	public void impostaTariffa(int centesimiAlMinuto) {
		if (centesimiAlMinuto > 0) {
			this.tariffa = centesimiAlMinuto / 100.0;
			System.out.println("[NOTIFICA SMS]  piano tariffario aggiornato a: " + centesimiAlMinuto + " cent/min ("
					+ this.tariffa + " €/min.");
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
			System.out.println("Chiamata terminata regolarmente. Durata: " + minutiDesiderati + " min. Costo: "
					+ costoTeorico + "€");
		} else {
			int minutiEffettivi = (int) (this.creditoDisponibile / this.tariffa);
			this.creditoDisponibile = 0.0;
			this.chiamateEffettuate++;

			System.out.println("[NOTIFICA SMS] Chiamata interrotta per esaurimento credito.");
			System.out.println("Conversazione durata solo " + minutiEffettivi + " minuti rispetto ai "
					+ minutiDesiderati + " richiesti.");
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

	private String cercaInRubrica(String numero) {
		for (Contatto c : rubrica) {
			if (c.getNumero().equals(numero)) {
				return c.getNome();
			}
		}
		return null;
	}

	public void visualizzaRubrica() {
		System.out.println("--- RUBRICA CONTATTI ---");
		if (rubrica.isEmpty()) {
			System.out.println("La rubrica è vuota.");
			return;
		}
		for (int i = 0; i < rubrica.size(); i++) {
			Contatto c = rubrica.get(i);
			System.out.println("[" + i + "] Nome: " + c.getNome() + " | Numero: " + c.getNumero());
		}
	}

	public void aggiungiContatto(String nome, String numero) {
		rubrica.add(new Contatto(nome, numero));
		System.out.println("[INFO] Contatto '" + nome + "' aggiunto con successo!");
	}

	public void rimuoviContatto(int indice) {
		if (indice >= 0 && indice < rubrica.size()) {
			Contatto rimosso = rubrica.remove(indice);
			System.out.println("[INFO] Contatto '" + rimosso.getNome() + "' rimosso.");
		} else {
			System.out.println("[ERRORE] Indice rubrica non valido.");
		}
	}

	public ArrayList<Contatto> getListaRubrica() {
		return rubrica;
	}

	public void visualizzaRegistroChiamate() {
		System.out.println("--- REGISTRO DETTAGLIATO CHIAMATE ---");
		if (registroNumeriChiamati.isEmpty()) {
			System.out.println("Nessuna chiamata nel registro.");
			return;
		}

		for (int i = 0; i < registroNumeriChiamati.size(); i++) {
			String numero = registroNumeriChiamati.get(i);
			String nomeContatto = cercaInRubrica(numero);

			if (nomeContatto != null) {
				System.out.println((i + 1) + ". Chiamata effettuata a: " + nomeContatto + " (" + numero + ")");
			} else {
				System.out.println((i + 1) + ". Chiamata effettuata a: " + numero);
			}
		}
		System.out.println("Quantità totale telefonate effettuate: " + this.chiamateEffettuate);
	}

	public void avviaSlotMachine(Scanner in) {
		int giocate = 0;
		int vittorie = 0;
		boolean inGioco = true;

		System.out.println("-----------");
		System.out.println("         SLOT MACHINE APP        ");
		System.out.println("-----------");

		while (inGioco) {
			System.out.println("1. Gioca");
			System.out.println("2. Esci");
			System.out.print("Scegli: ");
			int sceltaSlot = in.nextInt();

			if (sceltaSlot == 1) {
				giocate++;
				// settando random.nextInt(5) aumentano o diminuiscono le possibilità di vittoria.
				// "5" sta per (5 icone)
				//la possibilità di vincita è di circa 4%
				int n1 = random.nextInt(5) + 1;
				int n2 = random.nextInt(5) + 1;
				int n3 = random.nextInt(5) + 1;

				System.out.println(" [ " + n1 + " ] [ " + n2 + " ] [ " + n3 + " ] ");

				if (n1 == n2 && n2 == n3) {
					System.out.println(" COMPLIMENTI! HAI VINTO! ");
					vittorie++;
				} else {
					System.out.println("Nessuna combinazione. Riprova!");
				}
			} else if (sceltaSlot == 2) {
				inGioco = false;
				System.out.println("Chiusura Slot Machine...");
				System.out.println(
						"Statistiche sessione: hai vinto " + vittorie + " volte su " + giocate + " partite giocate.");
			} else {
				System.out.println("[ERRORE] Scelta non valida.");
			}
		}
	}

}
