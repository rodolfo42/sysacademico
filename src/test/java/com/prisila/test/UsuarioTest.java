package com.prisila.test;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.prisila.modelo.entidade.Usuario;

public class UsuarioTest {
	
	Usuario usuario;
	
	@Before
	public void before() {
		usuario = new Usuario();
	}
	
	@After
	public void after() {
		usuario = null;
	}
	
	@Test
	public void senhaMD5() {
		usuario.setSenha("root");
		usuario.criptografarSenha();
		assertEquals("63a9f0ea7bb98050796b649e85481845", usuario.getSenha());
		
		usuario.setSenha("teste");
		usuario.criptografarSenha();
		assertEquals("698dc19d489c4e4db73e28a713eab07b", usuario.getSenha());
	}
	
}
