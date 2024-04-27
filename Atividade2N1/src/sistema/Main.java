package sistema;

public class Main {

	public static void main(String[] args) {
		Banco banco = new Banco();
		Conta contaLoja1 = new Conta(0, "Loja 1");
		Conta contaLoja2 = new Conta(0, "Loja 2");
		Conta contaFuncionario1 = new Conta(0, "Funcionario 1");
		Conta contaFuncionario2 = new Conta(0, "Funcionario 2");
		Conta contaFuncionario3 = new Conta(0, "Funcionario 3");
		Conta contaFuncionario4 = new Conta(0, "Funcionario 4");
		
		Funcionario[] funcionariosLoja1 = {
			new Funcionario(contaFuncionario1, new Conta(0, "Investimento Funcionario 1"), banco),
			new Funcionario(contaFuncionario2, new Conta(0, "Investimento Funcionario 2"), banco)
	    };
		Funcionario[] funcionariosLoja2 = {
				new Funcionario(contaFuncionario3, new Conta(0, "Investimento Funcionario 3"), banco),
				new Funcionario(contaFuncionario4, new Conta(0, "Investimento Funcionario 4"), banco)
		};
		
		Loja[] lojas = { 
				new Loja(contaLoja1, funcionariosLoja1, banco),
				new Loja(contaLoja2, funcionariosLoja2, banco)
		};
		
        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < clientes.length; i ++) {
        	clientes[i] = new Cliente(new Conta(1000, "Cliente " + String.valueOf(i+1)), lojas, banco);
        	clientes[i].start();
        }
        
        for (Funcionario funcionario : funcionariosLoja1) {
        	funcionario.start();
        }
        
        for (Funcionario funcionario : funcionariosLoja2) {
        	funcionario.start();
        }
        
        System.out.println("---| TRANSFERENCIAS CLIENTES-LOJAS |--- ");
        
        for (Cliente cliente : clientes) {
            try {
            	cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\n---| SALDO LOJAS |--- ");
        System.out.println("Saldo Loja 1: R$ " + lojas[0].getSaldo());
		System.out.println("Saldo Loja 2: R$ " + lojas[1].getSaldo());
		
        System.out.println("\n---| TRANSFERENCIAS LOJAS-FUNCIONARIOS |--- ");
        
        for (Loja loja : lojas) {
        	loja.pagarSalarios();
        }
        
        System.out.println("\n---| NOVO SALDO LOJAS |--- ");
        System.out.println("Saldo Loja 1: R$ " + lojas[0].getSaldo());
		System.out.println("Saldo Loja 2: R$ " + lojas[1].getSaldo());
		
		System.out.println("\n---| SALDO CLIENTES |--- ");
		System.out.println("Cliente 1: R$ " + clientes[0].getSaldo());
		System.out.println("Cliente 2: R$ " + clientes[1].getSaldo());
		System.out.println("Cliente 3: R$ " + clientes[2].getSaldo());
		System.out.println("Cliente 4: R$ " + clientes[3].getSaldo());
		System.out.println("Cliente 5: R$ " + clientes[4].getSaldo());
		
		
		System.out.println("\n---| SALDO FUNCIONÁRIOS |--- ");
		System.out.println("Saldo Funcionario 1: R$ " + funcionariosLoja1[0].getSaldo());
		System.out.println("Investimento Funcionario 1: R$ " + funcionariosLoja1[0].getInvestimento());
		System.out.println("\nSaldo Funcionario 2: R$ " + funcionariosLoja1[1].getSaldo());
		System.out.println("Investimento Funcionario 2: R$ " + funcionariosLoja1[1].getInvestimento());
		System.out.println("\nSaldo Funcionario 3: R$ " + funcionariosLoja2[0].getSaldo());
		System.out.println("Investimento Funcionario 3: R$ " + funcionariosLoja2[0].getInvestimento());
		System.out.println("\nSaldo Funcionario 4: R$ " + funcionariosLoja2[1].getSaldo());
		System.out.println("Investimento Funcionario 4: R$ " + funcionariosLoja2[1].getInvestimento());
	}
}
