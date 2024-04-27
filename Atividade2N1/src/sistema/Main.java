package sistema;

public class Main {

	public static void main(String[] args) {
		Banco banco = new Banco();
		Conta contaLoja1 = new Conta(0, "loja1");
		Conta contaLoja2 = new Conta(0, "loja2");
		Conta contaFuncionario1 = new Conta(0, "funcionario1");
		Conta contaFuncionario2 = new Conta(0, "funcionario2");
		Conta contaFuncionario3 = new Conta(0, "funcionario3");
		Conta contaFuncionario4 = new Conta(0, "funcionario4");
		
		Funcionario[] funcionariosLoja1 = {
			new Funcionario(contaFuncionario1, new Conta(0, "investimentoFuncionario1"), banco),
			new Funcionario(contaFuncionario2, new Conta(0, "investimentoFuncionario2"), banco)
	    };
		Funcionario[] funcionariosLoja2 = {
				new Funcionario(contaFuncionario3, new Conta(0, "investimentoFuncionario3"), banco),
				new Funcionario(contaFuncionario4, new Conta(0, "investimentoFuncionario4"), banco)
		};
		
		Loja[] lojas = { 
				new Loja(contaLoja1, funcionariosLoja1, banco),
				new Loja(contaLoja2, funcionariosLoja2, banco)
		};
		
        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < clientes.length; i ++) {
        	clientes[i] = new Cliente(new Conta(1000, "cliente" + String.valueOf(i+1)), lojas, banco);
        	clientes[i].start();
        }
        
        for (Funcionario funcionario : funcionariosLoja1) {
        	funcionario.start();
        }
        
        for (Funcionario funcionario : funcionariosLoja2) {
        	funcionario.start();
        }
        
        for (Cliente cliente : clientes) {
            try {
            	cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        for (Loja loja : lojas) {
        	loja.pagarSalarios();
        }
     
	}
}
