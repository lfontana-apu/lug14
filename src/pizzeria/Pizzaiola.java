package pizzeria;

public class Pizzaiola {
	public String nome;
	
	public void cucina(Cliente primo, Cliente secondo, Cliente terzo) {
		System.out.println("La pizzaiola " + nome + " prepara e inforna le seguenti pizze: " + primo.pizza + ", " + secondo.pizza + " e " + terzo.pizza);
		System.out.println("La pizzaiola " + nome + " preleva le pizze pronte e chiama la cameriera");
	}
}
