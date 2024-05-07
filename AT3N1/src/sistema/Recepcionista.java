package sistema;

import java.util.Random;

public class Recepcionista extends Thread {
	private Hotel hotel;
	
	public Recepcionista(Hotel hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public void run() {
        while (true) {
        	if (!hotel.getFilaEspera().isEmpty()) {
        		try {
					verificarFilaEspera();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
        }
    }
	
	private void verificarFilaEspera() throws InterruptedException {
		Grupo proximoGrupo = hotel.getProximoGrupoFilaEspera();
		while (proximoGrupo == null) {
			Thread.sleep(new Random().nextInt(2000));
		}
		if (hotel.buscarQuartosDisponiveis().length > quantidadeQuartosProGrupo(proximoGrupo)) {
			if (proximoGrupo != null) {
			hotel.alocarHospedes(proximoGrupo);
			}
		} else {
			System.out.println("ta chei");
		}
	}
	
	private int quantidadeQuartosProGrupo(Grupo grupo) {
		int tamanhoGrupo = grupo.getHospedes().length;
		int quartosNecessarios = (tamanhoGrupo + 3) / 4;
		return quartosNecessarios;
	}
}
