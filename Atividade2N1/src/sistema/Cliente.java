package sistema;

import java.io.Console;

public class Cliente extends Thread {
	private Conta conta;
	
	public Cliente(Conta conta) {
		this.conta = conta;
	}
	
	@Override
	public void run() {
		while(true) {
			double valorCompra;
			if (Math.random() < 0.5) {
				valorCompra = 100;
			} else {
				valorCompra = 200;
			}
			synchronized(conta) {
				if (conta.getSaldo() >= valorCompra) {
					conta.retirarSaldo(valorCompra);
					System.out.println("Cliente " + this.getId() + " realizou uma compra de R$ " + valorCompra);
				} else {
					System.out.println("Cliente " + this.getId() + " sem saldo restante!");
					break;
				}
			}
		}
	}
}
