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
        	try {
				verificarFilaEspera();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
	
	private synchronized void verificarFilaEspera() throws InterruptedException {
		while (hotel.getProximoGrupoFilaEspera() == null) {
			Thread.sleep(new Random().nextInt(2000));
		}
		Grupo proximoGrupo = hotel.getProximoGrupoFilaEspera();
		int quantidadeQuartosNecessarios = quantidadeQuartosProGrupo(proximoGrupo);
		Quarto[] quartosNecessarios = new Quarto[quantidadeQuartosNecessarios];
		
		if (hotel.checarQuartosDisponiveis() && hotel.buscarQuartosDisponiveis().length >= quantidadeQuartosNecessarios) {
			for (int i = 0; i < quartosNecessarios.length; i ++) {
				quartosNecessarios[i] = hotel.buscarQuartosDisponiveis()[i];
			}
			if (proximoGrupo.getTolerancia() > 0 && quartosNecessarios.length > 0) {
				hotel.alocarHospedes(proximoGrupo, quartosNecessarios);
				hotel.removerGrupoFila();
			}
		} else {
			proximoGrupo.reduzirTolerancia();
		}
	}
	
	private int quantidadeQuartosProGrupo(Grupo grupo) {
		 float num = (float) grupo.getHospedes().length/4;
	     int integer = (int) num;
	     if(num - integer > 0) {
	         integer++;
	     }
	     return integer;
	}
}
