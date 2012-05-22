package dao;

import modelo.entidade.Usuario;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDao extends Dao<Usuario> {

	public UsuarioDao(Session session) {
		super(session);
	}

}
