package sistema;

public class Quarto {
	private Hospede[] hospedes;
	private boolean chaveNoQuarto, emLimpeza, ocupado;
	private int id;
	private boolean isSujo;
	
	public Quarto(int id, boolean ocupado, boolean emLimpeza, boolean chaveNoQuarto, boolean isSujo) {
		this.id = id;
		this.ocupado = ocupado;
		this.chaveNoQuarto = chaveNoQuarto;
		this.emLimpeza = emLimpeza;
		this.isSujo = isSujo;
	}
	
	public boolean isOcupado() {
		return ocupado;
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

	public void livreParaLimpeza() {
		chaveNoQuarto = false;
		isSujo = true;
	}
	
	public void removerHospedes() {
		for (Hospede hospede : hospedes) {
			hospede.setQuarto(null);
		}
		livreParaLimpeza();
		hospedes = null;
		ocupado = false;
	}
	
	public int getNumeroHospedes() {
		return hospedes.length;
	}

	public int getId() {
		return id;
	}
	
	public boolean isSujo() {
		return isSujo;
	}

	public void setSujo(boolean isSujo) {
		this.isSujo = isSujo;
	}
}
