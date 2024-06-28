/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Motorista;

public class DAOMotorista  extends DAO<Motorista>{
	//nome é usado como campo unico 
	public Motorista read (Object chave) {
		String cnh = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Motorista.class);
		q.descend("cnh").constrain(cnh);
		List<Motorista> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}


}

