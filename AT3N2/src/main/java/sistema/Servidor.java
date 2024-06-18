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
	//Aponta para a fonte do banco do livros
	private static final String ARQUIVO_LIVROS = "src/main/java/resources/livros.json";
	private List<Livro> livros;
	
	public Servidor() { //Construtor apenas carrega os livros
		carregarLivros();
	}
	
	private void carregarLivros() {
		//Cria uma instância de ObjectMapper, que é usada para ler e escrever objetos JSON
		ObjectMapper mapper = new ObjectMapper();
		try {
			//Lê o arquivo JSON especificado por ARQUIVO_LIVROS e converte seu conteúdo em uma lista de livros
			livros = mapper.readValue(new File(ARQUIVO_LIVROS), new TypeReference<List<Livro>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void salvarLivros() {
		//Leitura e escrida de objetos JSON.
	    ObjectMapper mapper = new ObjectMapper();
	    try {
	        //Escreve os livros e formata o JSON com indentação para legibilidade
	        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO_LIVROS), livros);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    //Recarrega os livros para sincronização
	    carregarLivros();
	}
	
	private String listarLivros() {
		//Stream processa os livros
		String listagem = livros.stream()
                .map(Livro::toString)	// toString() em cada livro da lista
                .collect(Collectors.joining(","));	// Junta todas as strings resultantes, separando-as por vírgulas.
        return listagem;
    }
	
	private String alugarLivro(String nome) {
        for (Livro livro : livros) {
        	//Verifica se o livro está na lista (ignorando letras maiúsculas), e se ainda está em estoque
            if (livro.getNome().equalsIgnoreCase(nome) && livro.getNumeroDeExemplares() > 0) {
            	//Reduz o número em estoque e salva os livros
            	livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() - 1);
            	salvarLivros();
                return "Livro alugado com sucesso!";
            }
        }
        return "Livro não disponível.";
    }
	
	private String devolverLivro(String nome) {
        for (Livro livro : livros) {
        	//Verifica se o livro está na lista (ignorando letras maiúsculas)
            if (livro.getNome().equalsIgnoreCase(nome)) {
            	//Aumenta o número em estoque e salva os livros
            	livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() + 1);
            	salvarLivros();
                return "Livro devolvido com sucesso!";
            }
        }
        return "Livro não encontrado.";
    }
	
	private String cadastrarLivro(String autor, String nome, String genero, int numeroDeExemplares) {
		//Cadastra livro com dados fornecidos e salva a lista atualizada
        livros.add(new Livro(autor, nome, genero, numeroDeExemplares));
        salvarLivros();
        return "Livro cadastrado com sucesso!";
    }
	
	public synchronized String lidarComPedido(String pedido) {
		//Usa barras de espaço para separar o comando em partes
        String[] partes = pedido.split(" ");
        String comando = partes[0];
        
        //Busca se o comando usado está na lista
        switch (comando) {
            case "LISTAR":
            	return listarLivros();
            case "ALUGAR":
            	return alugarLivro(partes[1]); //partes[1] se refere ao nome do livro mencionado
            case "DEVOLVER":
            	return devolverLivro(partes[1]); //partes[1] se refere ao nome do livro mencionado
            case "CADASTRAR":
            	return cadastrarLivro(partes[1], partes[2], partes[3], Integer.parseInt(partes[4])); //Cadastra livro com a informação das partes do comando
            default:
                return "Comando Inválido: '" + partes[0] + "'";
        }
    }
	
	public void start() {
		//Cria um ServerSocket que escuta na porta 12345
		try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor da biblioteca está rodando...");
            
            //Loop para aceitar e processar conexões de clientes
            while (true) {
                try (Socket socket = serverSocket.accept();
                	// BufferedReader para entrada do cliente
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                	// PrintWriter para saída
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                	
                	// Lê o pedido do cliente
                    String pedido = in.readLine();
                    // Processa o pedido e envia a resposta
                    String resposta = lidarComPedido(pedido);
                    out.println(resposta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		//Instancia e inicia o servidor ao rodar
		Servidor servidor = new Servidor();
		servidor.start();
	}
}
