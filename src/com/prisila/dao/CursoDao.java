package com.prisila.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Curso;

@Component
public class CursoDao extends Dao<Curso> {
	
	public CursoDao(Session session) {
		super(session);
	}
	
}
