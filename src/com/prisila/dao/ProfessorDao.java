package com.prisila.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Professor;

@Component
public class ProfessorDao extends Dao<Professor> {
	
	public ProfessorDao(Session session) {
		super(session);
	}
	
}
