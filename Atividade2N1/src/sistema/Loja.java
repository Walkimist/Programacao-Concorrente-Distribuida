package sistema;

public class Loja {
	private Conta contaPagamento;
	private Funcionario[] funcionarios;
	
	public Loja(Conta conta, Funcionario[] funcionarios) {
		this.contaPagamento = conta;
		this.funcionarios = funcionarios;
	}
	
	public void pagarSalarios() {
		double totalSalarios = 0;
		for (Funcionario funcionario : funcionarios) {
			totalSalarios += funcionario.getSalario();
		}
		contaPagamento.retirarSaldo(totalSalarios);
		for (Funcionario funcionario : funcionarios) {
			funcionario.receberSalario();
		}
	}
}
