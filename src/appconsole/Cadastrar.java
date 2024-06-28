package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		Fachada.inicializar();
		cadastrar();
		Fachada.finalizar();
		System.out.println("fim da appconsole");
	}

	public void cadastrar() {
		try {
			System.out.println("cadastrando...");
			
			Fachada.criarMotor("zetec", 1.0);
			Fachada.criarMotorista("1111", "joao");
			Fachada.criarCarro("AAA1100", "zetec", "1111");
			
			Fachada.criarMotorista("2222", "ana");
			Fachada.criarMotorista("3333", "jose");
			Fachada.criarMotor("fire", 2.0);
			Fachada.criarMotor("equinox", 3.0);

			System.out.println("cadastrou.");
		} catch (Exception e) {
			System.out.println("exceção=" + e.getMessage());
		}
	}

	// =================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}
