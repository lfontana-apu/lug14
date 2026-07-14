package pizzeria;

public class PizzeriaTest {

	public static void main(String[] args) {
		// entrano tre clienti
		Cliente cliente1 = new Cliente(), cliente2 = new Cliente(), cliente3 = new Cliente();
		cliente1.nome = "Davide";
		cliente2.nome = "Kekko";
		cliente3.nome = "Ciro";
		cliente1.pizza = "Diavola";
		cliente2.pizza = "Capricciosa";
		cliente3.pizza = "Ai funghi";
		String bevanda = "Coca Cola";
		
		System.out.println(cliente1.nome + ", " + cliente2.nome + " e " + cliente3.nome + " elencano le ordinazioni alla cameriera: " + cliente1.pizza + ", " + cliente2.pizza + ", " + cliente3.pizza + " e " + bevanda);
		
		Cameriera miaCameriera = new Cameriera();
		miaCameriera.nome = "Federica";
		miaCameriera.comanda(cliente1, cliente2, cliente3, bevanda);
		
		System.out.println(cliente1.nome + ", " + cliente2.nome + " e " + cliente3.nome + " gustano le pizze ricordando i bei tempi del corso ITS");
		
	}

}
