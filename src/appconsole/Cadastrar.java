package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando videos...");
			Fachada.criarVideo("Video1", "video1.com", 1);
			Fachada.criarVideo("Video2", "video2.com", 2);
			Fachada.criarVideo("Video3", "video3.com", 3);
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando generos...");
			Fachada.criarGenero("Ação");
			Fachada.criarGenero("Romance");
			Fachada.criarGenero("Terror");
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("categorizando filmes...");
			Fachada.categorizarVideo("Video1", "Romance");
			Fachada.categorizarVideo("Video2", "Ação");
			Fachada.categorizarVideo("Video3", "Terror");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Cadastrar();
	}
}
