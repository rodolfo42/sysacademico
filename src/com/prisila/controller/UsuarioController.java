package com.prisila.controller;

import java.util.List;

import com.prisila.annotation.Publico;
import com.prisila.dao.UsuarioDao;
import com.prisila.modelo.entidade.Usuario;
import com.prisila.modelo.entidade.UsuarioLogado;



import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class UsuarioController {
	
	private final UsuarioDao dao;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	public UsuarioController(UsuarioDao dao, Result result, UsuarioLogado usuarioLogado) {
		this.dao = dao;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Get
	@Path("/usuario/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Path("/usuario/adicionar")
	public void adicionar(Usuario usuario) {
		usuario.setSenha(usuario.getSenhaEncriptada());
		dao.salva(usuario);
		result.redirectTo(UsuarioController.class).listar();
	}
	
	@Get
	@Path("/usuario/listar")
	public List<Usuario> listar() {
		return dao.listaTudo();
	}
	
	@Get
	@Path("/usuario/login/erro")
	public void erroLogin() {
		
	}
	
	@Get
	@Path("/login")
	@Publico
	public void login() {
		// caso o usuario esteja logado, ir para o inicio
		if(usuarioLogado.isLogado()) {
			result.redirectTo(MenuController.class).inicio();
		}
	}
	
	@Post
	@Path("/login")
	@Publico
	public void login(Usuario usuario) {
		if (dao.existeLoginSenha(usuario)) {
			usuarioLogado.login(usuario);
			result.redirectTo(MenuController.class).inicio();
		} else {
			result.include("isError", true);
			result.include("errorMessage", "Login ou senha inválidos");
			result.redirectTo(getClass()).login();
		}
	}
	
	@Get
	@Path("/logout")
	public void logout() {
		usuarioLogado.logout();
		result.redirectTo(getClass()).login();
	}
}
