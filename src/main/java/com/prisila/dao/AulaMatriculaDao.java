package com.prisila.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.constante.StatusAula;
import com.prisila.modelo.entidade.AulaMatricula;
import com.prisila.modelo.entidade.Matricula;

@Component
public class AulaMatriculaDao extends Dao<AulaMatricula> {
	
	public AulaMatriculaDao(Session session) {
		super(session);
	}
	
	public List<AulaMatricula> buscarAulas(Matricula matricula){
		return super.buscarTodos(Restrictions.eq("matricula.id", matricula.getId()));
	}

	@Override
	public void salvar(AulaMatricula aulaMatricula) {
		aulaMatricula.setStatusAula(StatusAula.AULA_NAO_REALIZADA);
		
		super.salvar(aulaMatricula);
	}
	
}
