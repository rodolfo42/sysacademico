package dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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
	private List<Criterion> criteriosBusca;
	private Criteria criteria;
	
	public Dao(Session session) {
		this.session = session;
		this.classePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.criteria = session.createCriteria(classePersistencia);
		this.criteriosBusca = new ArrayList<Criterion>();
	}
	
	protected Session getSession() {
		return session;
	}
	
	public void salva(T objeto) {
		Transaction tx = session.beginTransaction();
		session.save(objeto);
		tx.commit();
	}
	
	public void deleta(Long id) {
		Transaction tx = session.beginTransaction();
		T objetoDelete = (T) session.load(classePersistencia, id);
		session.delete(objetoDelete);
		tx.commit();
	}
	
	public void atualiza(T objeto) {
		Transaction tx = session.beginTransaction();
		session.update(objeto);
		tx.commit();
	}
	
	public T carrega(Long id) {
		return (T) session.load(classePersistencia, id);
	}
	
	public final List<T> listaTudo() {
		return session.createCriteria(classePersistencia).list();
	}
	
	protected Criterion getParametroBusca(String nomeDoCampo, String textoDaBusca) {
		return Restrictions.ilike(nomeDoCampo, textoDaBusca, MatchMode.ANYWHERE);
	}
	
	protected void addCriterion(Criterion c) {
		criteriosBusca.add(c);
	}
	
	public List<T> buscar() {
		Criterion[] crits = {};
		crits = criteriosBusca.toArray(crits);
		criteriosBusca.clear();
		return buscar(crits);
	}
	
	public List<T> buscar(Criterion... criterion) {
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		Criteria criteriaBackup = criteria;
		criteria = session.createCriteria(classePersistencia);
		return criteriaBackup.list();
	}
	
	protected Class<T> getClassePersistencia() {
		return classePersistencia;
	}
	
	protected void adicionaCriteria(Criteria criteria) {
		this.criteria = criteria;
	}
	
	protected Criteria getCriteria() {
		return this.criteria;
	}
}
