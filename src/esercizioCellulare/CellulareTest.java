package esercizioCellulare;
import java.util.Scanner;

public class CellulareTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Cellulare mioCellulare = new Cellulare();
		int scelta = 0;
		
		System.out.println("--- BENVENUTO NEL TUO CELLULARE ---");
		
		while (scelta != 7){
			System.out.println("\n --- MENU NAVIGAZIONE ---");
			System.out.println("1. Ricarica");
			System.out.println("2. Imposta tariffa");
			System.out.println("3. Chiama");
			System.out.println("4. Credito");
			System.out.println("5. Contatore chiamate");
			System.out.println("6. Azzera chiamate");
			System.out.println("7. Spegni");
			System.out.println("Scegli un'opzione (1-7):");
			
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
			            System.out.println("[ERRORE] Il numero deve contenere solo cifre!\n");
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
			            System.out.println("- I fissi iniziano con '0' (da 8 a 11 cifre)\n");
			        }
			    }

			    System.out.print("Inserisci i minuti di durata desiderati: ");
			    int minuti = scanner.nextInt();
			    mioCellulare.effettuaChiamata(numero, minuti);
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
				System.out.println("\n Spegnimento del cellulare in corso... Arrivederci!");
				break;
				
				default:
					System.out.println("Opzione errata, riprova.");
					break;
			}
		}
		
		scanner.close();
	}

}
