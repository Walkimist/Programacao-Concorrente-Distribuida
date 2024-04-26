package sistema;

public class Cliente extends Thread {
	private Conta conta;
	
	public Cliente(Conta conta) {
		this.conta = conta;
	}
}
