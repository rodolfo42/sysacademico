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
		Matricula matriculaParaEditar = dao.carrega(id);
		matriculaSessao.setMatricula(matriculaParaEditar);
		
		List<Responsavel> listaResponsavelPorAluno = responsavelDao.listaPeloAluno(matriculaParaEditar.getAluno());
		result.include("responsavelListPorAluno", listaResponsavelPorAluno);
		
		return matriculaParaEditar;
	}
	
	@Put
	@Path("/matriculas/{matricula.id}")
	public void alterar(Matricula matricula) {
		Matricula matriculaNoBanco = dao.carrega(matricula.getId());
		
		matriculaNoBanco.setResponsavel(matricula.getResponsavel());
		dao.atualiza(matriculaNoBanco);
		result.redirectTo(this).listar();
	}
	
	@Put
	@Path("/matriculas/inativar/{id}")
	public void inativar(Long id) {
		dao.inativar(id);
		result.redirectTo(this).listar();
	}
	
	@Put
	@Path("/matriculas/ativar/{id}")
	public void ativar(Long id) {
		dao.ativar(id);
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
		List<Matricula> listaMatriculaPorNomeDoAluno;
		if (StringUtil.notNullOrEmpty(nomeAluno)) {
			listaMatriculaPorNomeDoAluno = dao.buscarPorNomeAluno(nomeAluno);
		}else{
			listaMatriculaPorNomeDoAluno = listar();
		}
		
		result.use(json()).from(listaMatriculaPorNomeDoAluno).include("aluno").include("responsavel").include("curso")
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
		cursoList = cursoDao.listaTudo();
		tipoAulaList = TipoAula.values();
		result.include("alunoList", alunoList);
		result.include("responsavelList", responsavelList);
		result.include("cursoList", cursoList);
		result.include("tipoAulaList", tipoAulaList);
	}
	
}
