package com.prisila.dao;

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
	private Class<T> clazz;
	private final List<Criterion> criteriosBusca;
	private Criteria criteria;
	
	public Dao(Session session) {
		this.session = session;
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.criteria = session.createCriteria(clazz);
		this.criteriosBusca = new ArrayList<Criterion>();
	}
	
	protected Session getSession() {
		return session;
	}
	
	public void salvar(T objeto) {
		Transaction tx = session.beginTransaction();
		session.save(objeto);
		tx.commit();
	}
	
	public void atualiza(T objeto) {
		Transaction tx = session.beginTransaction();
		session.update(objeto);
		tx.commit();
	}
	
	public void deletar(Long id) {
		Transaction tx = session.beginTransaction();
		T objetoDelete = (T) session.load(clazz, id);
		session.delete(objetoDelete);
		tx.commit();
	}
	
	public T carrega(Long id) {
		return (T) session.load(clazz, id);
	}
	
	public final List<T> listaTudo() {
		return session.createCriteria(clazz).list();
	}
	
	protected Criterion getCriterionLike(String nomeDoCampo, String textoDaBusca) {
		// MatchMode.ANYWHERE já embrulha a string em porcentagem
		return Restrictions.ilike(nomeDoCampo, textoDaBusca, MatchMode.ANYWHERE);
	}
	
	protected Dao<T> adicionarCriterion(Criterion c) {
		criteriosBusca.add(c);
		return this;
	}
	
	public List<T> buscarTodos() {
		Criterion[] crits = {};
		crits = criteriosBusca.toArray(crits);
		criteriosBusca.clear();
		return buscarTodos(crits);
	}
	
	public T buscarUm(Criterion... criterion) {
		return (T) getCriteria(criterion).uniqueResult();
	}
	
	public List<T> buscarTodos(Criterion... criterion) {
		return getCriteria(criterion).list();
	}
	
	public List<T> buscarTantos(int quantos, Criterion... criterion) {
		return getCriteria(criterion).setMaxResults(quantos).list();
	}
	
	private Criteria getCriteria(Criterion[] criterion) {
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		
		Criteria criteriaAux = criteria;
		resetCriteria();
		return criteriaAux;
	}
	
	private void resetCriteria() {
		criteria = session.createCriteria(clazz);
	}
	
	protected Class<T> getClassePersistente() {
		return clazz;
	}
	
	protected Criteria getCriteria() {
		return this.criteria;
	}
}
