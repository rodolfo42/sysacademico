package com.prisila.modelo.constante;

public enum TipoMatricula {
	
	//CURRICULAR("Curricular"),
	PERSONAL("Personal");
	
	private final String nome;
	
	TipoMatricula(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
}
