package sistema;

import java.util.ArrayList;
import java.util.HashSet;
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
    		quartos[i] = new Quarto(false, false, false);
    	}
    	
    	filaEspera = new LinkedList<>();
    	
    	recepcionistas = new Recepcionista[5];
    	semaforoRecepcionistas = new Semaphore(5);
    	for (int i = 0; i < recepcionistas.length; i ++) {
    		recepcionistas[i] = new Recepcionista(this);
    	}
    	
    	lockFilaEspera = new ReentrantLock();
    }

    public void alocarHospedes(Grupo grupo) {
        Quarto[] quartosDisponiveis = buscarQuartosDisponiveis();
        Hospede[] hospedes = grupo.getHospedes();
        int index = 0;
        int quarto = 0;
        List<Hospede> listaHospedes = new ArrayList<Hospede>();
        for (Hospede hospede : hospedes) {
        	listaHospedes.add(hospede);
        	index ++;
        	if (index > 3) {
        		Hospede[] grupoHospedes = new Hospede[listaHospedes.size()];
    			grupoHospedes = listaHospedes.toArray(grupoHospedes);
    			for (Hospede hospedeAlocado : grupoHospedes) {
    				hospedeAlocado.setQuarto(quartosDisponiveis[quarto]);
    			}
    			quartosDisponiveis[quarto].setHospedes(grupoHospedes);
    			listaHospedes.clear();
    			quarto ++;
    			index = 0;
        	}
        }
    }
    
    public void desalocarHospedes(Grupo grupo) {
    	Hospede[] hospedes = grupo.getHospedes();
    	List<Quarto> quartosOcupados = new ArrayList<Quarto>();
    	for (Hospede hospede : hospedes) {
    		Quarto quarto = hospede.getQuarto();
    		if (!quartosOcupados.contains(quarto)) {
    			quartosOcupados.add(quarto);
    		}
    	}
    	
    	for (Quarto quarto : quartosOcupados) {
    		if (quarto != null) quarto.removerHospedes();
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