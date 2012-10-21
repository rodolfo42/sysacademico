package dao;

import modelo.entidade.HorarioProfessor;

import org.hibernate.Session;

public class HorarioProfessorDao extends Dao<HorarioProfessor> {
	
	public HorarioProfessorDao(Session session) {
		super(session);
	}
	
}
