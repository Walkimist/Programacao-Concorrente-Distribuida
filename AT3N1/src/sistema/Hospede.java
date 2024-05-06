package sistema;

public class Hospede extends Thread {
	private Quarto quarto;
	private Hotel hotel;
	
	public Hospede(Quarto quarto, Hotel hotel) {
		this.quarto = quarto;
		this.hotel = hotel;
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public void sairParaPassear() {
		quarto.setChaveNoQuarto(false);
	}
	
	public void voltarAoQuarto() {
		if (!quarto.isEmLimpeza()) {
			quarto.setChaveNoQuarto(true);
		}
	}

	public void finalizarEstadia() {
		quarto.setChaveNoQuarto(false);
	}
}