package sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	public Cliente(String address, int port) throws IOException {
        socket = new Socket(address, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
	
	public void enviarPedido(String request) {
        out.println(request);
    }
	
	public String receberResposta() throws IOException {
        return in.readLine();
    }

	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
            Cliente cliente = new Cliente("localhost", 12345);
            
            System.out.print("Comandos do servidor:\n"
            		+ "'LISTAR' - Lista todos os livros em estoque\n"
            		+ "'CADASTRAR <Nome-Do-Autor> <Nome-Do-Livro> <Genero> <Numero de Exemplares>'\n"
            		+ "'ALUGAR <Nome-Do-Livro>'\n"
            		+ "'DEVOLVER <Nome-Do-Livro>'\n\n"
            		+ "Sempre que for colocar um nome composto em qualquer campo, separar cada palavra por '-', exemplo: 'Senhor-Dos-Anéis'\n\n> ");
            
            Scanner sc = new Scanner(System.in);
            String entrada = sc.nextLine();
            
            cliente.enviarPedido(entrada);
            String resposta = cliente.receberResposta().replace("Livro", "\nLivro");
            
            System.out.print("\nResposta do servidor:" + resposta + '\n');
            
            sc.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
