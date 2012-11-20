package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

import com.prisila.annotation.Admin;
import com.prisila.annotation.Publico;
import com.prisila.dao.UsuarioDao;
import com.prisila.modelo.entidade.LoginInfo;
import com.prisila.modelo.entidade.Usuario;
import com.prisila.util.GeralUtil;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;
import com.prisila.util.StringUtil;

@Resource
public class UsuarioController extends Controller {
	
	private final UsuarioDao usuarioDao;
	private final Result result;
	private final LoginInfo loginInfo;
	private Validator validator;
	
	public UsuarioController(UsuarioDao dao, Result result, LoginInfo loginInfo, Validator validator) {
		this.usuarioDao = dao;
		this.result = result;
		this.loginInfo = loginInfo;
		this.validator = validator;
	}
	
	@Get
	@Admin
	@Path("/usuarios/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Admin
	@Path("/usuarios/adicionar")
	public void adicionar(final Usuario usuario, final String confirmacaoSenha) {
		validator.checking(new Validations() {
			{
				that(StringUtil.notNullOrEmpty(confirmacaoSenha) && confirmacaoSenha.equals(usuario.getSenha()),
						"confirmacaoSenha", "usuario.confirmacaoSenha.diferente");
				that(usuarioDao.isSenhaValida(usuario.getSenha()), "usuario.senha", "usuario.senha.minimo");
				that(!usuarioDao.existeUsername(usuario.getLogin()), "usuario.login", "usuario.login.existente");
			}
		});
		validator.validate(usuario);
		validator.onErrorUsePageOf(this).adicionar();
		try {
			usuario.criptografarSenha();
			usuarioDao.salvar(usuario);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Usuário adicionado com sucesso"));
			result.redirectTo(UsuarioController.class).listar();
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro de usuario!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR,
					"Erro inesperado ao registrar o novo usuário. Tente novamente"));
			result.redirectTo(UsuarioController.class).adicionar();
		}
	}
	
	@Get
	@Path("/perfil/editar")
	public void editarUsuarioAtual() {
		result.include("usuario", loginInfo.getUsuario());
	}
	
	@Post
	@Admin
	@Path("/perfil/editar")
	public void editarUsuarioAtual(Usuario usuario, final String senhaAtual, final String confirmacaoSenha) {
		final Usuario usuarioASerEditado = usuarioDao.buscarPorUsername(loginInfo.getLogin());
		usuarioASerEditado.setLogin(usuario.getLogin());
		usuarioASerEditado.setNome(usuario.getNome());
		
		final boolean isAlteracaoDeSenha = StringUtil.notNullOrEmpty(usuario.getSenha())
				&& StringUtil.notNullOrEmpty(senhaAtual) && StringUtil.notNullOrEmpty(confirmacaoSenha);
		
		final Usuario dadosEdicao = usuario;
		final String usernameAtual = loginInfo.getLogin();
		validator.checking(new Validations() {
			{
				if (!usernameAtual.equals(dadosEdicao.getLogin())) {
					that(!usuarioDao.existeUsername(dadosEdicao.getLogin()), "usuario.login", "usuario.login.existente");
				}
				if (isAlteracaoDeSenha) {
					that(usuarioDao.isSenhaValida(dadosEdicao.getSenha()), "usuario.senha", "usuario.senha.minimo");
					that(StringUtil.notNullOrEmpty(confirmacaoSenha) && confirmacaoSenha.equals(dadosEdicao.getSenha()),
							"confirmacaoSenha", "usuario.confirmacaoSenha.diferente");
					that(StringUtil.notNullOrEmpty(senhaAtual)
							&& GeralUtil.hashMD5(senhaAtual).equals(usuarioASerEditado.getSenha()), "senhaAtual",
							"usuario.editar.senhaatual");
				}
			}
		});
		
		validator.validate(usuarioASerEditado);
		validator.onErrorUsePageOf(this).editarUsuarioAtual();
		
		try {
			// TODO verificar se o usuario em questão está logado e atualizar
			// info de sessão
			if (isAlteracaoDeSenha) {
				usuarioASerEditado.setSenha(dadosEdicao.getSenha());
				usuarioASerEditado.criptografarSenha();
			}
			usuarioDao.salvar(usuarioASerEditado);
			loginInfo.atualizarInfo(usuarioASerEditado);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Dados editados com sucesso"));
			result.redirectTo("/");
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro do usuario logado!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR,
					"Erro inesperado ao editar os dados do usuário. Tente novamente"));
		}
	}
	
	@Get
	@Admin
	@Path("/usuarios/editar/{username}")
	public void editar(String username) {
		result.include("usuario", usuarioDao.buscarPorUsername(username));
	}
	
	@Post
	@Admin
	@Path("/usuarios/editar/{username}")
	public void editar(String username, Usuario usuario, final String confirmacaoSenha, final String senhaAtual) {
		Usuario usuarioASerEditado = usuarioDao.buscarPorUsername(username);
		usuarioASerEditado.setLogin(usuario.getLogin());
		usuarioASerEditado.setNome(usuario.getNome());
		usuarioASerEditado.setAdmin(usuario.isAdmin());
		
		final Usuario novoUsuario = usuario;
		final String usernameAtual = username;
		validator.checking(new Validations() {
			{
				if (!usernameAtual.equals(novoUsuario.getLogin())) {
					that(!usuarioDao.existeUsername(novoUsuario.getLogin()), "usuario.login", "usuario.login.existente");
				}
				// TODO permitir edicao de senha
			}
		});
		
		validator.validate(usuarioASerEditado);
		validator.onErrorUsePageOf(this).editar(username);
		try {
			// TODO verificar se o usuario em questão está logado e atualizar
			// info de sessão
			usuarioDao.salvar(usuarioASerEditado);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Dados editados com sucesso"));
			result.redirectTo(UsuarioController.class).listar();
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro de usuario!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR,
					"Erro inesperado ao editar os dados do usuário. Tente novamente"));
		}
	}
	
	@Get
	@Admin
	@Path("/usuarios/remover/{username}")
	public void remover(String username) {
		Usuario usuario = usuarioDao.buscarPorUsername(username);
		
		// TODO verificar se o usuario em questão está logado e invalidar sessão
		// dele
		if (usuario == null) {
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Usuário não existe"));
			result.redirectTo(getClass()).listar();
		}
		try {
			usuarioDao.deletar(usuario.getId());
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Usuário excluído com sucesso"));
			result.redirectTo(UsuarioController.class).listar();
		} catch (Exception e) {
			LOG.error("Erro ao remover usuario!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Erro inesperado remover o usuário. Tente novamente"));
			result.redirectTo(getClass()).listar();
		}
	}
	
	@Get
	@Admin
	@Path("/usuarios/listar")
	public void listar() {
		List<Usuario> usuarioList = usuarioDao.listaTudo();
		Usuario usuarioAtual = usuarioDao.buscarPorUsername(loginInfo.getLogin());
		usuarioList.remove(usuarioAtual);
		result.include("usuarioList", usuarioList);
	}
	
	@Get
	@Path("/login")
	@Publico
	public void login() {
		// caso o usuario esteja logado, ir para o inicio
		if (loginInfo.isLogado()) {
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
			loginInfo.login(usuarioDao.buscarPorUsernameSenha(login, senha));
			String urlAposLogin = loginInfo.getUrlAposLogin();
			if ((urlAposLogin) != null) {
				result.redirectTo(urlAposLogin);
			} else {
				result.redirectTo(MenuController.class).inicio();
			}
		} else {
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Usuário ou senha inválidos"));
			result.redirectTo(getClass()).login();
		}
	}
	
	@Get
	@Path("/logout")
	public void logout() {
		loginInfo.logout();
		setMensagem(result, new Mensagem(TipoMensagem.INFO, "Você foi deslogado do sistema"));
		result.redirectTo(getClass()).login();
	}
	
	/**
	 * Action para trazer as informações de login atual via JSON
	 */
	@Get
	@Path("/logininfo")
	public void loginInfo() {
		result.use(json()).from(loginInfo.getUsuario()).serialize();
	}
}
