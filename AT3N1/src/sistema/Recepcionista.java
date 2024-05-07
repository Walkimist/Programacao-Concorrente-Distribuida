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
		if (hotel.buscarQuartosDisponiveis() != null && !hotel.getFilaEspera().isEmpty()) {
			Grupo proximoGrupo = hotel.getProximoGrupoFilaEspera();
			if (proximoGrupo != null) {
			hotel.alocarHospedes(proximoGrupo);
			}
		} else if (hotel.buscarQuartosDisponiveis() == null) {
			System.out.println("Quartos cheios!");
		}
	}
}
