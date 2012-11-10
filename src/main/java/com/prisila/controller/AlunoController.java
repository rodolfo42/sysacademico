package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Responsavel;

@Resource
public class AlunoController extends Controller {
	
	private final AlunoDao dao;
	private final ResponsavelDao responsavelDao;
	private final Result result;
	private List<Responsavel> respList;
	
	public AlunoController(AlunoDao dao, ResponsavelDao responsavelDao, Result result) {
		this.dao = dao;
		this.responsavelDao = responsavelDao;
		this.result = result;
	}
	
	@Get
	@Path("/alunos/cadastrar")
	public void cadastrar() {
		respList = responsavelDao.listaTudo();
		result.include("responsavelList", respList);
	}
	
	@Post
	@Path("/alunos/cadastrar")
	public void cadastrar(Aluno aluno) {
		dao.salvar(aluno);
		result.redirectTo(MatriculaController.class).adicionar();
	}
	
	@Get
	@Path("/alunos/{id}")
	public Aluno editar(Long id) {
		respList = responsavelDao.listaTudo();
		result.include("responsavelList", respList);
		return dao.carrega(id);
	}
	
	@Put
	@Path("/alunos/{aluno.id}")
	public void alterar(Aluno aluno) {
		dao.atualiza(aluno);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/alunos/listar")
	public List<Aluno> listar() {
		return dao.listaTudo();
	}
	
	@Delete
	@Path("/alunos/{id}")
	public void deletar(Long id) {
		dao.deletar(id);
		result.redirectTo(this).listar();
	}
	
}
