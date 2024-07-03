package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando videos...");
			Fachada.criarVideo("O labirinto do fauno", "labfauno.con", 5);
			Fachada.criarVideo("A viagem de Chihiro", "chihiro.com", 5);
			Fachada.criarVideo("Elementos", "elementos.com", 2);
			Fachada.criarVideo("Coraline", "coraline.com", 5);
			Fachada.criarVideo("Blade Runner", "bladerunner.com", 3);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando generos...");
			Fachada.criarGenero("Ação");
			Fachada.criarGenero("Aventura");
			Fachada.criarGenero("Suspense");
			Fachada.criarGenero("Animação");
			Fachada.criarGenero("Romance");
			Fachada.criarGenero("Terror");
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("categorizando filmes...");
			
			Fachada.categorizarVideo("O labirinto do fauno", "Suspense");
			Fachada.categorizarVideo("O labirinto do fauno", "Terror");
			Fachada.categorizarVideo("A viagem de Chihiro", "Animação");
			Fachada.categorizarVideo("A viagem de Chihiro", "Aventura");
			Fachada.categorizarVideo("Elementos", "Animação");
			Fachada.categorizarVideo("Elementos", "Romance");
			Fachada.categorizarVideo("Coraline", "Animação");
			Fachada.categorizarVideo("Coraline", "Suspense");
			Fachada.categorizarVideo("Blade Runner", "Aventura");
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Cadastrar();
	}
}