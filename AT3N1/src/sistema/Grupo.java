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
                Thread.sleep(new Random().nextInt(5000)); // Tempo aleatório para simular chegada dos hóspedes
                hotel.getSemaforoRecepcionistas().acquire();
                hotel.colocarNaFila(this);
                hotel.getSemaforoRecepcionistas().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
}
