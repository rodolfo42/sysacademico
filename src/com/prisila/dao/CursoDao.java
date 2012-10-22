package com.prisila.dao;



import org.hibernate.Session;

import com.prisila.modelo.entidade.Curso;


import br.com.caelum.vraptor.ioc.Component;

@Component
public class CursoDao extends Dao<Curso> {
	
	public CursoDao(Session session) {
		super(session);
	}
	
}
