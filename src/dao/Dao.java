package dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@SuppressWarnings("unchecked")
public class Dao<T> {

	private final Session session;
	private Class<T> classePersistencia;


	public Dao(Session session) {
		this.session = session;
		this.classePersistencia = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Session getSession() {
		return session;
	}
	

	public void salva(T objeto) {
		Transaction tx = session.beginTransaction();
		session.save(objeto);
		tx.commit();
	}
	
	public void deleta(Long id){
		Transaction tx = session.beginTransaction();
		T objetoDelete = (T) session.load(getClassePersistencia(),id);
		session.delete(objetoDelete);
		tx.commit();
	}
	
	public void atualiza(T objeto){
		Transaction tx = session.beginTransaction();
		session.update(objeto);
		tx.commit();
	}
	
	public T carrega(Long id){
		return (T) session.load(getClassePersistencia(),id);
	}
	
	public final List<T> listaTudo() {
		return session.createCriteria(getClassePersistencia()).list();
	}

	public List<T> busca(String textoDaBusca, String nomeDoCampo) {
		return session.createCriteria(getClassePersistencia())
				.add(Restrictions.ilike(nomeDoCampo, textoDaBusca, MatchMode.ANYWHERE))
				.list();
	}
	
	public List<T> lista(Criterion... criterion) {
		Criteria crit = session.createCriteria(getClassePersistencia());
		for(Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}


	public Class<T> getClassePersistencia() {
		return classePersistencia;
	}
}
