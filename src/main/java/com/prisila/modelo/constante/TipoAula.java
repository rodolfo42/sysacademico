package com.prisila.modelo.constante;

public enum TipoAula {
	PRATICA_INDIVIDUAL("Prática Individual"), PRATICA_GRUPO("Prática em Grupo"), TEORIA_GRUPO("Teoria em Grupo");
	
	private final String nome;
	
	private TipoAula(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
