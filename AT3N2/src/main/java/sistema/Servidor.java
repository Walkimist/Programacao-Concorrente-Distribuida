package sistema;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
	private static final String ARQUIVO_LIVROS = "livros.json";
	private List<Livro> livros;
	
	public Servidor() {
		carregarLivros();
	}
	
	private void carregarLivros() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			livros = mapper.readValue(new File(ARQUIVO_LIVROS), new TypeReference<List<Livro>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void salvarLivro() {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_LIVROS), livros);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public synchronized String lidarComPedido(String pedido) {
        String[] partes = pedido.split(" ");
        String comando = partes[0];

        switch (comando) {
            case "LISTAR":
            case "ALUGAR":
            case "DEVOLVER":
            case "CADASTRAR":
            default:
                return "Comando Inválido: '" + partes[0] + "'";
        }
    }
	
	public void start() {
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor da biblioteca está rodando...");
            while (true) {
                try (Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                	
                    String pedido = in.readLine();
                    String resposta = lidarComPedido(pedido);
                    out.println(resposta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
}
