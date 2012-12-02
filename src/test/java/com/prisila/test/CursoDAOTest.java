package com.prisila.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.prisila.dao.CursoDao;
import com.prisila.modelo.entidade.Curso;

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
		
		try {
			dao.salvar(curso);
			dao.salvar(curso);
			assertEquals(1, dao.buscarTodos().size());
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void editar() {
		Curso curso = new Curso();
		curso.setNome("Curso");
		
		try {
			dao.salvar(curso);
			curso.setNome("Curso2");
			dao.salvar(curso);
		} catch(Exception e) {
			fail(e.getMessage());
		}
		
		assertEquals(1, dao.buscarTodos().size());
		assertEquals("Curso2", dao.carrega(1L).getNome());
	}
	
	@Override
	public void finalize() {
		super.finalize();
		dao = null;
	}
}