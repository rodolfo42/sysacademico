package com.prisila.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.After;
import org.junit.Before;

/**
 * extender essa classe para todos os testCases de DAO's contém lógica para
 * instanciar o SessionFactory com configs de teste
 */
public abstract class DAOTest {
	
	SessionFactory sessionFactory;
	Session session;
	
	@Before
	public void setup() {
		System.out.println("DAOTest.setup()");
		sessionFactory = new AnnotationConfiguration().configure("hibernate.test.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
	}
	
	@After
	public void finalize() {
		System.out.println("DAOTest.finalize()");
		session.close();
		sessionFactory.close();
		
		session = null;
		sessionFactory = null;
	}
}
