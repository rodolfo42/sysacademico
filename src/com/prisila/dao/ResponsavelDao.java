package com.prisila.dao;

import java.util.List;



import org.hibernate.Session;

import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;


import br.com.caelum.vraptor.ioc.Component;

@SuppressWarnings("unchecked")
@Component
public class ResponsavelDao extends Dao<Responsavel> {
	
	public ResponsavelDao(Session session) {
		super(session);
	}
	
	public List<Aluno> listaPeloAluno(Aluno aluno) {
		StringBuffer qry = new StringBuffer();
		qry.append("from Responsavel r join fetch r.listaAluno a ");
		qry.append("where a.id = ?");
		
		return getSession().createQuery(qry.toString()).setLong(0, aluno.getId()).list();
	}
}
