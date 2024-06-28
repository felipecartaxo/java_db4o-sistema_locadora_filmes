package regras_negocio;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOCarro;
import daodb4o.DAOMotor;
import daodb4o.DAOMotorista;
import modelo.Carro;
import modelo.Motor;
import modelo.Motorista;

public class Fachada {
	private Fachada() {}

	private static DAOCarro daocarro = new DAOCarro();
	private static DAOMotor daomotor = new DAOMotor();
	private static DAOMotorista daomotorista = new DAOMotorista();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}


	public static void criarMotor(String nomemotor, double potencia) throws Exception {
		DAO.begin();
		Motor  motor = daomotor.read(nomemotor);
		if (motor != null) {
			DAO.rollback();
			throw new Exception("criar motor - motor ja existe:" + nomemotor);
		}
		
		motor = new Motor(nomemotor, potencia);
		daomotor.create(motor);
		DAO.commit();
	}

	public static void criarMotorista(String cnh, String nome) throws Exception {
		DAO.begin();
		Motorista  motorista = daomotorista.read(cnh);
		if (motorista != null) {
			DAO.rollback();
			throw new Exception("criar motorista - motorista ja existe:" + cnh);
		}
		
		motorista = new Motorista(cnh, nome);
		daomotorista.create(motorista);
		DAO.commit();
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

	public static List<Motorista> listarMotoristas() {
		List<Motorista> result = daomotorista.readAll();
		return result;
	}


	
}
