package com.prisila.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aula;
import com.prisila.modelo.entidade.Matricula;

@Component
public class AulaDao extends Dao<Aula> {
	
	public AulaDao(Session session) {
		super(session);
	}
	
}
