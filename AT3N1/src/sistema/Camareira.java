package sistema;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread {
	private Hotel hotel;
	private Lock lock;
	
	public Camareira(Hotel hotel) {
		this.hotel = hotel;
		this.lock = new ReentrantLock();
	}
	
	@Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(20000)); // Tempo aleatório para simular limpeza dos quartos
                hotel.getSemaforoCamareiras().acquire();
                limparQuartos();
                hotel.getSemaforoCamareiras().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
	public synchronized void limparQuartos() throws InterruptedException {
		for (Quarto quarto : hotel.getQuartos()) {
            synchronized (quarto) {
                if (!quarto.isChaveNoQuarto() && quarto.isSujo()) {
                	quarto.setEmLimpeza(true);
                    System.out.println(getName() + " limpando quarto " + quarto.getId());
                    Thread.sleep(2000);
                    quarto.setEmLimpeza(false);
                    System.out.println(getName() + " limpou o quarto " + quarto.getId());
                }
            }
        }
	}
}
