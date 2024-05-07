package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		
		Hospede[] hospedes = new Hospede[50];
		for (int i = 0; i < 50; i ++) {
			hospedes[i] = new Hospede(hotel);
		}
		
		int maximoHospedes = 50;
		int index = 0;
		while (maximoHospedes > 0) {
			int tamanhoGrupo = new Random().nextInt(10) + 1;
			if (maximoHospedes - tamanhoGrupo < 0) {
				tamanhoGrupo = maximoHospedes;
				maximoHospedes = 0;
			} else {
				maximoHospedes -= tamanhoGrupo;
			}
			
			List<Hospede> listaHospedes = new ArrayList<Hospede>();
			for(int i =0; i < tamanhoGrupo; i++) {
			    listaHospedes.add(hospedes[index]);
			    index++;
			}
			Hospede[] grupoHospedes = new Hospede[listaHospedes.size()];
			grupoHospedes = listaHospedes.toArray(grupoHospedes);
			new Grupo(grupoHospedes, hotel).start();
		}
		
		for (Hospede hospede : hospedes) {
			hospede.start();
		}
		
		for (Recepcionista recepcionista : hotel.getRecepcionitas()) {
			recepcionista.start();
		}
		
//		for (Recepcionista recepcionista : hotel.getRecepcionitas()) {
//			recepcionista.start();
//		}
	}
}
