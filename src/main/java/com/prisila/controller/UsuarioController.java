package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.validator.Validations;

import com.prisila.annotation.Publico;
import com.prisila.dao.UsuarioDao;
import com.prisila.modelo.entidade.Usuario;
import com.prisila.modelo.entidade.UsuarioLogado;
import com.prisila.util.GeralUtil;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;
import com.prisila.util.StringUtil;

@Resource
public class UsuarioController extends Controller {
	
	private final UsuarioDao usuarioDao;
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	private Validator validator;
	
	public UsuarioController(UsuarioDao dao, Result result, UsuarioLogado usuarioLogado, Validator validator) {
		this.usuarioDao = dao;
		this.result = result;
		this.usuarioLogado = usuarioLogado;
		this.validator = validator;
	}
	
	@Get
	@Path("/usuarios/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Path("/usuarios/adicionar")
	public void adicionar(final Usuario usuario, final String confirmacaoSenha) {
		validator.checking(new Validations() {
			{
				that(StringUtil.notEmptyOrNull(confirmacaoSenha) && confirmacaoSenha.equals(usuario.getSenha()),
						"confirmacaoSenha", "usuario.confirmacaoSenha.diferente");
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
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Erro inesperado ao registrar o novo usuário. Tente novamente"));
			result.redirectTo(UsuarioController.class).adicionar();
		}
		
	}
	
	@Get
	@Path("/perfil/editar")
	public void editarUsuarioAtual() {
		result.include("usuario", usuarioLogado.getUsuario());
	}
	
	@Post
	@Path("/perfil/editar")
	public void editarUsuarioAtual(Usuario usuario, final String senhaAtual, final String confirmacaoSenha) {
		final Usuario usuarioASerEditado = usuarioDao.buscarPorUsername(usuarioLogado.getLogin());
		usuarioASerEditado.setLogin(usuario.getLogin());
		usuarioASerEditado.setNome(usuario.getNome());
		
		final Usuario dadosEdicao = usuario;
		final String usernameAtual = usuarioLogado.getLogin();
		validator.checking(new Validations() {
			{
				if(!usernameAtual.equals(dadosEdicao.getLogin())) {
					that(!usuarioDao.existeUsername(dadosEdicao.getLogin()), "usuario.login", "usuario.login.existente");
				}
				that(usuarioDao.isSenhaValida(dadosEdicao.getSenha()), "usuario.senha", "usuario.senha.minimo");
				that(StringUtil.notEmptyOrNull(confirmacaoSenha) && confirmacaoSenha.equals(dadosEdicao.getSenha()),
						"confirmacaoSenha", "usuario.confirmacaoSenha.diferente");
				that(StringUtil.notEmptyOrNull(senhaAtual) && GeralUtil.hashMD5(senhaAtual).equals(usuarioASerEditado.getSenha()),
						"senhaAtual", "usuario.editar.senhaatual");
			}
		});
		
		validator.validate(usuarioASerEditado);
		validator.onErrorUsePageOf(this).editarUsuarioAtual();
		
		try {
			// TODO verificar se o usuario em questão está logado e atualizar info de sessão
			usuarioASerEditado.setSenha(dadosEdicao.getSenha());
			usuarioASerEditado.criptografarSenha();
			usuarioDao.salvar(usuarioASerEditado);
			usuarioLogado.atualizarInfo(usuarioASerEditado);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Dados editados com sucesso"));
			result.redirectTo("/");
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro do usuario logado!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Erro inesperado ao editar os dados do usuário. Tente novamente"));
		}
	}
	
	@Get
	@Path("/usuarios/editar/{username}")
	public void editar(String username) {
		result.include("usuario", usuarioDao.buscarPorUsername(username));
	}
	
	@Post
	@Path("/usuarios/editar/{username}")
	public void editar(String username, Usuario usuario, final String confirmacaoSenha, final String senhaAtual) {
		Usuario usuarioASerEditado = usuarioDao.buscarPorUsername(username);
		usuarioASerEditado.setLogin(usuario.getLogin());
		usuarioASerEditado.setNome(usuario.getNome());
		
		final Usuario novoUsuario = usuario;
		final String usernameAtual = username;
		validator.checking(new Validations() {
			{
				if(!usernameAtual.equals(novoUsuario.getLogin())) {
					that(!usuarioDao.existeUsername(novoUsuario.getLogin()), "usuario.login", "usuario.login.existente");
				}
				// that(usuarioDao.isSenhaValida(novoUsuario.getSenha()), "usuario.senha", "usuario.senha.minimo");
				// that(StringUtil.notEmptyOrNull(confirmacaoSenha) && confirmacaoSenha.equals(novoUsuario.getSenha()),
				//		"confirmacaoSenha", "usuario.confirmacaoSenha.diferente");
				// that(StringUtil.notEmptyOrNull(senhaAtual) && GeralUtil.hashMD5(senhaAtual).equals(novoUsuario.getSenha()),
				//		"senhaAtual", "usuario.editar.senhaatual");
			}
		});
		
		validator.validate(usuarioASerEditado);
		validator.onErrorUsePageOf(this).editar(username);
		try {
			// TODO verificar se o usuario em questão está logado e atualizar info de sessão
			usuarioDao.salvar(usuarioASerEditado);
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Dados editados com sucesso"));
			result.redirectTo(UsuarioController.class).listar();
		} catch (Exception e) {
			LOG.error("Erro ao editar cadastro de usuario!", e);
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Erro inesperado ao editar os dados do usuário. Tente novamente"));
		}
	}
	
	@Get
	@Path("/usuarios/remover/{username}")
	public void remover(String username) {
		Usuario usuario = usuarioDao.buscarPorUsername(username);

		// TODO verificar se o usuario em questão está logado e invalidar sessão
		if(usuario == null) {
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
		}
		
		
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
			String urlAposLogin = usuarioLogado.getUrlAposLogin();
			if((urlAposLogin) != null) {
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
