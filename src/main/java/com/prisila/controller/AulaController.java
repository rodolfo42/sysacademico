package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;

import com.prisila.dao.AulaDao;
import com.prisila.dao.AulaMatriculaDao;
import com.prisila.dao.CursoDao;
import com.prisila.dao.MatriculaDao;
import com.prisila.dao.ProfessorDao;
import com.prisila.dao.SalaDao;
import com.prisila.exception.MatriculaInexistenteNaSessao;
import com.prisila.modelo.constante.StatusAula;
import com.prisila.modelo.constante.StatusAulaAluno;
import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.entidade.Aula;
import com.prisila.modelo.entidade.AulaMatricula;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.HorarioProfessor;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.MatriculaSessao;
import com.prisila.modelo.entidade.Professor;
import com.prisila.modelo.entidade.Sala;
import com.prisila.util.Mensagem;
import com.prisila.util.Mensagem.TipoMensagem;

@Resource
public class AulaController extends Controller {
	
	private final AulaDao dao;
	private final ProfessorDao professorDao;
	private final SalaDao salaDao;
	private final AulaMatriculaDao aulaMatriculaDao;
	private final MatriculaDao matriculaDao;
	private final CursoDao cursoDao;
	private final Result result;
	private MatriculaSessao matriculaSessao;
	private long duracaoAulaEmMilisegundos;	
	
	public AulaController(AulaDao dao, ProfessorDao professorDao, SalaDao salaDao, Result result, 
			MatriculaSessao matriculaSessao, AulaMatriculaDao aulaMatriculaDao, MatriculaDao matriculaDao,
			CursoDao cursoDao, Validator validator) {
		this.dao = dao;
		this.professorDao = professorDao;
		this.result = result;
		this.matriculaSessao = matriculaSessao;
		this.salaDao = salaDao;
		this.aulaMatriculaDao = aulaMatriculaDao;
		this.matriculaDao = matriculaDao;
		this.cursoDao = cursoDao;
	}
	
	@Path("/aula")
	@Post
	public void salvar(Aula aula){		
		dao.salvar(aula);
	}
	
	@Get
	public List<Professor> buscaHorariosDisponiveis(Curso curso) {
		List<Professor> lista = professorDao.buscaHorariosDisponiveis(curso);
		curso = cursoDao.carrega(curso.getId());
		
		return filtraSugestaoHorariosParaAula(lista, curso);
	}
	
	private List<Professor> filtraSugestaoHorariosParaAula(List<Professor> listaProfessorHorarios, Curso curso) {
		List<Professor> professores = new ArrayList<Professor>();
		Professor novoProfessor = null;
		List<HorarioProfessor> horarios = new ArrayList<HorarioProfessor>();
		HorarioProfessor novoHorarioProfessor;
		long auxSomador;
		boolean temHorarioParaProfessor;
		
		duracaoAulaEmMilisegundos = curso.getDuracaoAulaMilisegundos();
		
		for (Professor professor : listaProfessorHorarios) {
			Collections.sort(professor.getListaHorarioProfessor());
			
			temHorarioParaProfessor = false;
			horarios = new ArrayList<HorarioProfessor>();
			for (HorarioProfessor horarioProfessor : professor.getListaHorarioProfessor()) {
				auxSomador = horarioProfessor.getHoraInicio();
				
				while ((auxSomador + duracaoAulaEmMilisegundos) <= horarioProfessor.getHoraFim()) {
					temHorarioParaProfessor = true;
					novoHorarioProfessor = new HorarioProfessor();
					novoHorarioProfessor.setHoraInicio(auxSomador);
					auxSomador += duracaoAulaEmMilisegundos;
					novoHorarioProfessor.setHoraFim(auxSomador);
					novoHorarioProfessor.setDiaDaSemana(horarioProfessor.getDiaDaSemana());
					horarios.add(novoHorarioProfessor);
				}
			}
			if (temHorarioParaProfessor) {
				novoProfessor = professor;
				novoProfessor.setListaHorarioProfessor(horarios);
				professores.add(novoProfessor);
			}
		}
		return professores;
	}
	
	@Get
	public List<Professor> marcar(){
		Matricula matricula = getMatriculaNaSessao();
		
		incluirRecursosNaResult();
		return buscaHorariosDisponiveis(matricula.getCurso());
	}
	
