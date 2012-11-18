package com.prisila.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Matricula;

@Component
public class MatriculaDao extends Dao<Matricula> {
	
	private AlunoDao alunoDao;
	
	public MatriculaDao(Session session, AlunoDao alunoDao) {
		super(session);
		this.alunoDao = alunoDao;
	}
	
	public List<Matricula> buscarPorNomeAluno(String nomeAluno) {
		return buscarPorAlunos(alunoDao.buscarPorNome(nomeAluno));
	}
	
	public List<Matricula> buscarPorAlunos(List<Aluno> alunos) {
		if (alunos == null) {
			return null;
		}
		List<Long> ids = new ArrayList<Long>(0);
		for (Aluno aluno : alunos) {
			ids.add(aluno.getId());
		}
		getCriteria().createAlias("aluno", "a", Criteria.INNER_JOIN, Restrictions.in("a.id", ids));
		return buscarTodos();
	}
	
	@Override
	public void salvar(Matricula matricula){
		Calendar data = Calendar.getInstance();
		
		matricula.setAtivo(true);
		matricula.setData(data);
		
		super.salvar(matricula);
	}
}
