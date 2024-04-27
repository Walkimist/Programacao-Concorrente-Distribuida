package sistema;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
	private Lock lock = new ReentrantLock();
	
	public void transferir(Conta origem, Conta destino, double valor) {
		lock.lock();
		try {
			origem.retirarSaldo(valor);
			destino.adicionarSaldo(valor);
			System.out.println(origem.getNome() + " Transferiu R$ " + valor + " para " + destino.getNome());
		} finally {
			lock.unlock();
		}
		
	}
}