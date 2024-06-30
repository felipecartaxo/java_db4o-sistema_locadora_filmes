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
}