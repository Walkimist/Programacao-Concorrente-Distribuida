package sistema;


public class Recepcionista extends Thread {
	private Quarto[] quartos;
	private Grupo[] grupos;
	private Hotel hotel;
	
	public Recepcionista(Quarto[] quartos, Grupo[] grupos, Hotel hotel) {
		this.grupos = grupos;
		this.quartos = quartos;
		this.hotel = hotel;
	}
	
}
