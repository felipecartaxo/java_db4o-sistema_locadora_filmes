package appconsole;

import modelo.Genero;
import modelo.Video;
import regras_negocio.Fachada;


public class Consultar {

	public Consultar(){

		try {
			Fachada.inicializar();
			System.out.println("consultas... \n");
			
			// Quais os videos de classificação X
			
			
			// Quais os gêneros que tem mais de N vídeos
			
			
			// Quais os videos de título X
			System.out.println("\nVideos com o título 'Coraline': ");
			for(Video video : Fachada.videosPorTitulo("Coraline"))
				System.out.println(video);
			
			// Quais os vídeos de link X
			System.out.println("\nVideos com o link bladerunner.com");
			for(Video video : Fachada.videosPorLink("bladerunner.com"))
				System.out.println(video);
			
			// Quais os videos do gênero X
			System.out.println("\nVídeos cujo gênero é Suspense: ");
			for(Genero genero : Fachada.videosPorGenero("Suspense"))
				System.out.println(genero);                
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Fachada.finalizar();
		System.out.println("\nfim do programa");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}