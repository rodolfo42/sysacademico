package controller;

import java.util.List;

import modelo.entidade.Responsavel;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.ResponsavelDao;

@Resource
public class ResponsavelController {
	
	private final ResponsavelDao dao;
	private final Result result;

	public ResponsavelController(ResponsavelDao dao, Result result){
		this.dao = dao;
		this.result = result;
	}
	
	@Get @Path("/responsavel/adicionar")
	public void adicionar() {
	}
	
	@Post @Path("/responsavel/adicionar")
	public void adicionar(Responsavel responsavel) {
		dao.salva(responsavel);
		result.redirectTo(this).listar();
	}
	
	@Path("/responsavel/{id}/editar")
	public Responsavel editar(Long id){
		return dao.carrega(id);
	}
	
	@Path("/responsavel/{responsavel.id}")
	public void alterar(Responsavel responsavel){
		dao.atualiza(responsavel);
		result.redirectTo(this).listar();
	}
		
	
	@Get @Path("/responsavel/listar")
	public List<Responsavel> listar(){
		return dao.listaTudo();
	}
	
}
