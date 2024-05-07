package sistema;

public class Quarto {
	private int quantidadeHospedes;
	private boolean chaveNoQuarto, emLimpeza, ocupado;
	
	public Quarto(int quantidadeHospedes, boolean ocupado, boolean emLimpeza, boolean chaveNoQuarto) {
		this.quantidadeHospedes = quantidadeHospedes;
		this.ocupado = ocupado;
		this.chaveNoQuarto = chaveNoQuarto;
		this.emLimpeza = emLimpeza;
	}
	
	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
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
