package daodb4o;

import java.util.ArrayList;
import java.util.List;

import com.db4o.ObjectSet;
import com.db4o.query.Query;

import modelo.Genero;

public class DAOGenero extends DAO<Genero> {

	// Nome usado como campo único da classe Genero
	public Genero read (Object chave) {
		
		String nome = (String) chave;	// Casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Genero.class);
		q.descend("nome").constrain(nome);
		List<Genero> resultados = q.execute();
		
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}
	
	// ---------- Consultas ----------
	
	// Quais os gêneros que tem mais de N vídeos
	public List<Genero> generosComMaisVideos (int valor) {
		
		/*
		 * Query q = manager.query(); // Não está funcionando corretamente
		 * q.constrain(Genero.class);
		 * q.descend("videos").size().constrain(valor).greater();
		 * 
		 * return q.execute();
		 */
		
		 Query q = manager.query();
	        q.constrain(Genero.class);
	        ObjectSet<Genero> generos = q.execute();
	        
	        List<Genero> generosComMaisVideos = new ArrayList<>();
	        
	        for (Genero genero : generos) {
	            if (genero.getVideos().size() > valor) {
	                generosComMaisVideos.add(genero);
	            }
	        }
	        
	        return generosComMaisVideos;
	}
	
	public List<Genero> videosPorGenero(String nome){
	    Query q = manager.query();
	    q.constrain(Genero.class);
	    q.descend("nome").constrain(nome);
	    ObjectSet<Genero> resultados = q.execute();
	    return resultados;
	}
}