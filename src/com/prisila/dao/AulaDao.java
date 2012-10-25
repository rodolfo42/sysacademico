package com.prisila.dao;

import org.hibernate.Session;

import com.prisila.modelo.entidade.Aula;

public class AulaDao extends Dao<Aula> {
	
	public AulaDao(Session session) {
		super(session);
	}
	
}
