package sistema;

public class Funcionario extends Thread {
	private Conta contaSalario, contaInvestimento;
	private Double salario;
	
	public Funcionario(Conta contaSalario, Conta contaInvestimento, double salario) {
		this.contaSalario = contaSalario;
		this.contaInvestimento = contaInvestimento;
		this.salario = salario;
	}

	public Double getSalario() {
		return salario;
	}
	
	public void receberSalario() {
		contaSalario.adicionarSaldo(salario);
		double valorInvestimento = salario * 0.2;
		contaSalario.retirarSaldo(valorInvestimento);
		contaInvestimento.adicionarSaldo(valorInvestimento);
	}
}
