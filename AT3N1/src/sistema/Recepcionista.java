package sistema;

public class Recepcionista extends Thread {
	private Hotel hotel;
	
	public Recepcionista(Hotel hotel) {
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
