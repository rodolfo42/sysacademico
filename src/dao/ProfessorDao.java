package dao;

import modelo.entidade.Professor;

import org.hibernate.Session;

public class ProfessorDao extends Dao<Professor> {
	
	public ProfessorDao(Session session) {
		super(session);
	}
	
}
