package sistema;

import java.util.ArrayList;
import java.util.List;

public class Recepcionista {
	private Quarto[] quartos;
	private Grupo[] grupos;
	
	public Recepcionista(Quarto[] quartos, Grupo[] grupos) {
		this.grupos = grupos;
		this.quartos = quartos;
	}
	
	public void alocarHospedes(Quarto[] quartos, Grupo grupo) {
		Hospede[] hospedes = grupo.getHospedes();
		int index = 0;
		for (Hospede hospede : hospedes) {
			hospede.setQuarto(quartos[index]);
			quartos[index].setQuantidadeHospedes(quartos[index].getQuantidadeHospedes() + 1);
			if (quartos[index].getQuantidadeHospedes() == 4) {
				index ++;
			}
		}
		for (int i = 0; i <= index; i ++) {
			quartos[i].setOcupado(true);
		}
	}
	
	public Quarto[] buscarQuartosDisponiveis() {
		List<Quarto> listaQuartosDisponiveis = new ArrayList<Quarto>();
		for (Quarto quarto : quartos) {
			if (!quarto.isOcupado()) {
				listaQuartosDisponiveis.add(quarto);
			}
		}
		Quarto[] quartosDisponiveis = new Quarto[listaQuartosDisponiveis.size()];
		quartosDisponiveis = listaQuartosDisponiveis.toArray(quartosDisponiveis);
		
		return quartosDisponiveis;
	}
}
