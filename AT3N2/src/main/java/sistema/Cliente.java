package sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public Cliente(String address, int port) throws IOException {
        socket = new Socket(address, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
	
	public String enviarPedido(String pedido) throws IOException {
		out.println(pedido);
		return in.readLine();
	}

	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

}
