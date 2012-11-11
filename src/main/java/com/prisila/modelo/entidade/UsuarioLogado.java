package com.prisila.modelo.entidade;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@Component
@SessionScoped
public class UsuarioLogado implements Serializable {
	private static final long serialVersionUID = -769652759770888432L;

	private Usuario usuario;

	private String urlAposLogin;
	
	public String getLogin() {
		return usuario.getLogin();
	}
	
	public String getNome() {
		return usuario.getNome();
	}
	
	public void login(Usuario usuario) {
		if (!isLogado()) {
			atualizarInfo(usuario);
		} else {
			logout();
			login(usuario);
		}
	}
	
	public boolean isLogado() {
		return usuario != null;
	}
	
	public void logout() {
		this.usuario = null;
		this.urlAposLogin = null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public boolean isAdmin() {
		return usuario.isAdmin();
	}

	public void setUrlAposLogin(String url) {
		this.urlAposLogin = url;
	}
	
	public String getUrlAposLogin() {
		String url = urlAposLogin;
		urlAposLogin = null;
		return url;
	}

	public void atualizarInfo(Usuario usuario) {
		this.usuario = usuario;
	}
}