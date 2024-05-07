package sistema;

import java.util.Random;

public class Grupo extends Thread{
	private Hospede[] hospedes;
	private Hotel hotel;
	private int tolerancia;
	
	public Grupo(Hospede[] hospedes, Hotel hotel, int tolerancia) {
		this.hospedes = hospedes;
		this.hotel = hotel;
		this.tolerancia = tolerancia;
	}
	
	public Hospede[] getHospedes() {
		return hospedes;
	}
	
	public int getTolerancia() {
		return tolerancia;
	}
	
	public void setTolerancia(int tolerancia) {
		this.tolerancia = tolerancia;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				if (tolerancia <= 0) { return; }
                Thread.sleep(new Random().nextInt(15000)); // Tempo aleatório para simular chegada dos hóspedes
                System.out.println("Grupo " + this.getId() + " de tamanho "+ hospedes.length +" procurando fazer check-in");
                hotel.getSemaforoRecepcionistas().acquire();
                hotel.colocarNaFila(this);
                hotel.getSemaforoRecepcionistas().release();
                Thread.sleep(new Random().nextInt(22000) + 10000);
                hotel.getSemaforoRecepcionistas().acquire();
                hotel.desalocarHospedes(this);
                System.out.println("Grupo " + this.getId() + " Fizeram check-out");
                hotel.getSemaforoRecepcionistas().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	
	public synchronized void reduzirTolerancia() throws InterruptedException {
		if (tolerancia > 0) {
			System.out.println("Hotel cheio! O grupo " + this.getId() + " tentará mais " + tolerancia + " vezes");
		} else if (tolerancia == 0) {
			System.out.println("Grupo " + this.getId() + " desistiu de fazer check-in e deixou uma reclamação");
		}
    	tolerancia -= 1;
    	Grupo.sleep(200000);
	}
}
