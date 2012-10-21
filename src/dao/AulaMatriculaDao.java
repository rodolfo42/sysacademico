package dao;

import modelo.entidade.AulaMatricula;

import org.hibernate.Session;

public class AulaMatriculaDao extends Dao<AulaMatricula> {

	public AulaMatriculaDao(Session session) {
		super(session);
	}

}
