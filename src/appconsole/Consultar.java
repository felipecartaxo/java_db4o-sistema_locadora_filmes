package appconsole;

import modelo.Genero;
import modelo.Video;
import regras_negocio.Fachada;


public class Consultar {

	public Consultar(){

		try {
			
			System.out.println("consultas... \n");
			
			// Quais os videos de classificação X
			
			
			// Quais os gêneros que tem mais de N vídeos
			
			
			// Quais os videos de título X
			System.out.println("\nVideos com o título Video1");
			for(Video a : Fachada.videosPorTitulo("Video1"))
				System.out.println(a);
			
			// Quais os videos do gênero X
			System.out.println("\nVídeos cujo gênero é romance: ");
			for(Genero b : Fachada.videosPorGenero("Romance"))
				System.out.println(b);                
            
			// Quais os vídeos de link X
			System.out.println("\nVideos com o link video2.com");
			for(Video c : Fachada.videosPorLink("video2.com"))
				System.out.println(c);
			
			
			
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