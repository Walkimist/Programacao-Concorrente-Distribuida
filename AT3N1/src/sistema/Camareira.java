package sistema;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Camareira extends Thread {
	private Hotel hotel;
	private static int count = 1;
	
	public Camareira(Hotel hotel) {
		this.hotel = hotel;
		this.setName("Camareira " + count);
		count++;
	}
	
	@Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(20000));  //Tempo aleatório para simular limpeza dos quartos
                //Usa semáforo para limpar quartos e garantir que só 1 camareira vai limpar o quarto por vez
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
                	System.out.println(this.getName() + " limpando quarto " + quarto.getId());
                	Thread.sleep(5000); //Simulando tempos de limpeza
                    quarto.setEmLimpeza(false);
                    quarto.setSujo(false);
                    System.out.println("Quarto " + quarto.getId() + " limpo!");
                }
            }
        }
	}
}
