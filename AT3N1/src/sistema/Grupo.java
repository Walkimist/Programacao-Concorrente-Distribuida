package sistema;

import java.util.Random;

public class Grupo extends Thread{
	private Hospede[] hospedes;
	private Hotel hotel;
	private int tolerancia; //Conta as tentativas do grupo de fazer check-in
	private static int count = 1;
	
	public Grupo(Hospede[] hospedes, Hotel hotel, int tolerancia) {
		this.hospedes = hospedes;
		this.hotel = hotel;
		this.tolerancia = tolerancia;
		this.setName("Grupo " + count); //Nomeia a thread
		count++;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				if (tolerancia <= 0) { return; } //Finaliza a thread caso tenha tentado o número máximo de vezes
                Thread.sleep(new Random().nextInt(15000)); // Tempo aleatório para simular chegada dos hóspedes
                System.out.println(this.getName() + " de tamanho "+ hospedes.length +" procurando fazer check-in");
                
                //Faz check-in, utilizando semáforos nas recepcionistas
                hotel.getSemaforoRecepcionistas().acquire();
                hotel.colocarNaFila(this);
                hotel.getSemaforoRecepcionistas().release();
                
                Thread.sleep(new Random().nextInt(22000) + 10000); //Simula tempo de estadia
                
                //Faz check-out, utilizando semáforos nas recepcionistas
                hotel.getSemaforoRecepcionistas().acquire();
                hotel.desalocarHospedes(this);
                System.out.println(this.getName() + " Fizeram check-out");
                hotel.getSemaforoRecepcionistas().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
	public synchronized void reduzirTolerancia() throws InterruptedException {
		if (tolerancia > 0) { //Enquanto ainda tem tolerância, realiza novas tentativas
			System.out.println("Hotel cheio! O " + this.getName() + " tentará mais " + tolerancia + " vezes");
		} else if (tolerancia == 0) { //Caso não tenha mais tolerância, fazer reclamação e ir embora
			System.out.println(this.getName() + " desistiu de fazer check-in e deixou uma reclamação");
		}
    	tolerancia -= 1;
    	Grupo.sleep(20000); //Dar uma volta na cidade antes de tentar de novo
	}
	
	//Getters
	public Hospede[] getHospedes() {
		return hospedes;
	}
		
	public int getTolerancia() {
		return tolerancia;
	}
		
	//Setters
	public void setTolerancia(int tolerancia) {
		this.tolerancia = tolerancia;
	}
}
