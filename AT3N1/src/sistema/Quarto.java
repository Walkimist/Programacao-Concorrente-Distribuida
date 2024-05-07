package sistema;

public class Quarto {
	private Hospede[] hospedes;
	private boolean chaveNoQuarto, emLimpeza, ocupado;
	
	public Quarto(boolean ocupado, boolean emLimpeza, boolean chaveNoQuarto) {
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
	
	public boolean isChaveNoQuarto() {
		return chaveNoQuarto;
	}

	public void setChaveNoQuarto(boolean chaveNoQuarto) {
		this.chaveNoQuarto = chaveNoQuarto;
	}
	
	public void setHospedes(Hospede[] hospedes) {
		this.hospedes = hospedes;
		ocupado = true;
	}
	
	public void removerHospedes() {
		for (Hospede hospede : hospedes) {
			hospede.setQuarto(null);
		}
		hospedes = null;
		ocupado = false;
	}
	
	public int getNumeroHospedes() {
		return hospedes.length;
	}
}
