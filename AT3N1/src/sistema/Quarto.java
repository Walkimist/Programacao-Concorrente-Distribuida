package sistema;

public class Quarto {
	private int quantidadeHospedes;
	private boolean chaveNoQuarto;
	
	public Quarto(int quantidadeHospedes, boolean chaveNoQuarto) {
		this.quantidadeHospedes = quantidadeHospedes;
		this.chaveNoQuarto = chaveNoQuarto;
	}

	public int getQuantidadeHospedes() {
		return quantidadeHospedes;
	}

	public void setQuantidadeHospedes(int quantidadeHospedes) {
		this.quantidadeHospedes = quantidadeHospedes;
	}
	
	public boolean isChaveNoQuarto() {
		return chaveNoQuarto;
	}

	public void setChaveNoQuarto(boolean chaveNoQuarto) {
		this.chaveNoQuarto = chaveNoQuarto;
	}
}
