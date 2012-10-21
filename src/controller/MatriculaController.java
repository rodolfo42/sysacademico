package controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import modelo.constante.TipoAula;
import modelo.constante.TipoMatricula;
import modelo.entidade.Aluno;
import modelo.entidade.Curso;
import modelo.entidade.Matricula;
import modelo.entidade.Responsavel;
import util.UtString;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import dao.AlunoDao;
import dao.CursoDao;
import dao.MatriculaDao;
import dao.ResponsavelDao;

@Resource
public class MatriculaController {
	
	private final MatriculaDao dao;
	private final AlunoDao alunoDao;
	private final ResponsavelDao responsavelDao;
	private final CursoDao cursoDao;
	private final Result result;
	private List<Aluno> alunoList;
	private List<Responsavel> responsavelList;
	private TipoMatricula[] tipoMatriculaList;
	private TipoAula[] tipoAulaList;
	private List<Curso> cursoList;
	
	public MatriculaController(MatriculaDao matriculaDao, AlunoDao alunoDao, ResponsavelDao responsavelDao,
			CursoDao cursoDao, Result result) {
		this.dao = matriculaDao;
		this.alunoDao = alunoDao;
		this.responsavelDao = responsavelDao;
		this.cursoDao = cursoDao;
		this.result = result;
	}
	
	@Get
	@Path("/matricula/adicionar")
	public void adicionar() {
		incluirListasNaResult();
	}
	
	@Post
	@Path("/matricula/adicionar")
	public void adicionar(Matricula matricula) {
		dao.salva(matricula);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/matricula/{id}")
	public Matricula editar(Long id) {
		incluirListasNaResult();
		return dao.carrega(id);
	}
	
	@Put
	@Path("/matricula/{matricula.id}")
	public void alterar(Matricula matricula) {
		dao.atualiza(matricula);
		result.redirectTo(this).listar();
	}
	
	@Delete
	@Path("/matricula/{id}")
	public void deletar(Long id) {
		dao.deleta(id);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/matricula/listar")
	public List<Matricula> listar() {
		return dao.listaTudo();
	}
	
	@Get
	@Path("/matricula/busca.json")
	public void buscaJson(String nomeAluno) {
		if (!UtString.isNullOrEmpty(nomeAluno)) {
			dao.buscarPorAluno(nomeAluno);
		}
		
		result.use(json()).from(dao.buscar()).include("aluno").include("responsavel").include("curso")
				.include("listaTipoAula", "listaTipoAula.nome").serialize();
	}
	
	@Get
	@Path("/matricula/aluno.json")
	public void alunoJson(String idResponsavel) {
		if (!idResponsavel.equals("0")) {
			Responsavel responsavel = new Responsavel();
			responsavel.setId(Long.parseLong(idResponsavel));
			result.use(json()).from(alunoDao.listaPeloResponsavel(responsavel)).serialize();
		} else {
			result.use(json()).from(alunoDao.listaTudo()).serialize();
		}
	}
	
	@Get
	@Path("/matricula/responsavel.json")
	public void responsavelJson(String idAluno) {
		if (!idAluno.equals("0")) {
			Aluno aluno = new Aluno();
			aluno.setId(Long.parseLong(idAluno));
			result.use(json()).from(responsavelDao.listaPeloAluno(aluno)).serialize();
		} else {
			result.use(json()).from(responsavelDao.listaTudo()).serialize();
		}
	}
	
	private void incluirListasNaResult() {
		alunoList = alunoDao.listaTudo();
		responsavelList = responsavelDao.listaTudo();
		tipoMatriculaList = TipoMatricula.values();
		cursoList = cursoDao.listaTudo();
		tipoAulaList = TipoAula.values();
		result.include("alunoList", alunoList);
		result.include("responsavelList", responsavelList);
		result.include("tipoMatriculaList", tipoMatriculaList);
		result.include("cursoList", cursoList);
		result.include("tipoAulaList", tipoAulaList);
	}
	
}
