package sistema;

public class Banco {
	public void transferir(Conta origem, Conta destino, double valor) {
		origem.retirarSaldo(valor);
		destino.adicionarSaldo(valor);
	}
}
