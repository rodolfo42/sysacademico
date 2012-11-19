package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.CursoDao;
import com.prisila.dao.MatriculaDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.constante.TipoMatricula;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.MatriculaSessao;
import com.prisila.modelo.entidade.Responsavel;
import com.prisila.util.StringUtil;

@Resource
public class MatriculaController extends Controller {
	
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
	private MatriculaSessao matriculaSessao;
	
	public MatriculaController(MatriculaDao matriculaDao, AlunoDao alunoDao, ResponsavelDao responsavelDao,
			CursoDao cursoDao, Result result, MatriculaSessao matriculaSessao) {
		this.dao = matriculaDao;
		this.alunoDao = alunoDao;
		this.responsavelDao = responsavelDao;
		this.cursoDao = cursoDao;
		this.result = result;
		this.matriculaSessao = matriculaSessao;
	}
	
	@Get
	@Path("/matriculas/cadastrar")
	public void cadastrar() {
		incluirListasNaResult();
	}
	
	@Post
	@Path("/matriculas/cadastrar")
	public void cadastrar(Matricula matricula) {
		dao.salvar(matricula);
	}
	
	@Post
	@Path("/matriculas/guardaNaSessao")
	public void guardaNaSessao(Matricula matricula) {
		Matricula matriculaAux = dao.buscarPorAluno(matricula.getAluno())
									.buscarPorResponsavel(matricula.getResponsavel())
									.buscarPorCurso(matricula.getCurso())
									.buscarUm();
		if (matriculaAux != null){
			matriculaSessao.setMatricula(matriculaAux);
			matriculaSessao.setPrecisaSalvar(false);
		}else{
			matriculaSessao.setMatricula(matricula);
			matriculaSessao.setPrecisaSalvar(true);
		}
		result.redirectTo(AulaController.class).marcar();
	}
	
	@Get
	@Path("/matriculas/{id}")
	public Matricula editar(Long id) {
		incluirListasNaResult();
		return dao.carrega(id);
	}
	
	@Put
	@Path("/matriculas/{matricula.id}")
	public void alterar(Matricula matricula) {
		dao.atualiza(matricula);
		result.redirectTo(this).listar();
	}
	
	@Delete
	@Path("/matriculas/{id}")
	public void deletar(Long id) {
		dao.deletar(id);
		result.redirectTo(this).listar();
	}
	
	@Get
	@Path("/matriculas/listar")
	public List<Matricula> listar() {
		return dao.listaTudo();
	}
	
	@Get
	@Path("/matriculas/busca.json")
	public void buscaJson(String nomeAluno) {
		if (StringUtil.notNullOrEmpty(nomeAluno)) {
			dao.buscarPorNomeAluno(nomeAluno);
		}
		
		result.use(json()).from(dao.buscarTodos()).include("aluno").include("responsavel").include("curso")
				.include("listaTipoAula", "listaTipoAula.nome").serialize();
	}
	
	@Get
	@Path("/matriculas/aluno.json")
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
	@Path("/matriculas/responsavel.json")
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

	public MatriculaSessao getMatriculaSessao() {
		return matriculaSessao;
	}
	
}
