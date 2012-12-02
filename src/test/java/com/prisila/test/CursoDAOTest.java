package com.prisila.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.prisila.dao.CursoDao;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.Professor;

public class CursoDAOTest extends DAOTest {
	
	CursoDao dao;
	
	@Override
	public void setup() {
		super.setup();
		dao = new CursoDao(session);
	}
	
	@Test
	public void adicionar() {
		Curso curso = new Curso();
		curso.setNome("Curso");
		
		dao.salvar(curso);
		dao.salvar(curso);
		assertEquals("deveria ter salvo", 1, dao.buscarTodos().size());
	}
	
	@Test
	public void editar() {
		Curso curso = new Curso();
		curso.setNome("Curso");
		
		dao.salvar(curso);
		curso.setNome("Curso2");
		dao.salvar(curso);
		
		assertEquals("deveria ter salvo/editado", "Curso2", dao.carrega(1L).getNome());
	}
	
	@Test
	public void deletar() {
		Curso curso = new Curso();
		curso.setNome("Curso");
		
		dao.salvar(curso);
		assertEquals("deveria ter salvo", 1, dao.buscarTodos().size());
		assertNotNull("ID nao deveria estar null apos save()", curso.getId());
		
		dao.deletar(curso.getId());
		assertEquals("deveria ter deletado", 0, dao.buscarTodos().size());
	}
	
	@Test
	public void adicionarComProfessores() {
		Curso curso = new Curso();
		curso.setNome("Curso");
		
		List<Professor> listaProf = new ArrayList<Professor>();
		listaProf.add(getProfessorTeste());
		listaProf.add(getProfessorTeste());
		curso.setListaProfessor(listaProf);
		
		dao.salvar(curso);
		assertEquals("deveria ter salvo", 1, dao.buscarTodos().size());
		assertEquals("deveria ter salvo", listaProf, dao.carrega(1L).getListaProfessor());
	}
	
	/**
	 * Retorna uma instância de Professor com dados teste
	 * 
	 * @return
	 */
	private Professor getProfessorTeste() {
		return TesteDB.newProfessor();
	}
	
	@Override
	public void finalize() {
		super.finalize();
		dao = null;
	}
}