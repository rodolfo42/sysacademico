package dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import modelo.entidade.Curso;

@Component
public class CursoDao extends Dao<Curso>{

	public CursoDao(Session session) {
		super(session);
	}

}
