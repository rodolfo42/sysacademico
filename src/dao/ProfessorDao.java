package dao;

import org.hibernate.Session;

import modelo.entidade.Professor;

public class ProfessorDao extends Dao<Professor> {

	public ProfessorDao(Session session) {
		super(session);		
	}

}
