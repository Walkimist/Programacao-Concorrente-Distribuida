package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Hotel hotel = new Hotel(); //Inicia o Hotel
		
		//Declaração dos hospedes
		Hospede[] hospedes = new Hospede[50];
		for (int i = 0; i < 50; i ++) {
			hospedes[i] = new Hospede();
		}
		
		int maximoHospedes = 50;
		int index = 0;
		while (maximoHospedes > 0) { //Cria grupos até incluir todos os hóspedes
			int tamanhoGrupo = new Random().nextInt(10) + 1; //Tamanho aleatório de grupo, de 1 a 10 hóspedes
			if (maximoHospedes - tamanhoGrupo < 0) { //Garante que não haverão grupos maiores que o número restante de hóspedes
				tamanhoGrupo = maximoHospedes;
				maximoHospedes = 0;
			} else {
				maximoHospedes -= tamanhoGrupo;
			}
			
			List<Hospede> listaHospedes = new ArrayList<Hospede>(); //ArrayList para listar os hospedes de cada grupo
			for(int i =0; i < tamanhoGrupo; i++) { //Adiciona os hospedes do array hospedes para a lista que será colocada no grupo
			    listaHospedes.add(hospedes[index]);
			    index++;
			}
			//Convertendo ArrayList para array
			Hospede[] grupoHospedes = new Hospede[listaHospedes.size()];
			grupoHospedes = listaHospedes.toArray(grupoHospedes);
			new Grupo(grupoHospedes, hotel, 3).start(); //Cria e inicializa threads dos grupos
		}
		
		//Inicializa threads dos hospedes
		for (Hospede hospede : hospedes) {
			hospede.start();
		}
		
		//Inicializa threads das recepcionistas
		for (Recepcionista recepcionista : hotel.getRecepcionitas()) {
			recepcionista.start();
		}
		
		//Inicializa threads das camareiras
		for (Camareira camareira : hotel.getCamareiras()) {
			camareira.start();
		}
	}
}
