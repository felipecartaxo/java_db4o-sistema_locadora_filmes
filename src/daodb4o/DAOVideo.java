package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Video;

public class DAOVideo extends DAO<Video> {

	// Nome usado como campo único da classe Video
	public Video read (Object chave) {
		
		String titulo = (String) chave;	// Casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("titulo").constrain(titulo);
		List<Video> resultados = q.execute();
		
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}
	
	// Método sobrescrito da classe DAO para criar "id" sequencial 
	public void create(Video obj){
		int novoid = super.gerarId();  	// Gera novo id da classe
		obj.setId(novoid);				// Atualiza id do objeto antes de grava-lo no banco
		manager.store( obj );
	}
	
	// ---------- Consultas ----------
	
	// Método para buscar vídeos pelo título
	public static List<Video> videosPorTitulo(String titulo) {
		
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("video").descend("titulo").constrain(titulo);
		return q.execute();
    }
    
    // Método para buscar vídeos pelo link
    public static List<Video> videosPorLink(String link) {
        Query q = manager.query();
        q.constrain(Video.class);
        q.constrain(Video.class);
        q.descend("video").descend("link").constrain(link);
        return q.execute();
    } 
    	
	// -------------------------------------
}