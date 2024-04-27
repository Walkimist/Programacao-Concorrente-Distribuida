package sistema;

public class Conta {
	private double saldo;
	private String nome;
	
	//Construtor
	public Conta(double saldo, String nome) {
		this.saldo = saldo;
		this.nome = nome;
	}
	
	//Getters
	public String getNome() {
		return nome;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	//Transações
	public void adicionarSaldo(double valor) {
		saldo += valor;
	}
	
	public void retirarSaldo(double valor) {
		saldo -= valor;
	}
}
