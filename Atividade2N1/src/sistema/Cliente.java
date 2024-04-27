package sistema;

public class Cliente extends Thread {
	private Conta conta;
	private Loja[] lojas;
	private Banco banco;
	
	//Construtor
	public Cliente(Conta conta, Loja[] lojas, Banco banco) {
		this.conta = conta;
		this.lojas = lojas;
		this.banco = banco;
	}
	
	//Getters
	public double getSaldo() {
		return conta.getSaldo();
	}
	
	//Funções
	@Override
	public void run() {
		int i = 0;
		while(true) {
			double valorCompra;
			if (Math.random() < 0.5) { //Aleatóriamente escolhe entre 100 e 200 para valor da compra, com 50% de chance
				valorCompra = 100;
			} else {
				valorCompra = 200;
			}
			synchronized(conta) {
				if (conta.getSaldo() > 0) { //Checar se há dinheiro em conta
					if (valorCompra > conta.getSaldo()) valorCompra = conta.getSaldo(); //Caso tenha menos que o valor escolhido aleatoriamente, usar resto do saldo na loja
					if (i % 2 == 0) { //Alterna entre as lojas
						banco.transferir(conta, lojas[0].getConta(), valorCompra);
					} else {
						banco.transferir(conta, lojas[1].getConta(), valorCompra);;
					}
					i++;
				} else { //Quando acabar o saldo
					break;
				}
			}
		}
	}
}
