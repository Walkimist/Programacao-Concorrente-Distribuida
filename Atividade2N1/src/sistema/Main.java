package sistema;

public class Main {

	public static void main(String[] args) {
		Banco banco = new Banco();
		Conta contaLoja1 = new Conta(0);
		Conta contaLoja2 = new Conta(0);
		Conta contaFuncionario1 = new Conta(0);
		Conta contaFuncionario2 = new Conta(0);
		Conta contaFuncionario3 = new Conta(0);
		Conta contaFuncionario4 = new Conta(0);
		
		Funcionario[] funcionariosLoja1 = {
			new Funcionario(contaFuncionario1, new Conta(0), 1400),
			new Funcionario(contaFuncionario2, new Conta(0), 1400)
	    };
		Funcionario[] funcionariosLoja2 = {
				new Funcionario(contaFuncionario3, new Conta(0), 1400),
				new Funcionario(contaFuncionario4, new Conta(0), 1400)
		};
		
		Loja loja1 = new Loja(contaLoja1, funcionariosLoja1);
        Loja loja2 = new Loja(contaLoja2, funcionariosLoja2);
		
        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < clientes.length; i ++) {
        	clientes[i] = new Cliente(new Conta(1000));
        	clientes[i].start();
        }
        
        for (Funcionario funcionario : funcionariosLoja1) {
        	funcionario.start();
        }
        
        for (Funcionario funcionario : funcionariosLoja2) {
        	funcionario.start();
        }
	}
}
