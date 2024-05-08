package sistema;

import java.util.Random;

public class Hospede extends Thread {
	private Quarto quarto;
	
	public Hospede() {
		this.quarto = null;
	}
	
	//Getters
	public Quarto getQuarto() {
		return quarto;
	}
	
	//Setters
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	@Override
	public void run() {
		while (true) {
			if (quarto != null) { //Se estiver alocado
				try {
					Thread.sleep(new Random().nextInt(15000)); //Simula tempo aleatório antes de sair para passear
					System.out.println("Quarto " + quarto.getId() + " livre para limpeza");
					quarto.livreParaLimpeza(); //Libera quarto
					while(quarto.isEmLimpeza()) { //Caso não esteja limpo, espera um tempo antes de tentar novamente
						Thread.sleep(5000);
					}
					quarto.setChaveNoQuarto(true); //Retorna pro quarto
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}