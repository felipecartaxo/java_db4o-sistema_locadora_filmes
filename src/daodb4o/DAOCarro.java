/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;


import java.util.List;

import com.db4o.query.Query;

import modelo.Carro;

public class DAOCarro  extends DAO<Carro>{

	//nome é usado como campo unico 
	public Carro read (Object chave) {
		String placa = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Carro.class);
		q.descend("placa").constrain(placa);
		List<Carro> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

}



