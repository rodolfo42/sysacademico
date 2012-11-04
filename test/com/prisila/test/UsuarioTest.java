package com.prisila.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prisila.modelo.entidade.Usuario;

public class UsuarioTest {
	
	Usuario usuario;
	
	@Before
	protected void setUp() {
		usuario = new Usuario();
	}
	
	@After
	protected void tearDown() {
		usuario = null;
	}
	
	@Test
	public void senhaMD5() {
		usuario.setSenha("root");
		assertEquals("63a9f0ea7bb98050796b649e85481845", usuario.getSenhaEncriptada());
		
		usuario.setSenha("teste");
		assertEquals("698dc19d489c4e4db73e28a713eab07b", usuario.getSenhaEncriptada());
	}
	
}
