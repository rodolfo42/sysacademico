package com.prisila.dao;

import java.util.List;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.Professor;

@Component
@SuppressWarnings("unchecked")
public class ProfessorDao extends Dao<Professor> {
	
	public ProfessorDao(Session session) {
		super(session);
	}
	
	public List<Professor> buscarHorariosDisponiveis(Curso curso) {
		StringBuilder qry = new StringBuilder();
		qry.append("from Professor p join fetch p.listaCurso c ");
		qry.append(" where c.id = ? ");
		return (List<Professor>) getSession().createQuery(qry.toString()).setLong(0, curso.getId()).list();
	}
	
}
