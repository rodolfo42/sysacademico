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
public class Dao<M> {

	private final Session session;
	private Class<M> classePersistencia;


	public Dao(Session session) {
		this.session = session;
		this.classePersistencia = (Class<M>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Session getSession() {
		return session;
	}
	

	public void salva(M objeto) {
		Transaction tx = session.beginTransaction();
		session.save(objeto);
		tx.commit();
	}
	
	public void deleta(Long id){
		Transaction tx = session.beginTransaction();
		M objetoDelete = (M) session.load(getClassePersistencia(),id);
		session.delete(objetoDelete);
		tx.commit();
	}
	
	public void atualiza(M objeto){
		Transaction tx = session.beginTransaction();
		session.update(objeto);
		tx.commit();
	}
	
	public M carrega(Long id){
		return (M) session.load(getClassePersistencia(),id);
	}
	
	public final List<M> listaTudo() {
		return session.createCriteria(getClassePersistencia()).list();
	}

	public List<M> busca(String nome) {
		return session.createCriteria(getClassePersistencia())
				.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE))
				.list();
	}
	
	public List<M> lista(Criterion... criterion) {
		Criteria crit = session.createCriteria(getClassePersistencia());
		for(Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}


	public Class<M> getClassePersistencia() {
		return classePersistencia;
	}
}
