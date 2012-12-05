package com.prisila.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aula;

@Component
public class AulaDao extends Dao<Aula> {
	
	public AulaDao(Session session) {
		super(session);
	}
	
	public Aula buscarUma(Aula aula){
		adicionarCriterion(Restrictions.eq("timestamp", aula.getTimestamp()));
		adicionarCriterion(Restrictions.eq("tipoAula", aula.getTipoAula()));
		adicionarCriterion(Restrictions.or(
				Restrictions.eq("professor.id", aula.getProfessor().getId()), 
				Restrictions.eq("sala.id", aula.getSala().getId())));
		return buscarUm();
	}
	
}
