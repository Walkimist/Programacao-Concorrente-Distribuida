package sistema;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	//Para conexão de socket e comunicação
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	
	//Construtor para conectar com o servidor
	public Cliente(String address, int port) throws IOException {
		//Inicializa socket com ip e porta
        socket = new Socket(address, port);
        //Inicializa leitor e escritor para receber e enviar mensagens ao servidor
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
	
	public void enviarPedido(String request) {
        out.println(request);
    }
	
	public String receberResposta() throws IOException {
        return in.readLine();
    }
	
	//Fecha a conexão do cliente
	public void close() throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			//Cria um novo cliente conectado ao servidor localhost na porta 12345
            Cliente cliente = new Cliente("localhost", 12345);
            
            //Exibe os comandos disponíveis para o usuário
            System.out.print("Comandos do servidor:\n"
            		+ "'LISTAR' - Lista todos os livros em estoque\n"
            		+ "'CADASTRAR <Nome-Do-Autor> <Nome-Do-Livro> <Genero> <Numero de Exemplares>'\n"
            		+ "'ALUGAR <Nome-Do-Livro>'\n"
            		+ "'DEVOLVER <Nome-Do-Livro>'\n\n"
            		+ "Sempre que for colocar um nome composto em qualquer campo, substituir os espaços por '-', exemplo: 'Senhor-Dos-Anéis'\n\n> ");
            
            //Scanner para ler a entrada do usuário
            Scanner sc = new Scanner(System.in);
            String entrada = sc.nextLine();
            
            //Envia pedido e recebe resposta
            cliente.enviarPedido(entrada);
            //Separa resposta com quebras de linha para facilitar a leitura
            String resposta = cliente.receberResposta().replace("Livro", "\nLivro");
            
            //Imprime resultado
            System.out.print("\nResposta do servidor:\n" + resposta + '\n');
            
            //Finaliza o scanner e a conexão
            sc.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
