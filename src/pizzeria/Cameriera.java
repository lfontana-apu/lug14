package pizzeria;

public class Cameriera {
	public String nome;
	
	public void comanda(Cliente uno, Cliente due, Cliente tre, String bev) {
		System.out.println("La cameriera " + nome + " porta " + bev + " al tavolo");
		System.out.println("La cameriera " + nome + " chiede alla pizzaiola di preparare " + uno.pizza + ", " + due.pizza + " e " + tre.pizza);
		
		Pizzaiola miaPizzaiola = new Pizzaiola();
		miaPizzaiola.nome = "Deborah";
		miaPizzaiola.cucina(uno, due, tre);
		
		System.out.println("La cameriera " + nome + " consegna " + uno.pizza + ", " + due.pizza + " e " + tre.pizza + " al tavolo augurando buon appetito");
	}
}
