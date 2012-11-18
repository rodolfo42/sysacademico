package com.prisila.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.AulaMatricula;

@Component
public class AulaMatriculaDao extends Dao<AulaMatricula> {
	
	public AulaMatriculaDao(Session session) {
		super(session);
	}
	
}
