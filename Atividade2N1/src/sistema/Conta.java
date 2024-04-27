package sistema;

public class Conta {
	private double saldo;

	public Conta(double saldo) {
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void adicionarSaldo(double valor) {
		saldo += valor;
	}
	
	public void retirarSaldo(double valor) {
		saldo -= valor;
	}
}
