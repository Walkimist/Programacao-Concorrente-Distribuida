package sistema;

public class Hospede extends Thread {
	private Quarto quarto;
	private Hotel hotel;
	
	public Hospede(Hotel hotel) {
		this.quarto = null;
		this.hotel = hotel;
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public Quarto getQuarto() {
		return quarto;
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