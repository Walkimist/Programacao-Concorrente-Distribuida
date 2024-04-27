package sistema;

public class Loja {
	private Conta contaPagamento;
	private Funcionario[] funcionarios;
	private Banco banco;
	
	//Construtor
	public Loja(Conta conta, Funcionario[] funcionarios, Banco banco) {
		this.contaPagamento = conta;
		this.funcionarios = funcionarios;
		this.banco = banco;
	}
	
	//Getters
	public Conta getConta( ) {
		return contaPagamento;
	}
	
	public double getSaldo() {
		return contaPagamento.getSaldo();
	}
	
	//Funções
	public void pagarSalarios() {
		for (Funcionario funcionario : funcionarios) {
			if (!funcionario.foiPago()) {
				banco.transferir(contaPagamento, funcionario.getConta(), contaPagamento.getSaldo() >= 1400? 1400 : contaPagamento.getSaldo()); //Caso tenha 1400 ou mais, pagar funcionario, se não, pagar saldo restante
				funcionario.receberSalario();
			}
		}
	}
}
