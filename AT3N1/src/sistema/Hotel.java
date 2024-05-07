package sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class Hotel extends Thread {
    private Quarto[] quartos;
    private Grupo[] grupos;
    private Recepcionista[] recepcionistas;
    private Camareira[] camareiras;
    
    private ReentrantLock lockFilaEspera;
    private Queue<Grupo> filaEspera;
    //private Semaphore semaforoCamareiras;
    private Semaphore semaforoRecepcionistas;
    
    public Hotel() {
    	quartos = new Quarto[10];
    	for (int i = 0; i < quartos.length; i ++) {
    		quartos[i] = new Quarto(0, false, false, false);
    	}
    	
    	filaEspera = new LinkedList<>();
    	
    	recepcionistas = new Recepcionista[5];
    	for (int i = 0; i < recepcionistas.length; i ++) {
    		recepcionistas[i] = new Recepcionista(this);
    	}
    	
    	lockFilaEspera = new ReentrantLock();
    }

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

    public Grupo getProximoGrupoFilaEspera() {
        lockFilaEspera.lock();
        try {
            return filaEspera.poll();
        } finally {
            lockFilaEspera.unlock();
        }
    }
    
    public Queue<Grupo> getFilaEspera() {
    	return filaEspera;
    }
    
    //public Semaphore getSemaforoCamareiras() {
    //    return semaforoCamareiras;
    //}

    public Semaphore getSemaforoRecepcionistas() {
        return semaforoRecepcionistas;
    }
    
    public Recepcionista[] getRecepcionitas() {
    	return recepcionistas;
    }
}