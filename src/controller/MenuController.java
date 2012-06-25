package controller;

import java.util.List;

import modelo.entidade.Aluno;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.AlunoDao;

@Resource
public class MenuController {
	
	private final AlunoDao alunoDao;
	private final Result result;
	private List<Aluno> alunosList;

	public MenuController(AlunoDao alunoDao, Result result){
		this.alunoDao = alunoDao;
		this.result = result;
	}
	
	@Get
	public void inicio() {
		alunosList = alunoDao.listaAniversariantes();
		result.include("alunosList",alunosList);
	}
	
}
