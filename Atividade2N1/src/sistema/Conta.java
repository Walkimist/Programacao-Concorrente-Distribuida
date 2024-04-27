package sistema;

public class Conta {
	private double saldo;
	private String nome;
	
	public Conta(double saldo, String nome) {
		this.saldo = saldo;
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
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
