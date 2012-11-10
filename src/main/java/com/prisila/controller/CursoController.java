package com.prisila.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.CursoDao;
import com.prisila.modelo.entidade.Curso;

@Resource
public class CursoController extends Controller {
	
	private final CursoDao dao;
	private final Result result;
	
	public CursoController(CursoDao dao, Result result) {
		this.dao = dao;
		this.result = result;
	}
	
	@Get
	@Path("/cursos/adicionar")
	public void adicionar() {
	}
	
	@Post
	@Path("/cursos/adicionar")
	public void adicionar(Curso curso) {
		dao.salvar(curso);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/cursos/{id}")
	public Curso editar(Long id) {
		return dao.carrega(id);
	}
	
	@Put
	@Path("/cursos/{curso.id}")
	public void alterar(Curso curso) {
		dao.atualiza(curso);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/cursos/listar")
	public List<Curso> listar() {
		return dao.listaTudo();
	}
	
	@Delete
	@Path("/cursos/{id}")
	public void deletar(Long id) {
		dao.deletar(id);
		result.redirectTo(this).listar();
	}
	
}
