package com.prisila.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.prisila.modelo.entidade.Professor;

public class TesteDBTest {
	
	@Test
	public void test() {
		TesteDB.newProfessor();
		TesteDB.newProfessor();
		TesteDB.newProfessor();
		
		TesteDB.getAll(Professor.class);
		assertEquals(3, TesteDB.getAll(Professor.class).size());
		
		assertNotNull(TesteDB.getOne(Professor.class));
	}
}