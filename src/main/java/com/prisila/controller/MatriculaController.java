package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

import com.prisila.dao.AlunoDao;
import com.prisila.dao.AulaDao;
import com.prisila.dao.AulaMatriculaDao;
import com.prisila.dao.CursoDao;
import com.prisila.dao.MatriculaDao;
import com.prisila.dao.ResponsavelDao;
import com.prisila.dao.SalaDao;
import com.prisila.exception.MatriculaInexistenteNaSessao;
import com.prisila.exception.TechnicalException;
import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Aula;
import com.prisila.modelo.entidade.AulaMatricula;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.EsquemaAula;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.MatriculaSessao;
import com.prisila.modelo.entidade.Professor;
import com.prisila.modelo.entidade.Responsavel;
import com.prisila.modelo.entidade.Sala;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;
import com.prisila.util.StringUtil;

@Resource
public class MatriculaController extends Controller {
	
	private final MatriculaDao dao;
	private final AlunoDao alunoDao;
	private final SalaDao salaDao;
	private final AulaDao aulaDao;
	private final AulaMatriculaDao aulaMatriculaDao;
	private final ResponsavelDao responsavelDao;
	private final CursoDao cursoDao;
	private final Result result;
	private List<Aluno> alunoList;
	private List<Responsavel> responsavelList;
	private TipoAula[] tipoAulaList;
	private List<Curso> cursoList;
	private MatriculaSessao matriculaSessao;
	private final AulaController aulaController;
	private final Validator validator;
	private static final long ZERO = 0;
	
	public MatriculaController(MatriculaDao matriculaDao, AlunoDao alunoDao, ResponsavelDao responsavelDao, 
			SalaDao salaDao, CursoDao cursoDao, AulaDao aulaDao, AulaMatriculaDao aulaMatriculaDao, 
			Result result, MatriculaSessao matriculaSessao,	AulaController aulaController, Validator validator) {
		this.dao = matriculaDao;
		this.alunoDao = alunoDao;
		this.responsavelDao = responsavelDao;
		this.cursoDao = cursoDao;
		this.salaDao = salaDao;
		this.aulaDao = aulaDao;
		this.aulaMatriculaDao = aulaMatriculaDao;
		this.result = result;
		this.matriculaSessao = matriculaSessao;
		this.aulaController = aulaController;
		this.validator = validator;
	}
	
	@Get
	@Path("/matriculas/cadastrar")
	public void cadastrar() {
		incluirRecursosNaResult();
	}
	
	@Post
	@Path("/matriculas/finalizar")
	public void finalizar(List<Aula> aulas) {
		result.on(TechnicalException.class).forwardTo(this).esquemaAula();
		
		Matricula matricula = getMatriculaNaSessao();
		
		boolean teveErroDeConstraint = false;
		AulaMatricula aulaMatricula = null;
		Aula aulaParaMarcar = null;
		Aula aulaNoBanco = null;
		EsquemaAula esquemaAula = null;
		List<Aula> listaAulasParaMarcar = new ArrayList<Aula>();
		
		try {
			for (Aula aula : aulas) {
				esquemaAula = new EsquemaAula(aula.getTipoAula(),
											  aula.getProfessor(), 
											  aula.getSala(), 
											  aula.getTimestampLong(), 
											  aula.getDiaDaSemana());
				
				matricula.adicionaVinculo(esquemaAula);
				
				for (Calendar dataParaMarcar : esquemaAula.getAulasDoMes()) {
					aulaParaMarcar = (Aula) aula.clone();
					aulaParaMarcar.setTimestamp(dataParaMarcar);
					
					aulaNoBanco = aulaDao.buscarUma(aulaParaMarcar);
					if (aulaNoBanco == null){
						listaAulasParaMarcar.add(aulaParaMarcar);
						aulaDao.salvar(aulaParaMarcar);
					}else{
						listaAulasParaMarcar.add(aulaNoBanco);
					}
					
				}
			}
		} catch (TechnicalException e) {
			LOG.error(e.getMessage());
		} catch (CloneNotSupportedException e) {
			LOG.error(e.getMessage());
		}
		
		if (matriculaSessao.isPrecisaSalvar()){
			dao.salvar(matricula);
		}
		
		for (Aula aula : listaAulasParaMarcar) {
			aulaMatricula = new AulaMatricula();
			aulaMatricula.setMatricula(matricula);
			aulaMatricula.setAula(aula);
			try{
				aulaMatriculaDao.salvar(aulaMatricula);
			}catch (ConstraintViolationException e) {
				LOG.warn("AulaMatricula já existe",e);
				teveErroDeConstraint = true;
			}
		}
		
		if (teveErroDeConstraint){
			String mensagem = "O aluno desta matrícula já estava alocado a aula";
			setMensagem(result, new Mensagem(TipoMensagem.WARNING, mensagem));
		}else{
			String mensagem = "Aula(s) marcada(s) para esta matrícula com sucesso";
			if (matriculaSessao.isPrecisaSalvar()){
				mensagem = "Matrícula salva com sucesso<br />Aula(s) marcada(s) para esta matrícula com sucesso";
			}
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, mensagem));
		}
		
		result.redirectTo(AulaController.class).listar();
	}
	
	@Post
	@Path("/matriculas/guardaNaSessao")
	public void guardaNaSessao(final Matricula matricula) {
		validator.checking(new Validations(){
			{
				that(!matricula.getAluno().getId().equals(ZERO),"matricula.aluno.id","matricula.aluno.obrigatorio");
				that(!matricula.getResponsavel().getId().equals(ZERO),"matricula.responsavel.id","matricula.responsavel.obrigatorio");
				that(!matricula.getCurso().getId().equals(ZERO),"matricula.curso.id","matricula.curso.obrigatorio");
			}
		});
		validator.validate(matricula);
		validator.onErrorRedirectTo(this).cadastrar();
		
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
		result.redirectTo(this).esquemaAula();
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
				.include("listaEsquemaAula", "listaEsquemaAula.tipoAula.nome").serialize();
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
	
	public List<Professor> esquemaAula(){
		Matricula matricula = getMatriculaNaSessao();
		
		incluirRecursosNaResult();
		return aulaController.buscaHorariosDisponiveis(matricula.getCurso());
	}
	
	private void incluirRecursosNaResult() {
		alunoList = alunoDao.listaTudo();
		List<Sala> listaSala = salaDao.buscarTodos();
		responsavelList = responsavelDao.listaTudo();
		cursoList = cursoDao.listaTudo();
		tipoAulaList = TipoAula.values();
		result.include("alunoList", alunoList);
		result.include("responsavelList", responsavelList);
		result.include("cursoList", cursoList);
		result.include("tipoAulaList", tipoAulaList);
		result.include("salaList", listaSala);
	}
	
	private Matricula getMatriculaNaSessao() {
		Matricula matricula = null;
		try {
			matricula = matriculaSessao.getMatricula();
		} catch (MatriculaInexistenteNaSessao e) {
			LOG.error(e.getMessage());
		}
		return matricula;
	}
	
}
