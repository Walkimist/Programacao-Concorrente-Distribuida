package sistema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Livro {
	//@JsonProperty serve para mapear os campos JSON nas variáveis das classes
	@JsonProperty("autor")
	private String autor;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("genero")
	private String genero;
	
	@JsonProperty("numeroDeExemplares")
	private int numeroDeExemplares;
	
	//Construtor padrão necessário para a desserialização pelo Jackson
	public Livro() {}
	
	//Construtor normal
	public Livro(String autor, String nome, String genero, int numeroDeExemplares) {
		this.autor = autor;
		this.nome = nome;
		this.genero = genero;
		this.numeroDeExemplares = numeroDeExemplares;
	}
	
	//Representação em string do objeto livro
	@Override
	public String toString() {
	return "Livro{" +
			"autor='" + autor + '\'' +
    	", nome='" + nome + '\'' +
    	", genero='" + genero + '\'' +
    	", numeroDeExemplares=" + numeroDeExemplares +
    	'}';
	}
	
	//Getters e Setters
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getNumeroDeExemplares() {
		return numeroDeExemplares;
	}

	public void setNumeroDeExemplares(int numeroDeExemplares) {
		this.numeroDeExemplares = numeroDeExemplares;
	}
}
