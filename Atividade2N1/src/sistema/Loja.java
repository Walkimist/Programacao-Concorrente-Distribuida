package sistema;

public class Loja {
	private Conta contaPagamento;
	private Funcionario[] funcionarios;
	private Banco banco;
	
	public Loja(Conta conta, Funcionario[] funcionarios, Banco banco) {
		this.contaPagamento = conta;
		this.funcionarios = funcionarios;
		this.banco = banco;
	}
	
	public Conta getConta( ) {
		return contaPagamento;
	}
	
	public double getSaldo() {
		return contaPagamento.getSaldo();
	}
	
	public void pagarSalarios() {
		for (Funcionario funcionario : funcionarios) {
			if (!funcionario.foiPago()) {
				banco.transferir(contaPagamento, funcionario.getConta(), contaPagamento.getSaldo() >= 1400? 1400 : contaPagamento.getSaldo());
				funcionario.receberSalario();
			}
		}
	}
}
