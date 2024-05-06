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
	
	@Override
	public void run() {
        while (true) {
        	verificarFilaEspera();
        }
    }
	
	private void verificarFilaEspera() {
		if (hotel.buscarQuartosDisponiveis().length > 0 && !hotel.getFilaEspera().isEmpty()) {
			Grupo proximoGrupo = hotel.getProximoGrupoFilaEspera();
			hotel.alocarHospedes(proximoGrupo);
		}
	}
}
