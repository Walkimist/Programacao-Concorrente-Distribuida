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
import java.util.stream.Collectors;

public class Servidor {
	private static final String ARQUIVO_LIVROS = "src/main/java/resources/livros.json";
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
	
	private void salvarLivros() {
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_LIVROS), livros);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    carregarLivros();
	}
	
	private String listarLivros() {
		String listagem = livros.stream()
                .map(Livro::toString)
                .collect(Collectors.joining(","));
        return listagem;
    }
	
	private String alugarLivro(String nome) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome) && livro.getNumeroDeExemplares() > 0) {
            	livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() - 1);
            	salvarLivros();
                return "Livro alugado com sucesso!";
            }
        }
        return "Livro não disponível.";
    }
	
	private String devolverLivro(String nome) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome)) {
            	livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() + 1);
            	salvarLivros();
                return "Livro devolvido com sucesso!";
            }
        }
        return "Livro não encontrado.";
    }
	
	private String cadastrarLivro(String autor, String nome, String genero, int numeroDeExemplares) {
        livros.add(new Livro(autor, nome, genero, numeroDeExemplares));
        salvarLivros();
        return "Livro cadastrado com sucesso!";
    }
	
	public synchronized String lidarComPedido(String pedido) {
        String[] partes = pedido.split(" ");
        String comando = partes[0];

        switch (comando) {
            case "LISTAR":
            	return listarLivros();
            case "ALUGAR":
            	return alugarLivro(partes[1]);
            case "DEVOLVER":
            	return devolverLivro(partes[1]);
            case "CADASTRAR":
            	return cadastrarLivro(partes[1], partes[2], partes[3], Integer.parseInt(partes[4]));
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
	
	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.start();
	}
}
