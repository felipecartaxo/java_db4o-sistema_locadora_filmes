package regras_negocio;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOGenero;
import daodb4o.DAOUsuario;
import daodb4o.DAOVideo;
import modelo.Carro;
import modelo.Motor;
import modelo.Motorista;
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
	
	public static void criarCarro(String placa, String nomemotor, String cnh) throws Exception {
		DAO.begin();
		Carro  carro = daocarro.read(placa);
		if (carro != null) {
			DAO.rollback();
			throw new Exception("criar carro - placa ja existe:" + placa);
		}
		Motor  motor = daomotor.read(nomemotor);
		if (motor == null) {
			DAO.rollback();
			throw new Exception("criar carro - motor nao existe:" + nomemotor);
		}
		Motorista  motorista = daomotorista.read(cnh);
		if (motorista == null) {
			DAO.rollback();
			throw new Exception("criar carro - motorista nao existe:" + cnh);
		}
		carro = new Carro(placa, motor, motorista);
		daocarro.create(carro);
		DAO.commit();
	}

	public static void alterarMotoristaDoCarro(String placa, String cnh) throws Exception {
		DAO.begin();
		Carro  carro = daocarro.read(placa);
		if (carro == null) {
			DAO.rollback();
			throw new Exception("alterar motorista do carro - placa inexistente:" + placa);
		}
		Motorista  motorista = daomotorista.read(cnh);
		if (motorista == null) {
			DAO.rollback();
			throw new Exception("alterar motorista do carro - motorista inexistente:" + cnh);
		}
		if (motorista.equals(carro.getMotorista())) {
			DAO.rollback();
			throw new Exception("alterar motorista do carro - motorista nao pode ser o atual:" + cnh);
		}
		carro.setMotorista(motorista);
		daocarro.update(carro);
		DAO.commit();
	}
	
	
	public static List<Carro> listarCarros() {
		List<Carro> result = daocarro.readAll();
		return result;
	}

	public static List<Motor> listarMotores() {
		List<Motor> result = daomotor.readAll();
		return result;
	}
}