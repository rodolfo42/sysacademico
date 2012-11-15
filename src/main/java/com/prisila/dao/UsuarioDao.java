package com.prisila.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.entidade.Usuario;
import com.prisila.util.GeralUtil;
import com.prisila.util.StringUtil;

@Component
public class UsuarioDao extends Dao<Usuario> {
	
	public UsuarioDao(Session session) {
		super(session);
	}
	
	public Usuario buscarPorUsernameSenha(String username, String senha) {
		return buscarUm(Restrictions.eq("login", username), Restrictions.eq("senha", GeralUtil.hashMD5(senha)));
	}
	
	public boolean existeUsernameSenha(String username, String senha) {
		return buscarPorUsernameSenha(username, senha) != null;
	}

	public Usuario buscarPorUsername(String username) {
		return buscarUm(Restrictions.eq("login", username));
	}

	public boolean existeUsername(String username) {
		return buscarPorUsername(username) != null;
	}

	public boolean isSenhaValida(String senha) {
		return StringUtil.notNullOrEmpty(senha) && senha.length() >= 8;
	}
}
