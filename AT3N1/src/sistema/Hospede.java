package sistema;

public class Hospede {
	private Quarto quarto;
	
	public Hospede(Quarto quarto) {
		this.quarto = quarto;
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