package sistema;

public class Camareira extends Thread {
	private Hotel hotel;
	
	public Camareira(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public void limparQuarto(Quarto quarto) {
		if (!quarto.isChaveNoQuarto()) {
			quarto.setEmLimpeza(true);
			System.out.println("Quarto limpo!");
			quarto.setEmLimpeza(false);
		}
	}
}
