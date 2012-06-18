package tests;

import junit.framework.TestCase;
import modelo.entidade.Usuario;

public class UsuarioDaoTest extends TestCase {
	
	Usuario usuario;
	
	protected void setUp() throws Exception {
		usuario = new Usuario();
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		usuario = null;
	}
	
	
	public void testSenhaMD5() {
		usuario.setSenha("root");
		assertEquals("63a9f0ea7bb98050796b649e85481845", usuario.getEncryptedSenha());
		
		usuario.setSenha("teste");
		assertEquals("698dc19d489c4e4db73e28a713eab07b", usuario.getEncryptedSenha());
	}
	
}
