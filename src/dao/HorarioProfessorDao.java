package dao;

import org.hibernate.Session;

import modelo.entidade.HorarioProfessor;

public class HorarioProfessorDao extends Dao<HorarioProfessor> {

	public HorarioProfessorDao(Session session) {
		super(session);
	}

}
