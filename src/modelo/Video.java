package modelo;

import java.util.ArrayList;

public class Video {
	
	// Atributos
	private int id; // Este id PROVAVELMENTE será gerado automaticamente
	private String titulo;
	private String link;
	private int classificacao; // De 1 a 5
	private ArrayList<Genero> generos = new ArrayList<>(); // Um vídeo possui um ou mais gêneros
	
	// Construtor padrão
	public Video() {}
	
	// Construtor com argumentos
	public Video(int id, String titulo, String link, int classificacao) throws Exception { // Um vídeo não pode ter dois gêneros iguais
		if(classificacao < 1 || classificacao > 5) {
			throw new Exception("Classificação de 1 a 5"); // A classificação deve ser de 1 a 5
		}
		
		this.id = id;
		this.titulo = titulo;
		this.link = link;
		this.classificacao = classificacao;
	}

	// Getters e settters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	
	public ArrayList<Genero> getGeneros() {
		return generos;
	}
	
	// Adiciona um gênero
	public void adicionarGenero(Genero genero) throws Exception{
		if(generos.contains(genero)) {
			throw new Exception("O filme não pode ter 2 gêneros iguais"); // Um filme não pode ter 2 gêneros iguais
		}
		
		generos.add(genero);
	}
	
	// Remove um gênero
	public void removerGenero(Genero genero) {
		generos.remove(genero);
	}
	
	// Localiza um gênero
	public Genero localizarGenero(String genero) {
		for(Genero g : generos) {
			if(g.getNome().equals(genero)) {
				return g;
			}
		}
		
		return null;
	}
	
	// toString
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(id + ", " + titulo + ", " + link + ", " + classificacao + "\nGeneros: ");
		for(Genero g : generos){
			sb.append(g.getNome() + " ");
		};
		
		return sb.toString();
	}
}