	@Get
	public List<AulaMatricula> listar() {
		incluirRecursosNaResult();
		result.include("matricula",getMatriculaNaSessao());
		return aulaMatriculaDao.buscarAulas(getMatriculaNaSessao());
	}
	
	@Get
	@Path("/aula/listar/{matricula.id}")
	public List<AulaMatricula> listar(Matricula matricula) {
		incluirRecursosNaResult();
		matricula = matriculaDao.carrega(matricula.getId());
		result.include("matricula",matricula);
		return aulaMatriculaDao.buscarAulas(matricula);
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
	
	@Put
	@Path("/aula/editarStatusAulaAluno")
	public void editarStatusAulaAluno(AulaMatricula aulaMatricula){
		
		AulaMatricula aulaMatriculaNoBanco = aulaMatriculaDao.carrega(aulaMatricula.getId());
		aulaMatriculaNoBanco.setStatusAulaAluno(aulaMatricula.getStatusAulaAluno());
		
		aulaMatriculaDao.atualiza(aulaMatriculaNoBanco);
		setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Status da aula desse aluno foi modificado com sucesso"));
		result.redirectTo(this).listarAulasOcorrendoAgora();
	}
	
	@Put
	@Path("/aula/editarStatusAula")
	public void editarStatusAula(Aula aula){
		
		Aula aulaNoBanco = dao.carrega(aula.getId());
		aulaNoBanco.setStatusAula(aula.getStatusAula());
		
		dao.atualiza(aulaNoBanco);
		setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Status da aula modificado com sucesso"));
		result.redirectTo(this).listarAulasOcorrendoAgora();
	}
	
	@Get
	@Path("/aula/lista/agora")
	public List<Aula> listarAulasOcorrendoAgora(){
		Calendar horaAtual = Calendar.getInstance();
		
		List<Aula> aulasDeHojeComecadasAntesDaHoraAtual = dao.getAulasOcorrendoAgora();
		List<Aula> aulasOcorrendoAgora = new ArrayList<Aula>();
		
		int duracaoAula;
		Calendar horaAula;
		for (Aula aula : aulasDeHojeComecadasAntesDaHoraAtual) {
			horaAula = aula.getTimestamp();
			duracaoAula = aula.getCurso().getDuracaoAula();
			horaAula.add(Calendar.MINUTE, duracaoAula);
			
			if (horaAula.after(horaAtual)){
				horaAula.add(Calendar.MINUTE, -duracaoAula);
				aulasOcorrendoAgora.add(aula);
			}
		}
		
		incluirRecursosNaResult();
		
		return aulasOcorrendoAgora;
	}
	
	@Path("/aula/atualiza/agora")
	public void marcarAulasOcorrendoAgora(){
		List<Aula> aulasOcorrendoAgora = listarAulasOcorrendoAgora();
		
		for (Aula aula : aulasOcorrendoAgora) {
			aula.setStatusAula(StatusAula.AULA_REALIZANDO);
			dao.atualiza(aula);
		}
	}
	
	@Get
	@Path("/aula/{aula.id}")
	public Aula editar(Aula aula){
		incluirRecursosNaResult();
		return dao.carrega(aula.getId());
	}
	
	@Put
	@Path("/aula/{aula.id}")
	public void alterar(Aula aula){
		Aula aulaBanco = dao.carrega(aula.getId());
		aulaBanco.setProfessor(aula.getProfessor());
		aulaBanco.setSala(aula.getSala());
		aulaBanco.setTipoAula(aula.getTipoAula());
		aulaBanco.setTimestamp(aula.getTimestamp());
		
		dao.atualiza(aulaBanco);
		result.redirectTo(this).listarAulasOcorrendoAgora();
	}
	
	@Delete
	@Path("/aula/{aula.id}")
	public void deletar(Aula aula){
		dao.deletar(aula.getId());
		result.redirectTo(this).listarAulasOcorrendoAgora();
	}
	
	/**
	 * Verifica se existe alguma aula marcada com base nos seguintes dados:
	 * <br>- Timestamp (Data e Hora)
	 * <br>- Professor
	 * <br>- Sala
	 * <br>Para poder liberar esta aula para uma reposição de outra aula perdida
	 * @param aulaReposicao
	 */
	@Get
	@Path("/aula/reposicao.json")
	public void getAulaReposicaoJson(Aula aulaReposicao, AulaMatricula aulaMatricula){
		
		boolean isJaExisteAula = dao.buscarUma(aulaReposicao) != null;
		result.use(json()).from(isJaExisteAula).serialize();
	}
	
	@Put
	@Path("/aulaMatricula/{aulaMatricula.id}")
	public void alterar(final AulaMatricula aulaMatricula){
		
		aulaMatriculaDao.atualiza(aulaMatricula);
	}
	
	@Put
	@Path("/aulaMatricula/motivoAusencia/{aulaMatricula.id}")
	public void salvaMotivoAusenciaRedirecionaParaTela(final AulaMatricula aulaMatricula){
		salvaMotivoAusencia(aulaMatricula);
		
		setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Motivo de ausência salvo"));
		result.redirectTo(this).listarAulasOcorrendoAgora();
	}

	private AulaMatricula salvaMotivoAusencia(final AulaMatricula aulaMatricula) {
		AulaMatricula aulaMatriculaBanco = aulaMatriculaDao.carrega(aulaMatricula.getId());
		
		aulaMatriculaBanco.setMotivoAusencia(aulaMatricula.getMotivoAusencia());
		alterar(aulaMatriculaBanco);
		
		return aulaMatriculaBanco;
	}
	
	@Post
	@Path("/aulaMatricula/reposicao/{aulaMatricula.id}")
	public void marcarReposicao(final Aula aulaReposicao, AulaMatricula aulaMatricula){
		aulaMatricula = salvaMotivoAusencia(aulaMatricula);
		
		try {
			Aula aula = criarAulaReposicao(aulaReposicao, aulaMatricula);
			
			criarAulaMatriculaReposicao(aulaMatricula.getMatricula(), aula);
			
			setMensagem(result, new Mensagem(TipoMensagem.SUCCESS, "Reposição marcada com sucesso"));
		} catch (CloneNotSupportedException e) {
			setMensagem(result, new Mensagem(TipoMensagem.ERROR, "Não foi possível marcar a reposição"));
			e.printStackTrace();
		}
		
		result.redirectTo(this).listarAulasOcorrendoAgora();
	}

	private void criarAulaMatriculaReposicao(Matricula matriculaReposicao, Aula aulaReposicao) {
		AulaMatricula aulaMatriculaReposicao = new AulaMatricula();
		aulaMatriculaReposicao.setAula(aulaReposicao);
		aulaMatriculaReposicao.setMatricula(matriculaReposicao);
		aulaMatriculaReposicao.setStatusAulaAluno(StatusAulaAluno.AULA_NAO_REALIZADA);
		aulaMatriculaDao.salvar(aulaMatriculaReposicao);
	}

	private Aula criarAulaReposicao(final Aula aulaReposicao, AulaMatricula aulaMatricula) throws CloneNotSupportedException {
		Aula aula = aulaMatricula.getAula().clone();
		Aula aulaProcurada;
		
		aula.setReposicao(true);
		aula.setTimestamp(aulaReposicao.getTimestamp());
		aula.setProfessor(aulaReposicao.getProfessor());
		aula.setSala(aulaReposicao.getSala());
		aula.setListaAulaMatricula(new ArrayList<AulaMatricula>());
		aulaProcurada = dao.buscarUma(aula);
		
		if (aulaProcurada == null){
			salvar(aula);
		}else{
			aula = aulaProcurada;
		}
		return aula;
	}
	
	// TODO Validar restrições para alterar qualquer dado da aula

	private void incluirRecursosNaResult(){
		TipoAula[] tipoAulas = TipoAula.values();
		List<Professor> listaProfessor = professorDao.listaTudo();
		List<Sala> listaSala = salaDao.listaTudo();
		StatusAula[] statusAulas = StatusAula.values();
		StatusAulaAluno[] statusAulaAlunos = StatusAulaAluno.values();
		result.include("professorList", listaProfessor);
		result.include("salaList", listaSala);
		result.include("tipoAulas", tipoAulas);
		result.include("statusAulas", statusAulas);
		result.include("statusAulaAlunos", statusAulaAlunos);
	}
	
}