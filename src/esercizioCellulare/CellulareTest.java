package esercizioCellulare;

import java.util.Scanner;

public class CellulareTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Cellulare mioCellulare = new Cellulare();
		int scelta = 0;

		System.out.println("--- BENVENUTO NEL TUO CELLULARE ---");

		while (scelta != 7) {
			System.out.println("--- MENU NAVIGAZIONE ---");
			System.out.println("1. Ricarica");
			System.out.println("2. Imposta tariffa");
			System.out.println("3. Chiama");
			System.out.println("4. Credito");
			System.out.println("5. Contatore chiamate");
			System.out.println("6. Azzera chiamate");
			System.out.println("7. Spegni");
			System.out.println("-------------------------");
			System.out.println("8. Gestisci Rubrica");
			System.out.println("9. Registro chiamate dettagliato");
			System.out.println("10. Slot machine");
			System.out.println("Scegli un'opzione (1-10):");

			scelta = scanner.nextInt();
			scanner.nextLine();

			switch (scelta) {
			case 1:
				System.out.println("Scegli quantità di ricarica (5, 10 o 25): ");
				int importo = scanner.nextInt();
				mioCellulare.ricarica(importo);
				break;

			case 2:
				System.out.println("Inserisci tariffa in centesimi al minuto (es. 15): ");
				int centesimi = scanner.nextInt();
				mioCellulare.impostaTariffa(centesimi);
				break;

			case 3:
				String numero = "";
				boolean procediChiamata = false;

				System.out.println("Come vuoi effettuare la chiamata?");
				System.out.println("1. Digitando il numero a mano");
				System.out.println("2. Scegliendo dalla rubrica");
				System.out.print("Scelta (1 o 2): ");
				int sceltaChiamata = scanner.nextInt();
				scanner.nextLine();

				if (sceltaChiamata == 2) {
					mioCellulare.visualizzaRubrica();
					if (mioCellulare.getListaRubrica().isEmpty()) {
						System.out.println("[INFO] Impossibile chiamare da rubrica: è vuota.");
					} else {
						System.out.print("Inserisci l'indice del contatto da chiamare: ");
						int indiceChiamata = scanner.nextInt();
						scanner.nextLine();

						if (indiceChiamata >= 0 && indiceChiamata < mioCellulare.getListaRubrica().size()) {
							numero = mioCellulare.getListaRubrica().get(indiceChiamata).getNumero();
							procediChiamata = true;
						} else {
							System.out.println("[ERRORE] Indice inserito non valido.");
						}
					}
				} else {
					boolean numeroValido = false;
					while (!numeroValido) {
						System.out.print("Inserisci il numero da comporre: ");
						numero = scanner.nextLine();

						String numeroPulito = numero;
						if (numeroPulito.startsWith("+39")) {
							numeroPulito = numeroPulito.substring(3);
						} else if (numeroPulito.startsWith("0039")) {
							numeroPulito = numeroPulito.substring(4);
						}

						boolean soloCifre = true;
						for (int i = 0; i < numeroPulito.length(); i++) {
							char carattere = numeroPulito.charAt(i);
							if (!Character.isDigit(carattere)) {
								soloCifre = false;
								break;
							}
						}

						if (numeroPulito.isEmpty() || !soloCifre) {
							System.out.println("[ERRORE] Il numero deve contenere solo cifre!");
							continue;
						}

						char primaCifra = numeroPulito.charAt(0);
						int lunghezza = numeroPulito.length();

						if (primaCifra == '3' && (lunghezza == 9 || lunghezza == 10)) {
							numeroValido = true;
						} else if (primaCifra == '0' && (lunghezza >= 8 && lunghezza <= 11)) {
							numeroValido = true;
						} else {
							System.out.println("[ERRORE] Numero non riconosciuto.");
							System.out.println("- I cellulari iniziano con '3' (9 o 10 cifre)");
							System.out.println("- I fissi iniziano con '0' (da 8 a 11 cifre)");
						}
					}
					procediChiamata = true;
				}

				if (procediChiamata) {
					System.out.print("Inserisci i minuti di durata desiderati: ");
					int minuti = scanner.nextInt();
					scanner.nextLine();
					mioCellulare.effettuaChiamata(numero, minuti);
				}
				break;

			case 4:
				System.out.println("Credito residuo attuale: " + mioCellulare.getCreditoDisponibile() + " €");
				break;

			case 5:
				System.out.println("Quantità di chiamate effettuate: " + mioCellulare.getChiamateEffettuate());
				break;

			case 6:
				mioCellulare.azzeraContatoreChiamate();
				break;

			case 7:
				System.out.println("Spegnimento del cellulare in corso... Arrivederci!");
				break;

			case 8:
				System.out.println("--- COSA VUOI FARE IN RUBRICA? ---");
				System.out.println("1. Visualizza contatti");
				System.out.println("2. Aggiungi un contatto");
				System.out.println("3. Rimuovi un contatto");
				System.out.print("Scelta (1-3): ");
				int sceltaRubrica = scanner.nextInt();
				scanner.nextLine();

				if (sceltaRubrica == 1) {
					mioCellulare.visualizzaRubrica();
				} else if (sceltaRubrica == 2) {
					System.out.print("Inserisci il nome: ");
					String nome = scanner.nextLine();
					System.out.print("Inserisci il numero: ");
					String num = scanner.nextLine();
					mioCellulare.aggiungiContatto(nome, num);
				} else if (sceltaRubrica == 3) {
					mioCellulare.visualizzaRubrica();
					if (!mioCellulare.getListaRubrica().isEmpty()) {
						System.out.print("Inserisci l'indice del contatto da rimuovere: ");
						int indRimuovi = scanner.nextInt();
						scanner.nextLine();
						mioCellulare.rimuoviContatto(indRimuovi);
					}
				} else {
					System.out.println("[ERRORE] Opzione non valida.");
				}
				break;

			case 9:
				mioCellulare.visualizzaRegistroChiamate();
				break;

			case 10:
				mioCellulare.avviaSlotMachine(scanner);
				break;

			default:
				System.out.println("Opzione errata, riprova.");
				break;
			}
		}

		scanner.close();
		System.exit(0);
	}
}