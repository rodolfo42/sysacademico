package controller;

import java.util.List;

import modelo.entidade.Aluno;
import modelo.entidade.Responsavel;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.AlunoDao;
import dao.ResponsavelDao;

@Resource
public class MenuController {
	
	private final AlunoDao dao;
	private final ResponsavelDao responsavelDao;
	private final Result result;
	private List<Responsavel> respList;

	public MenuController(AlunoDao dao,ResponsavelDao responsavelDao, Result result){
		this.dao = dao;
		this.responsavelDao = responsavelDao;
		this.result = result;
	}
	
	@Get
	public void inicio() {
	}
	
}
