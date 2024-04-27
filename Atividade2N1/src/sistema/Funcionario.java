package sistema;

public class Funcionario extends Thread {
	private Conta contaSalario, contaInvestimento;
	private boolean funcionarioPago;
	private Banco banco;
	
	public Funcionario(Conta contaSalario, Conta contaInvestimento, Banco banco) {
		this.contaSalario = contaSalario;
		this.contaInvestimento = contaInvestimento;
		this.banco = banco;
		funcionarioPago = false;
	}
	
	public Conta getConta( ) {
		return contaSalario;
	}
	
	public boolean foiPago() {
		return funcionarioPago;
	}
	
	public void receberSalario() {
		double valorInvestimento = contaSalario.getSaldo() * 0.2;
		banco.transferir(contaSalario, contaInvestimento, valorInvestimento);
		funcionarioPago = true;
	}

	public double getSaldo() {
		return contaSalario.getSaldo();
	}
	
	public double getInvestimento() {
		return contaInvestimento.getSaldo();
	}
	
}
