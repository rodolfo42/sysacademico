package com.prisila.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.prisila.modelo.constante.TipoAula;
import com.prisila.modelo.entidade.Aluno;
import com.prisila.modelo.entidade.Curso;
import com.prisila.modelo.entidade.Matricula;
import com.prisila.modelo.entidade.Professor;
import com.prisila.modelo.entidade.Responsavel;

/**
 * Classe que providencia objetos com dados falsos para os test cases
 */
@SuppressWarnings("unchecked")
public class TesteDB {
	
	static {
		dbMap = new HashMap<Class<?>, ArrayList<?>>(0);
	}
	
	private static HashMap<Class<?>, ArrayList<?>> dbMap;
	
	public static Professor newProfessor() {
		Professor prof = new Professor();
		prof.setNome("Joao da Siva Teste");
		prof.setEmail("teste@exemplo.com");
		prof.setTelefone("3142532374");
		prof.setCelular("11998534848");
		addInstance(prof);
		return prof;
	}
	
	public static Curso newCurso() {
		Curso curso = new Curso();
		curso.setNome("Curso teste");
		addInstance(curso);
		return curso;
	}
	
	public static Responsavel newResponsavel() {
		Responsavel resp = new Responsavel();
		resp.setNome("Responsavel teste");
		resp.setEmail("teste@exemplo.com");
		resp.setTelefone("3142532374");
		resp.setCelular("11998534848");
		resp.setCep("49100000");
		resp.setCpf("39784435039");
		resp.setDataConfirmacao(Calendar.getInstance().getTime());
		addInstance(resp);
		return resp;
	}
	
	public static Aluno newAluno() {
		Aluno a = new Aluno();
		a.setNome("Aluno Teste");
		
		Calendar dataNasc;
		dataNasc = Calendar.getInstance();
		dataNasc.add(Calendar.YEAR, -20);
		
		a.setDataNascimento(dataNasc.getTime());
		a.setListaResponsavel(Arrays.asList(newResponsavel()));
		addInstance(a);
		return a;
	}
	
	public static Matricula newMatricula() {
		Matricula matr = new Matricula();
		matr.setAluno(newAluno());
		matr.setAtivo(true);
//		matr.adicionaVinculo(TipoAula.PRATICA_INDIVIDUAL);
//		matr.adicionaVinculo(TipoAula.PRATICA_GRUPO);
		matr.setCurso(newCurso());
		matr.setData(Calendar.getInstance());
		matr.setResponsavel(matr.getAluno().getListaResponsavel().get(0));
		addInstance(matr);
		return matr;
	}
	
	/**
	 * Retorna todas as instâncias cadastradas
	 * 
	 * @param <T>
	 * @param clazz
	 * @return lista de todas as instâncias
	 */
	public static <T> List<T> getAll(Class<T> clazz) {
		return (List<T>) dbMap.get(clazz);
	}
	
	public static <T> T getOne(Class<T> clazz) {
		ArrayList<T> list = (ArrayList<T>) dbMap.get(clazz);
		if (list != null) {
			/*
			 * nao precisa ser list.size() -1, pois Math.random() só retorna <
			 * 1.0, então o arredondamento sempre vai ser para baixo
			 */
			int randIndex = (int) (Math.random() * (list.size()));
			return list.get(randIndex);
		} else {
			return null;
		}
	}
	
	/**
	 * adiciona um instância ao banco
	 * 
	 * @param <T>
	 * @param thing
	 * @return
	 */
	public static <T> boolean addInstance(T thing) {
		Class<T> clazz = (Class<T>) thing.getClass();
		if (!dbMap.containsKey(clazz)) {
			dbMap.put(clazz, new ArrayList<T>(0));
		}
		return ((ArrayList<T>) dbMap.get(clazz)).add(thing);
	}
}