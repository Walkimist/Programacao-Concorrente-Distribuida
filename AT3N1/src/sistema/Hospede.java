package sistema;

import java.util.Random;

public class Hospede extends Thread {
	private Quarto quarto;
	
	public Hospede() {
		this.quarto = null;
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
					quarto.setChaveNoQuarto(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}