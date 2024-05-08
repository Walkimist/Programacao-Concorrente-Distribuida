package sistema;

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
		while (hotel.getProximoGrupoFilaEspera() == null) { //Esperar caso não haja grupos fila
			Thread.sleep(5000);
		}
		
		Grupo proximoGrupo = hotel.getProximoGrupoFilaEspera();
		int quantidadeQuartosNecessarios = quantidadeQuartosProGrupo(proximoGrupo);
		Quarto[] quartosNecessarios = new Quarto[quantidadeQuartosNecessarios]; //Cria um array para armazenar os quartos do grupo
		
		if (hotel.checarQuartosDisponiveis() && hotel.buscarQuartosDisponiveis().length >= quantidadeQuartosNecessarios) {
			for (int i = 0; i < quartosNecessarios.length; i ++) { //Busca nos quartos disponíveis os quartos que o grupo precisa
				quartosNecessarios[i] = hotel.buscarQuartosDisponiveis()[i];
			}
			if (proximoGrupo.getTolerancia() > 0 && quartosNecessarios.length > 0 && proximoGrupo != null) {
				hotel.alocarHospedes(proximoGrupo, quartosNecessarios); //Passa o grupo e os quartos que eles precisam para alocação
				hotel.removerGrupoFila();
			}
		} else {
			proximoGrupo.reduzirTolerancia(); //Caso o hotel esteja cheio, reduz a tolerância de espera do grupo
		}
	}
	
	private int quantidadeQuartosProGrupo(Grupo grupo) { //Calcula quantos quartos serão necessários para o grupo
		 float num = (float) grupo.getHospedes().length/4;
	     int integer = (int) num;
	     if(num - integer > 0) {
	         integer++;
	     }
	     return integer;
	}
}
