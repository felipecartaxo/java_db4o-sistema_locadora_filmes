package appconsole;

import modelo.Genero;
import modelo.Video;
import regras_negocio.Fachada;


public class Consultar {

	public Consultar(){

		try {
			Fachada.inicializar();
			System.out.println("consultas... \n");
			
			System.out.println("\nVideos de classificação 5': ");
			for(Video video : Fachada.videosPorClassificacao(5))
				System.out.println(video);
			
			System.out.println("\nVídeos cujo gênero é Suspense: ");
			for(Video video : Fachada.videosPorGenero("Suspense"))
				System.out.println(video);
			
			System.out.println("\nVideos com o título 'Coraline': ");
			for(Video video : Fachada.videosPorTitulo("Coraline"))
				System.out.println(video);
			
			System.out.println("\nVideos com o link bladerunner.com: ");
			for(Video video : Fachada.videosPorLink("bladerunner.com"))
				System.out.println(video);
			
			System.out.println("\nGêneros com mais de 2 vídeos: ");
			for(Genero generos : Fachada.generosComMaisVideos(2))
				System.out.println(generos);
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