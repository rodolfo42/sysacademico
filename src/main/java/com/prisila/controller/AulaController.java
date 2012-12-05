package com.prisila.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AulaDao;
import com.prisila.dao.AulaMatriculaDao;
import com.prisila.dao.ProfessorDao;
import com.prisila.dao.SalaDao;
import com.prisila.exception.MatriculaInexistenteNaSessao;
import com.prisila.modelo.constante.StatusAula;
import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.entidade.AulaMatricula;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.HorarioProfessor;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.MatriculaSessao;
import com.prisila.modelo.entidade.Professor;
import com.prisila.modelo.entidade.Sala;
import com.prisila.util.PropertiesUtil;

@Resource
public class AulaController extends Controller {
	
	private final AulaDao dao;
	private final ProfessorDao professorDao;
	private final SalaDao salaDao;
	private final AulaMatriculaDao aulaMatriculaDao;
	private final Result result;
	private MatriculaSessao matriculaSessao;
	private long duracaoAulaEmMilisegundos;
	private static final String KEY_DURACAO_AULA = "duracao_aula";
	private static final int valorConversorSegundos = 60;
	private static final int valorConversorMilisegundos = 1000;
	
	public AulaController(AulaDao dao, ProfessorDao professorDao, SalaDao salaDao, Result result, MatriculaSessao matriculaSessao, AulaMatriculaDao aulaMatriculaDao) {
		this.dao = dao;
		this.professorDao = professorDao;
		this.result = result;
		this.matriculaSessao = matriculaSessao;
		this.salaDao = salaDao;
		this.aulaMatriculaDao = aulaMatriculaDao;
	}
	
	@Get
	public List<Professor> buscaHorariosDisponiveis(Curso curso) {
		List<Professor> lista;
		lista = professorDao.buscaHorariosDisponiveis(curso);
		return filtraSugestaoHorariosParaAula(lista);
	}
	
	private List<Professor> filtraSugestaoHorariosParaAula(List<Professor> listaProfessorHorarios) {
		List<Professor> professores = new ArrayList<Professor>();
		Professor novoProfessor = null;
		List<HorarioProfessor> horarios = new ArrayList<HorarioProfessor>();
		HorarioProfessor novoHorarioProfessor;
		long auxSomador;
		boolean temHorarioParaProfessor;
		
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		duracaoAulaEmMilisegundos = converteMinutosParaMilisegundos(Integer.parseInt(propertiesUtil.getProperty(KEY_DURACAO_AULA)));
		
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
	
	private long converteMinutosParaMilisegundos(int valorMinutos) {
		return (valorMinutos * valorConversorSegundos) * valorConversorMilisegundos;
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
		return aulaMatriculaDao.buscarAulas(getMatriculaNaSessao());
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
	@Path("/aula/editarStatusAula")
	public void editarStatusAula(AulaMatricula aulaMatricula){
		aulaMatricula.setMatricula(getMatriculaNaSessao());
		
		AulaMatricula aulaMatriculaNoBanco = aulaMatriculaDao.carrega(aulaMatricula);
		aulaMatriculaNoBanco.setStatusAula(aulaMatricula.getStatusAula());
		
		aulaMatriculaDao.atualiza(aulaMatriculaNoBanco);
		result.redirectTo(this).listar();
	}

	private void incluirRecursosNaResult(){
		TipoAula[] tipoAulas = TipoAula.values();
		List<Sala> listaSala = salaDao.buscarTodos();
		StatusAula[] statusAulas = StatusAula.values();
		result.include("salaList", listaSala);
		result.include("tipoAulas", tipoAulas);
		result.include("statusAulas", statusAulas);
	}
	
}