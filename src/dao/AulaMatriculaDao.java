package dao;

import org.hibernate.Session;

import modelo.entidade.AulaMatricula;

public class AulaMatriculaDao extends Dao<AulaMatricula> {

	public AulaMatriculaDao(Session session) {
		super(session);
	}

}
