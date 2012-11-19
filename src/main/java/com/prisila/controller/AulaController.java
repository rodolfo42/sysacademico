package com.prisila.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AulaDao;
import com.prisila.dao.AulaMatriculaDao;
import com.prisila.dao.MatriculaDao;
import com.prisila.dao.ProfessorDao;
import com.prisila.dao.SalaDao;
import com.prisila.exception.MatriculaInexistenteNaSessao;
import com.prisila.exception.TechnicalException;
import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.entidade.Aula;
import com.prisila.modelo.entidade.AulaMatricula;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.HorarioProfessor;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.MatriculaSessao;
import com.prisila.modelo.entidade.Professor;
import com.prisila.modelo.entidade.Sala;

@Resource
public class AulaController extends Controller {
	
	private final AulaDao dao;
	private final ProfessorDao professorDao;
	private final SalaDao salaDao;
	private final AulaMatriculaDao aulaMatriculaDao;
	private final MatriculaDao matriculaDao;
	private final Result result;
	private MatriculaSessao matriculaSessao;
	private long duracaoAulaEmMilisegundos;
	private static final String KEY_DURACAO_AULA = "duracao_aula";
	private static final int valorConversorSegundos = 60;
	private static final int valorConversorMilisegundos = 1000;
	
	public AulaController(AulaDao dao, ProfessorDao professorDao, SalaDao salaDao, Result result, MatriculaSessao matriculaSessao, MatriculaDao matriculaDao, AulaMatriculaDao aulaMatriculaDao) {
		this.dao = dao;
		this.professorDao = professorDao;
		this.result = result;
		this.matriculaSessao = matriculaSessao;
		this.salaDao = salaDao;
		this.matriculaDao = matriculaDao;
		this.aulaMatriculaDao = aulaMatriculaDao;
	}
	
	@Get
	public List<Professor> buscaHorariosDisponiveis(Curso curso) {
		List<Professor> lista;
		lista = professorDao.buscaHorariosDisponiveis(curso);
		return filtraSugestaoHorariosParaAula(lista);
		/*result.use(json()).from(lista).include("listaHorarioProfessor")
				.exclude("listaHorarioProfessor.criterioHoraInicio").exclude("listaHorarioProfessor.criterioHoraFim")
				.serialize();*/
	}
	
	private List<Professor> filtraSugestaoHorariosParaAula(List<Professor> listaProfessorHorarios) {
		List<Professor> professores = new ArrayList<Professor>();
		Professor novoProfessor = null;
		List<HorarioProfessor> horarios = new ArrayList<HorarioProfessor>();
		HorarioProfessor novoHorarioProfessor;
		long auxSomador;
		boolean temHorarioParaProfessor;
		
		// PropertiesLoader propertiesLoader = new PropertiesLoader();
		// duracaoAulaEmMilisegundos = converteMinutosParaMilisegundos(
		// Integer.parseInt(propertiesLoader.getValor(KEY_DURACAO_AULA)) );
		duracaoAulaEmMilisegundos = 3000000;
		
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
	public void marcar(){
		Matricula matricula = getMatriculaNaSessao();
		
		List<Professor> listaProfessor = null;
		listaProfessor = buscaHorariosDisponiveis(matricula.getCurso());
		incluirRecursosNaResult(listaProfessor);
	}
	
	@Post
	public void marcar(List<Aula> aulas){
		result.on(TechnicalException.class).forwardTo(this).marcar();
		
		Matricula matricula = getMatriculaNaSessao();
		
		if (matriculaSessao.isPrecisaSalvar()){
			matriculaDao.salvar(matricula);
		}
		
		AulaMatricula aulaMatricula = null;
		
		try {
			for (Aula aula : aulas) {
				aula.formatarAulaDaSemana();
				dao.salvar(aula);
				
				aulaMatricula = new AulaMatricula();
				aulaMatricula.setMatricula(matricula);
				aulaMatricula.setAula(aula);
				aulaMatriculaDao.salvar(aulaMatricula);
			}
		} catch (TechnicalException e) {
			LOG.error(e.getMessage());
		}
		
		result.redirectTo(this).listar();
	}
	
	@Get
	public List<AulaMatricula> listar() {
		
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
	
	

	private void incluirRecursosNaResult(List<Professor> listaProfessor){
		TipoAula[] tipoAulas = TipoAula.values();
		List<Sala> listaSala = salaDao.buscarTodos();
		result.include("salaList", listaSala);
		result.include("tipoAulas", tipoAulas);
		result.include("professorList", listaProfessor);
	}
	
}