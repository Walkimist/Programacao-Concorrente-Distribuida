package sistema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class Hotel {
    private Quarto[] quartos;
    private Recepcionista[] recepcionistas;
    private Camareira[] camareiras;
    
    private ReentrantLock lockFilaEspera;
    private Queue<Grupo> filaEspera;
    private Semaphore semaforoRecepcionistas;
    private Semaphore semaforoCamareiras;
    
    public Hotel() {
    	//Declarando quartos
    	quartos = new Quarto[10]; 
    	for (int i = 0; i < quartos.length; i ++) {
    		quartos[i] = new Quarto(i+1, false, false, false, false);
    	}
    	
    	//Declarando fila
    	filaEspera = new LinkedList<>(); 
    	
    	//Declarando recepcionistas
    	recepcionistas = new Recepcionista[5]; 
    	semaforoRecepcionistas = new Semaphore(5); //Semáforo
    	for (int i = 0; i < recepcionistas.length; i ++) {
    		recepcionistas[i] = new Recepcionista(this);
    	}
    	
    	//Declarando camareiras
    	camareiras = new Camareira[10]; 
    	semaforoCamareiras = new Semaphore(10); //Semáforo
    	for (int i = 0; i < camareiras.length; i ++) {
    		camareiras[i] = new Camareira(this);
    	}
    	
    	lockFilaEspera = new ReentrantLock(); //Lock
    }

    public synchronized void alocarHospedes(Grupo grupo, Quarto[] quartos) {
        Quarto[] quartosDisponiveis = buscarQuartosDisponiveis();
        Hospede[] hospedes = grupo.getHospedes(); //Pega todos os hospedes de um grupo
        int index = 0;
        int quarto = 0;
        List<Hospede> listaHospedes = new ArrayList<Hospede>(); //ArrayList para realizar a divisão de hospedes nos quartos
        
        for (int i = 0; i < hospedes.length; i++) { //Dentro dos hospedes do grupo
        	listaHospedes.add(hospedes[i]);
        	index ++;
        	if (index >= 4 || i == hospedes.length-1) { //Caso o quarto esteja cheio (4 hóspedes) OU não hajam mais hospedes para se adicionar
        		
        		//Convertendo ArrayList listaHospedes para array
        		Hospede[] grupoHospedes = new Hospede[listaHospedes.size()];
    			grupoHospedes = listaHospedes.toArray(grupoHospedes); 
    			
    			for (Hospede hospedeAlocado : grupoHospedes) { //Setando os quartos nos hospedes alocados
    				hospedeAlocado.setQuarto(quartosDisponiveis[quarto]);
    			}
    			quartosDisponiveis[quarto].setHospedes(grupoHospedes); //Setando hospedes nos quartos
    			listaHospedes.clear(); //Limpar lista para possível iteração em outros quartos
    			quarto ++;
    			index = 0;
        	}
        }
        System.out.println(grupo.getName() + " fez check-in");
    }
    
    public synchronized void desalocarHospedes(Grupo grupo) {
    	Hospede[] hospedes = grupo.getHospedes(); //Pega todos os hospedes de um grupo
    	List<Quarto> quartosOcupados = new ArrayList<Quarto>(); //ArrayList para realizar a remoção de hospedes dos quartos
    	
    	for (Hospede hospede : hospedes) {
    		Quarto quarto = hospede.getQuarto();
    		if (!quartosOcupados.contains(quarto)) { //Checa se a lista já possui o quarto, garantindo que não haverá execução dupla da remoção
    			quartosOcupados.add(quarto);
    		}
    	}
    	
    	for (Quarto quarto : quartosOcupados) { //Itera na lista filtrada de quartos
    		if (quarto != null) quarto.removerHospedes();
    	}
    }

    public Quarto[] buscarQuartosDisponiveis() {
        List<Quarto> listaQuartosDisponiveis = new ArrayList<Quarto>(); //Lista para armazenar os quartos disponiveis
        for (Quarto quarto : quartos) { //Itera em todos os quartos e verifica se está disponível
            if (!quarto.isOcupado() && !quarto.isEmLimpeza()) {
                listaQuartosDisponiveis.add(quarto);
            }
        }
        //Conversão de ArrayList para array
        Quarto[] quartosDisponiveis = new Quarto[listaQuartosDisponiveis.size()];
        quartosDisponiveis = listaQuartosDisponiveis.toArray(quartosDisponiveis);

        return quartosDisponiveis; //retorna o array convertido
    }
    
    public boolean checarQuartosDisponiveis() {
    	if (buscarQuartosDisponiveis().length <= 0) {
    		return false;
    	}
    	return true;
    }
    
    public synchronized void colocarNaFila(Grupo grupo) {
        lockFilaEspera.lock();
        try {
            filaEspera.add(grupo); //Adiciona grupo na fila para check-in
            System.out.println(grupo.getName() + " colocado na fila");
        } finally {
            lockFilaEspera.unlock();
        }
    }

    public Grupo getProximoGrupoFilaEspera() {
        lockFilaEspera.lock();
        try {
        	if (!filaEspera.isEmpty()) { //Retorna o proximo grupo, apenas se não for nulo
        		return filaEspera.element();
        	}
            return null;
        } finally {
            lockFilaEspera.unlock();
        }
    }
    
    public void removerGrupoFila() {
    	filaEspera.remove();
    }
    
    //Getters
    public Queue<Grupo> getFilaEspera() {
    	return filaEspera;
    }

    public Semaphore getSemaforoRecepcionistas() {
        return semaforoRecepcionistas;
    }
    
    public Semaphore getSemaforoCamareiras() {
		return semaforoCamareiras;
	}
    
    public Recepcionista[] getRecepcionitas() {
    	return recepcionistas;
    }

	public Camareira[] getCamareiras() {
		return camareiras;
	}
	
	public Quarto[] getQuartos() {
		return quartos;
	}
}