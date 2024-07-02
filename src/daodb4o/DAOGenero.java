package daodb4o;

import java.util.List;

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
	
	// Método para buscar vídeos pelo gênero
	public List<Genero> videosPorGenero(String nome){

		Query q = manager.query();
		q.constrain(Genero.class);
		q.descend("genero").constrain(nome);
		
		return q.execute();
	}
}