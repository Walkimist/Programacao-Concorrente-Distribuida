package sistema;

public class Loja {
	private Conta contaPagamento;
	private Funcionario[] funcionarios;
	
	public Loja(Conta conta, Funcionario[] funcionarios) {
		this.contaPagamento = conta;
		this.funcionarios = funcionarios;
	}
	
	public Conta getConta( ) {
		return contaPagamento;
	}
	
	public double getSaldo() {
		return contaPagamento.getSaldo();
	}
	
	public void pagarSalarios() {
		
	}
}
