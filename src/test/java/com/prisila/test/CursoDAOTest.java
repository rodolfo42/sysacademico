package com.prisila.test;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prisila.dao.CursoDao;
import com.prisila.modelo.entidade.Curso;

public class CursoDAOTest {
	
	CursoDao dao;
	SessionFactory sessionFactory;
	Session session;
	
	@Before
	public void construirSessionFactory() {
		sessionFactory = new AnnotationConfiguration()
			.configure("hibernate.test.cfg.xml")
			.buildSessionFactory();
		session = sessionFactory.openSession();
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
	
	
	@After
	public void finalizarSessionFactory() {
		session.close();
		sessionFactory.close();
		
		dao = null;
		session = null;
		sessionFactory = null;
	}
}