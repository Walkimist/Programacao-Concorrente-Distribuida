package sistema;

public class Camareira {
	public Camareira(Quarto[] quartos) {
	}
	
	public void limparQuarto(Quarto quarto) {
		if (!quarto.isChaveNoQuarto()) {
			quarto.setEmLimpeza(true);
			System.out.println("Quarto limpo!");
			quarto.setEmLimpeza(false);
		}
	}
}
