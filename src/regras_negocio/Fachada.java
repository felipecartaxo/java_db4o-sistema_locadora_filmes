package regras_negocio;

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOGenero;
import daodb4o.DAOUsuario;
import daodb4o.DAOVideo;
import modelo.Genero;
import modelo.Usuario;
import modelo.Video;

public class Fachada {
	private Fachada() {}

	private static DAOVideo daovideo = new DAOVideo();
	private static DAOGenero daogenero = new DAOGenero();
	private static DAOUsuario daousuario = new DAOUsuario();
	public static Usuario logado;	// Contem o objeto Usuario logado em TelaLogin.java

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Video cadastrarVideo(String titulo, String link, int classificacao) throws Exception {
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
	
	public static Genero cadastrarGenero(String nome) throws Exception {
		
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
	
	public static void excluirVideo(String titulo) throws Exception{
		
		DAO.begin();
		Video video = daovideo.read(titulo);
		if(video == null) 
			throw new Exception ("video incorreto para exclusao " + titulo);
		
		// Apaga o vídeo sem apagar os gêneros, pois podem existir outros filmes que compartilham do mesmo gênero
		daovideo.delete(video);
		DAO.commit();
	}
	
	public static List<Video> listarVideos() {
		List<Video> resultado = daovideo.readAll();
		
		return resultado;
	}
}