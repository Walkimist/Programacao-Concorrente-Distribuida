package sistema;

public class Funcionario extends Thread {
	private Conta contaSalario, contaInvestimento;
	private boolean funcionarioPago;
	private Banco banco;
	
	//Construtor
	public Funcionario(Conta contaSalario, Conta contaInvestimento, Banco banco) {
		this.contaSalario = contaSalario;
		this.contaInvestimento = contaInvestimento;
		this.banco = banco;
		funcionarioPago = false;
	}
	
	//Getters
	public Conta getConta( ) {
		return contaSalario;
	}
	
	public double getSaldo() {
		return contaSalario.getSaldo();
	}
	
	public double getInvestimento() {
		return contaInvestimento.getSaldo();
	}
	
	public boolean foiPago() {
		return funcionarioPago;
	}
	
	//Funções
	public void receberSalario() {
		double valorInvestimento = contaSalario.getSaldo() * 0.2; //20% do salário é investido 
		banco.transferir(contaSalario, contaInvestimento, valorInvestimento);
		funcionarioPago = true;
	}
}
