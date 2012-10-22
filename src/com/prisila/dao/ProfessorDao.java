package com.prisila.dao;



import org.hibernate.Session;

import com.prisila.modelo.entidade.Professor;


public class ProfessorDao extends Dao<Professor> {
	
	public ProfessorDao(Session session) {
		super(session);
	}
	
}
