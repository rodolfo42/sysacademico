package com.prisila.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Usuario;

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
