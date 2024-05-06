package sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

class Hotel extends Thread {
    private Quarto[] quartos;
    private Grupo[] grupos;
    private Recepcionista[] recepcionistas;
    private Camareira[] camareiras;

    private ReentrantLock lockFilaEspera;
    private Queue<Grupo> filaEspera;
    
    public Hotel() {}

    public void alocarHospedes(Grupo grupo) {
        Quarto[] quartosDisponiveis = buscarQuartosDisponiveis();
        Hospede[] hospedes = grupo.getHospedes();
        int index = 0;
        for (Hospede hospede : hospedes) {
            hospede.setQuarto(quartosDisponiveis[index]);
            quartosDisponiveis[index].setQuantidadeHospedes(quartosDisponiveis[index].getQuantidadeHospedes() + 1);
            if (quartosDisponiveis[index].getQuantidadeHospedes() == 4) {
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
            if (!quarto.isOcupado() && !quarto.isEmLimpeza()) {
                listaQuartosDisponiveis.add(quarto);
            }
        }
        Quarto[] quartosDisponiveis = new Quarto[listaQuartosDisponiveis.size()];
        quartosDisponiveis = listaQuartosDisponiveis.toArray(quartosDisponiveis);

        return quartosDisponiveis;
    }
    
    public void colocarNaFila(Grupo grupo) {
        lockFilaEspera.lock();
        try {
            filaEspera.add(grupo);
        } finally {
            lockFilaEspera.unlock();
        }
    }

    public Grupo getProximoHospedeFilaEspera() {
        lockFilaEspera.lock();
        try {
            return filaEspera.poll();
        } finally {
            lockFilaEspera.unlock();
        }
    }
}