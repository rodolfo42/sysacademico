package com.prisila.dao;

import org.hibernate.Session;

import com.prisila.modelo.entidade.HorarioProfessor;

public class HorarioProfessorDao extends Dao<HorarioProfessor> {
	
	public HorarioProfessorDao(Session session) {
		super(session);
	}
	
}
