package sistema;

public class Cliente extends Thread {
	private Conta conta;
	private Loja[] lojas;
	private Banco banco;
	
	public Cliente(Conta conta, Loja[] lojas, Banco banco) {
		this.conta = conta;
		this.lojas = lojas;
		this.banco = banco;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(true) {
			double valorCompra;
			if (Math.random() < 0.5) {
				valorCompra = 100;
			} else {
				valorCompra = 200;
			}
			synchronized(conta) {
				if (conta.getSaldo() > 0) {
					if (valorCompra > conta.getSaldo()) valorCompra = conta.getSaldo();
					if (i % 2 == 0) {
						banco.transferir(conta, lojas[0].getConta(), valorCompra);
						System.out.println("Cliente " + this.getId() + " realizou uma compra de R$ " + valorCompra + " na loja 1");
					} else {
						banco.transferir(conta, lojas[1].getConta(), valorCompra);
						System.out.println("Cliente " + this.getId() + " realizou uma compra de R$ " + valorCompra + " na loja 2");
					}
					i++;
				} else {
					System.out.println("Cliente " + this.getId() + " sem saldo restante para compra!");
					System.out.println("Saldo Loja 1: R$ " + lojas[0].getSaldo());
					System.out.println("Saldo Loja 2: R$ " + lojas[1].getSaldo());
					break;
				}
			}
		}
	}
}
