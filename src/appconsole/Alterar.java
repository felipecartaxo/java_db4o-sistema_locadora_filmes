package appconsole;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import regras_negocio.Fachada;

public class Alterar {

	public Alterar() {
		Fachada.inicializar();
		atualizar();
		Fachada.finalizar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void atualizar() {
		try {
			Fachada.alterarMotoristaDoCarro("AAA1100", "2222");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// =================================================
	public static void main(String[] args) {
		new Alterar();
	}
}
