package sistema;

public class Quarto {
	private int quantidadeHospedes;
	private boolean chaveNoQuarto, emLimpeza;
	
	public Quarto(int quantidadeHospedes) {
		this.quantidadeHospedes = quantidadeHospedes;
		this.chaveNoQuarto = false;
		this.emLimpeza = false;
	}

	public boolean isEmLimpeza() {
		return emLimpeza;
	}

	public void setEmLimpeza(boolean emLimpeza) {
		this.emLimpeza = emLimpeza;
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
