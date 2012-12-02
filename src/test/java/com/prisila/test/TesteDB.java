package com.prisila.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.prisila.modelo.entidade.Professor;

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
	
	/**
	 * adiciona um instância ao banco
	 * @param <T>
	 * @param thing
	 * @return
	 */
	public static <T> boolean addInstance(T thing) {
		Class<T> clazz = (Class<T>) thing.getClass();
		if(!dbMap.containsKey(clazz)) {
			dbMap.put(clazz, new ArrayList<T>(0));
		}
		return ((ArrayList<T>) dbMap.get(clazz)).add(thing);
	}
}