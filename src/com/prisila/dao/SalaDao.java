package com.prisila.dao;



import org.hibernate.Session;

import com.prisila.modelo.entidade.Sala;


public class SalaDao extends Dao<Sala> {
	
	public SalaDao(Session session) {
		super(session);
	}
	
}
