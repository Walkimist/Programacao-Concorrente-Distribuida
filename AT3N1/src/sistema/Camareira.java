package sistema;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread {
	private Hotel hotel;
	private Lock lock;
	
	public Camareira(Hotel hotel) {
		this.hotel = hotel;
		this.lock = new ReentrantLock();
	}
	
	public synchronized void limparQuarto(Quarto quarto) {
		lock.lock();
        try {
        	quarto.setEmLimpeza(true);
            Thread.sleep(2000); // Simula o tempo de limpeza
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        	quarto.setEmLimpeza(false);
        	lock.unlock();
        }
	}
}
