package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOGenero;
import daodb4o.DAOVideo;
import modelo.Genero;
import modelo.Video;

public class Fachada {
	private Fachada() {}

	private static DAOVideo daovideo = new DAOVideo();
	private static DAOGenero daogenero = new DAOGenero();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Video criarVideo(String titulo, String link, int classificacao) throws Exception {
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		if (video != null) {
			throw new Exception("Video ja cadastrado: " + titulo);
		}
		video = new Video(titulo, link, classificacao);
		
		daovideo.create(video);
		DAO.commit();
		
		return video;
	}
	
	// Adiciona um genero a um video
	public static void categorizarVideo (String titulo, String nome) throws Exception {
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		if (video == null) {
			throw new Exception("Video inexistente: " + titulo);
		}
		// Lembrar de adicionar exceção para o caso onde tentamos cadastrar generos iguais
		
		Genero genero = daogenero.read(nome);
		if (genero == null) {
			throw new Exception("Genero inexistente: " + nome);
		}
		
		video.adicionarGenero(genero);
		genero.adicionarVideo(video);
		
		daovideo.update(video);
		daogenero.update(genero);
		
		DAO.commit();
	}
	
	// Altera o titulo de um video já existente no banco
		public static void alterarTituloDoVideo(String titulo, String novoTitulo) throws Exception {
			
			DAO.begin();
			Video video = daovideo.read(titulo);
			if(video == null) {
				throw new Exception ("titulo do video inexistente: " + titulo);
			}
			if(video.getTitulo() == titulo) {
				throw new Exception ("video ja possui este titulo: " + titulo);
			}
			video.setTitulo(novoTitulo);
			DAO.commit();
		}
	
	// Altera a classificação de um video já existente no banco
	public static void alterarClassificacaoDoVideo(String titulo, int classificacao) throws Exception {
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		if(video == null) {
			throw new Exception ("video inexistente: " + titulo);
		}
		if(video.getClassificacao() == classificacao) {
			throw new Exception ("video ja possui esta classificacao: " + titulo);
		}
		video.setClassificacao(classificacao);
		DAO.commit();
	}
	
	public static void excluirVideo(String titulo) throws Exception{
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		if(video == null) 
			throw new Exception ("video incorreto para exclusao " + titulo);
		
		// Apaga o vídeo sem apagar os gêneros, pois podem existir outros filmes que compartilham do mesmo gênero
		daovideo.delete(video);
		DAO.commit();
	}
	
	public static Genero criarGenero(String nome) throws Exception {
		
		DAO.begin();
		Genero genero = daogenero.read(nome);
		if (genero != null) {
			throw new Exception("Genero ja cadastrado: " + nome);
		}
		genero = new Genero(nome);
			
		daogenero.create(genero);
		DAO.commit();
			
		return genero;
	}
	
//	// Adiciona um video na lista de videos de um genero
//	public static void associarFilmeComGenero (Video video, String nome) throws Exception {
//		
//		DAO.begin();
//		Genero genero = daogenero.read(nome);
//		
//		if (genero == null) {
//			throw new Exception("Genero inexistente");
//		}
//		
//		genero.adicionarVideo(video);
//		
//	}
	
	public static void excluirGenero(String nome) throws Exception{
		
		DAO.begin();
		Genero genero = daogenero.read(nome);
		if(genero == null) 
			throw new Exception ("genero incorreto para exclusao " + nome);
		
		// Apaga o genero sem apagar os videos, pois os videos podem conter outros gêneros ou podem simplesmente ficar sem gênero até que outro possa ser incluido
		daogenero.delete(genero);
		DAO.commit();
	}
	
	public static List<Video> listarVideos() {
		List<Video> resultado = daovideo.readAll();
		
		return resultado;
	}
	
	public static List<Genero> listarGeneros() {
		List<Genero> resultado = daogenero.readAll();
		
		return resultado;
	}
}