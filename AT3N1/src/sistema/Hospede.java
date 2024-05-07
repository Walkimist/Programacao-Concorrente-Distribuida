package sistema;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Hospede extends Thread {
	private Quarto quarto;
	private Hotel hotel;
	private ReentrantLock lock;
	
	public Hospede(Hotel hotel) {
		this.quarto = null;
		this.hotel = hotel;
		this.lock = new ReentrantLock();
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public Quarto getQuarto() {
		return quarto;
	}

	@Override
	public void run() {
		while (true) {
			if (quarto != null) {
				try {
					Thread.sleep(new Random().nextInt(15000));
					System.out.println("Quarto " + quarto.getId() + " livre para limpeza");
					quarto.livreParaLimpeza();
					while(quarto.isEmLimpeza()) {
						Thread.sleep(new Random().nextInt(5000));
						
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}