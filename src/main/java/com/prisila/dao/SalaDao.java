package com.prisila.dao;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Sala;

@Component
public class SalaDao extends Dao<Sala> {
	
	public SalaDao(Session session) {
		super(session);
	}
	
}
