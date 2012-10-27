package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AlunoDao;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.UsuarioLogado;

@Resource
public class MenuController extends Controller {
	
	private final AlunoDao alunoDao;
	private final Result result;
	private final UsuarioLogado usuario;
	
	public MenuController(AlunoDao alunoDao, Result result, UsuarioLogado usuario) {
		this.alunoDao = alunoDao;
		this.result = result;
		this.usuario = usuario;
	}
	
	@Get
	@Path("/")
	public void inicio() {
		List<Aluno> alunosList = alunoDao.listaAniversariantes();
		result.include("alunosList", alunosList);
		
		result.include("usuario", usuario);
	}
	
}
