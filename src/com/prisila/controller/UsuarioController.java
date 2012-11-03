package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.annotation.Publico;
import com.prisila.dao.UsuarioDao;
import com.prisila.modelo.entidade.Usuario;
import com.prisila.modelo.entidade.UsuarioLogado;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;

@Resource
public class UsuarioController extends Controller {
	
	private final UsuarioDao dao;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	public UsuarioController(UsuarioDao dao, Result result, UsuarioLogado usuarioLogado) {
		this.dao = dao;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Get
	@Path("/usuarios/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Path("/usuarios/adicionar")
	public void adicionar(Usuario usuario) {
		usuario.setSenha(usuario.getSenhaEncriptada());
		dao.salva(usuario);
		setMensagem(result, new Mensagem(TipoMensagem.INFO, "Usuário adicionado com sucesso!"));
		result.redirectTo(UsuarioController.class).listar();
	}
	
	@Get
	@Path("/usuarios/listar")
	public List<Usuario> listar() {
		return dao.listaTudo();
	}
	
	@Get
	@Path("/usuarios/login/erro")
	public void erroLogin() {
		
	}
	
	@Get
	@Path("/login")
	@Publico
	public void login() {
		// caso o usuario esteja logado, ir para o inicio
		if (usuarioLogado.isLogado()) {
			result.redirectTo(MenuController.class).inicio();
		}
	}
	
	@Post
	@Path("/login")
	@Publico
	public void login(Usuario usuario) {
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		if (dao.existeLoginSenha(login, senha)) {
			usuarioLogado.login(dao.buscarPorLoginSenha(login, senha));
			result.redirectTo(MenuController.class).inicio();
		} else {
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Usuário ou senha inválidos"));
			result.redirectTo(getClass()).login();
		}
	}
	
	@Get
	@Path("/logout")
	public void logout() {
		usuarioLogado.logout();
		setMensagem(result, new Mensagem(TipoMensagem.INFO, "Você foi deslogado do sistema"));
		result.redirectTo(getClass()).login();
	}
}
