package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

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
	
	private final UsuarioDao usuarioDao;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	public UsuarioController(UsuarioDao dao, Result result, UsuarioLogado usuarioLogado) {
		this.usuarioDao = dao;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
	
	@Get
	@Path("/usuarios/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Path("/usuarios/adicionar")
	public void adicionar(Usuario usuario, String confirmacaoSenha) {
		usuario.criptografarSenha();
		try {
			usuarioDao.salvar(usuario);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Usuário adicionado com sucesso"));
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro de usuario!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Erro inesperado ao adicionar o usuário"));
		}
		result.redirectTo(UsuarioController.class).listar();
	}
	
	@Get
	@Path("/usuarios/editar/{username}")
	public void editar(String username) {
		result.include("usuario", usuarioDao.buscarPorUsername(username));
	}
	
	@Post
	@Path("/usuarios/editar/{username}")
	public void editar(String username, Usuario usuario) {
		Usuario usuarioASerEditado = usuarioDao.buscarPorUsername(username);
		usuarioASerEditado.setLogin(usuario.getLogin());
		usuarioASerEditado.setNome(usuario.getNome());
		try {
			usuarioDao.salvar(usuarioASerEditado);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Dados editados com sucesso"));
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro de usuario!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Erro inesperado ao adicionar o usuário"));
		}
	}
	
	@Get
	@Path("/usuarios/remover/{username}")
	public void remover(String username) {
		Usuario usuario = usuarioDao.buscarPorUsername(username);
		usuarioDao.deletar(usuario.getId());
		result.redirectTo(getClass()).listar();
	}
	
	@Get
	@Path("/usuarios/listar")
	public void listar() {
		List<Usuario> usuarioList = usuarioDao.listaTudo();
		Usuario usuarioAtual = usuarioDao.buscarPorUsername(usuarioLogado.getLogin());
		usuarioList.remove(usuarioAtual);
		result.include("usuarioList", usuarioList);
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
		if (usuarioDao.existeUsernameSenha(login, senha)) {
			usuarioLogado.login(usuarioDao.buscarPorUsernameSenha(login, senha));
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
	
	/**
	 * Action para trazer as informações de login atual via JSON
	 */
	@Get
	@Path("/logininfo")
	public void loginInfo() {
		result.use(json()).from(usuarioLogado.getUsuario()).serialize();
	}
}
