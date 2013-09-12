package com.prisila.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.CursoDao;
import com.prisila.dao.MatriculaDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.entidade.Matricula;

public class MatriculaDaoTest extends DAOTest {
	
	MatriculaDao matriculaDao;
	AlunoDao alunoDao;
	ResponsavelDao responsavelDao;
	CursoDao cursoDao;
	
	@Override
	public void setup() {
		super.setup();
		// TODO usar injecao de dependencia do VRaptor
		// a questão é: COMO?
		matriculaDao = new MatriculaDao(session, alunoDao);
		responsavelDao = new ResponsavelDao(session);
		alunoDao = new AlunoDao(session, responsavelDao);
		cursoDao = new CursoDao(session);
	}
	
	@Test
	public void cadastrarMatricula() {
		Matricula matr = TesteDB.newMatricula();
		
		cursoDao.salvar(matr.getCurso());
		responsavelDao.salvar(matr.getResponsavel());
		alunoDao.salvar(matr.getAluno());
		
		matriculaDao.salvar(matr);
		assertEquals(1, matriculaDao.listaTudo().size());
	}
}