package com.prisila.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;

@SuppressWarnings("unchecked")
@Component
public class AlunoDao extends Dao<Aluno> {
	
	private ResponsavelDao responsavelDao;
	
	public AlunoDao(Session session, ResponsavelDao responsavelDao) {
		super(session);
		this.responsavelDao = responsavelDao;
	}
	
	/*
	 * public AlunoDao buscarPorNome(String nome) {
	 * addCriterion(getParametroBusca(nome, "nome_aluno")); return this; }
	 */

	public List<Aluno> listaPeloResponsavel(Responsavel responsavel) {
		StringBuilder qry = new StringBuilder();
		qry.append("from Aluno a join fetch a.listaResponsavel r ");
		qry.append("where r.id = ?");
		
		return getSession().createQuery(qry.toString()).setLong(0, responsavel.getId()).list();
	}
	
	public List<Aluno> listaAniversariantes() {
		Calendar calendario = Calendar.getInstance();
		StringBuilder qry = new StringBuilder();
		qry.append("from Aluno ");
		qry.append("where month(dataNascimento) = ?");
		qry.append("order by day(dataNascimento)");
		
		return getSession().createQuery(qry.toString()).setInteger(0, calendario.get(Calendar.MONTH) + 1).list();
	}
	
	public List<Aluno> buscarPorNome(String nomeAluno) {
		getCriteria().add(getCriterionLike("nome", nomeAluno));
		return buscarTodos();
	}

	public void salvarAlunoComResponsavel(Aluno novoAluno, Responsavel responsavelASalvar) {
		Transaction tx = getSession().beginTransaction();
		
		responsavelASalvar.adicionaVinculo(novoAluno);
		novoAluno.adicionaVinculo(responsavelASalvar);
		
		responsavelDao.getSession().save(responsavelASalvar);
		getSession().save(novoAluno);
		tx.commit();
	}
}
