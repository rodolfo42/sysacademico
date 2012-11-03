package com.prisila.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aula;

@Component
public class AulaDao extends Dao<Aula> {
	
	public AulaDao(Session session) {
		super(session);
	}
	
	
}
