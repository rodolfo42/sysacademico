package com.prisila.dao;



import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.prisila.modelo.entidade.Usuario;


import br.com.caelum.vraptor.ioc.Component;

@Component
public class UsuarioDao extends Dao<Usuario> {
	
	public UsuarioDao(Session session) {
		super(session);
	}
	
	public boolean existeLoginSenha(Usuario usr) {
		return buscar(Restrictions.eq("login", usr.getLogin()), Restrictions.eq("senha", usr.getSenhaEncriptada()))
				.size() > 0;
	}
}
