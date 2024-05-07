package sistema;

import java.util.Random;

public class Grupo extends Thread{
	private Hospede[] hospedes;
	private Hotel hotel;
	
	public Grupo(Hospede[] hospedes, Hotel hotel) {
		this.hospedes = hospedes;
		this.hotel = hotel;
	}
	
	public Hospede[] getHospedes() {
		return hospedes;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
                Thread.sleep(new Random().nextInt(15000)); // Tempo aleatório para simular chegada dos hóspedes
                System.out.println("Grupo " + this.getId() + " procurando fazer check-in");
                hotel.getSemaforoRecepcionistas().acquire();
                System.out.println("Grupo " + this.getId() + " colocado na fila");
                hotel.colocarNaFila(this);
                hotel.getSemaforoRecepcionistas().release();
                Thread.sleep(new Random().nextInt(22000));
                hotel.getSemaforoRecepcionistas().acquire();
                hotel.desalocarHospedes(this);
                System.out.println("Grupo " + this.getId() + " Fizeram check-out");
                hotel.getSemaforoRecepcionistas().release();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
}
