package com.prisila.dao;



import org.hibernate.Session;

import com.prisila.modelo.entidade.AulaMatricula;


public class AulaMatriculaDao extends Dao<AulaMatricula> {
	
	public AulaMatriculaDao(Session session) {
		super(session);
	}
	
}
