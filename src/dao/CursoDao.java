package dao;

import modelo.entidade.Curso;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class CursoDao extends Dao<Curso> {

	public CursoDao(Session session) {
		super(session);
	}

}
