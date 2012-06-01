package dao;

import modelo.entidade.Aluno;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class AlunoDao extends Dao<Aluno> {

	public AlunoDao(Session session) {
		super(session);
	}
	
}
