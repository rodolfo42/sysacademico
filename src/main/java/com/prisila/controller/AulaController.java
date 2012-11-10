package com.prisila.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.prisila.dao.AulaDao;
import com.prisila.dao.ProfessorDao;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.HorarioProfessor;
import com.prisila.modelo.entidade.Professor;

@Resource
public class AulaController {
	
	private final AulaDao aulaDao;
	private final ProfessorDao professorDao;
	private final Result result;
	private final Logger log = LoggerFactory.getLogger(getClass());
	private long duracaoAulaEmMilisegundos;
	private static final String KEY_DURACAO_AULA = "duracao_aula";
	private static final int valorConversorSegundos = 60;
	private static final int valorConversorMilisegundos = 1000;
	
	public AulaController(AulaDao dao, ProfessorDao professorDao, Result result) {
		this.aulaDao = dao;
		this.professorDao = professorDao;
		this.result = result;
	}
	
	@Get
	@Path("/aula/buscarHorario.json/{curso.id}")
	public void buscarHorariosDisponiveis(Curso curso) {
		List<Professor> lista;
		lista = professorDao.buscarHorariosDisponiveis(curso);
		lista = filtraSugestaoHorariosParaAula(lista);
		result.use(json()).from(lista).include("listaHorarioProfessor")
				.exclude("listaHorarioProfessor.criterioHoraInicio").exclude("listaHorarioProfessor.criterioHoraFim")
				.serialize();
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
}