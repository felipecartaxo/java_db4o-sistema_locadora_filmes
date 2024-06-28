package modelo;

import java.util.ArrayList;

public class Genero {
	
	// Atributos
	private String nome;
	private ArrayList<Video> videos = new ArrayList<>(); // Um gênero pode estar relacionado com um ou mais vídeos
	
	// Construtor padrão
	public Genero() {	
	}
	
	// Construtor com argumentos
	public Genero(String nome) {
		this.nome = nome;
	}

	// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Video> getVideos() {
		return videos;
	}
	
	// Adiciona um vídeo 
	public void adicionarVideo(Video video) {
		videos.add(video);
	}
	
	// Remove um vídeo
	public void removerVideo(Video video) {
		videos.remove(video);
	}
	
	// Localiza um vídeo
	public Video localizarVideo(String nome) {
		for (Video v : videos) {
			if(v.getTitulo().equals(nome)) {
				return v;
			}
		}
		
		return null;
	}
	
	// toString
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n" + nome);
		
		for(Video v : videos) {
			if(v.getGeneros() != null) {
				sb.append("\nVideos: " + v.getTitulo());
			}
		};
		
		return sb.toString();
	}
}