/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Motor;

public class DAOMotor  extends DAO<Motor>{
	
	//nome é usado como campo unico 
	public Motor read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Motor.class);
		q.descend("nome").constrain(nome);
		List<Motor> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}

