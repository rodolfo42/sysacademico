package com.prisila.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prisila.modelo.entidade.Professor;

public class TesteDBTest {
	
	@Before
	public void setUp() {
		
	}
	
	@Test
	public void test() {
		TesteDB.newProfessor();
		TesteDB.getAll(Professor.class);
		assertEquals(1, TesteDB.getAll(Professor.class).size());
	}
	
	@After
	public void tearDown() {
		
	}
	
}
