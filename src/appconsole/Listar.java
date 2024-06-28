package appconsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import modelo.Carro;
import modelo.Motor;
import modelo.Motorista;
import regras_negocio.Fachada;


public class Listar {

	public Listar(){
		Fachada.inicializar();
		listar();
		Fachada.finalizar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void listar(){
		System.out.println("\n---listagem de carros:");
		for(Carro p: Fachada.listarCarros())
			System.out.println(p);
		
		System.out.println("\n---listagem de motores:");
		for(Motor mt: Fachada.listarMotores())
			System.out.println(mt);
		
		System.out.println("\n---listagem de motoristas:");
		for(Motorista m: Fachada.listarMotoristas())
			System.out.println(m);
	}
	




	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

