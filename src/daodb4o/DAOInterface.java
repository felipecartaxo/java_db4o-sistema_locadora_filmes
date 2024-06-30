package daodb4o;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
import java.util.List;

// Classe que vai estabelecer um "contrato" com as classes que irão implementar os métodos abaixo
public interface DAOInterface<T> {
	
	public void create(T obj);
	public T read(Object chave);
	public T update(T obj);
	public void delete(T obj) ;
	public List<T> readAll();
	public void deleteAll();
}