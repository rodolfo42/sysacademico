package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class Dao<M> {

	private final Session session;

	public Dao(Session session) {
		this.session = session;
	}
	

	public void salva(M objeto) {
		Transaction tx = session.beginTransaction();
		session.save(objeto);
		tx.commit();
	}
	
	public void deleta(Long id, Class<M> m){
		Transaction tx = session.beginTransaction();
		M produtoDelete = (M) session.load(m,id);
		session.delete(produtoDelete);
		tx.commit();
	}
	
	public void atualiza(M objeto){
		Transaction tx = session.beginTransaction();
		session.update(objeto);
		tx.commit();
	}
	
	public M carrega(Long id, Class<M> m){
		return (M) session.load(m,id);
	}
	
	public final List<M> listaTudo(Class<M> m) {
		return session.createCriteria(m).list();
	}

	public List<M> busca(String nome, Class<M> m) {
		return session.createCriteria(m)
				.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE))
				.list();
	}
}
