package com.prisila.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Usuario;
import com.prisila.util.UtGeral;

@Component
public class UsuarioDao extends Dao<Usuario> {
	
	public UsuarioDao(Session session) {
		super(session);
	}
	
	public Usuario buscarPorLoginSenha(String login, String senha) {
		return buscarUm(Restrictions.eq("login", login), Restrictions.eq("senha", UtGeral.hashMD5(senha)));
	}
	
	public boolean existeLoginSenha(String login, String senha) {
		return buscarPorLoginSenha(login, senha) != null;
	}
}
