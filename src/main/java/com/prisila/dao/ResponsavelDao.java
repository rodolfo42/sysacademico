package com.prisila.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;

@SuppressWarnings("unchecked")
@Component
public class ResponsavelDao extends Dao<Responsavel> {
	
	public ResponsavelDao(Session session) {
		super(session);
	}
	
	public List<Responsavel> listaPeloAluno(Aluno aluno) {
		StringBuilder qry = new StringBuilder();
		qry.append("from Responsavel r join fetch r.listaAluno a ");
		qry.append("where a.id = ?");
		
		return getSession().createQuery(qry.toString()).setLong(0, aluno.getId()).list();
	}

	public Responsavel getByCPF(String cpf) {
		adicionarCriterion(Restrictions.eq("cpf", cpf));
		return buscarUm();
	}

	public List<Responsavel> buscarPorCPFAproximado(String cpf) {
		adicionarCriterion(Restrictions.ilike("cpf", cpf, MatchMode.ANYWHERE));
		return buscarTodos();
	}

	public List<Responsavel> buscarPorNomeAproximado(String nome) {
		adicionarCriterion(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		return buscarTodos();
	}
}