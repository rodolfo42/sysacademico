package com.prisila.controller;

import java.util.List;

import com.prisila.dao.AlunoDao;
import com.prisila.modelo.entidade.Aluno;



import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class MenuController {
	
	private final AlunoDao alunoDao;
	private final Result result;
	
	public MenuController(AlunoDao alunoDao, Result result) {
		this.alunoDao = alunoDao;
		this.result = result;
	}
	
	@Get @Path("/")
	public void inicio() {
		List<Aluno> alunosList = alunoDao.listaAniversariantes();
		result.include("alunosList", alunosList);
	}
	
}
