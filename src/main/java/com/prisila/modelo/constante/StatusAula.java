package com.prisila.modelo.constante;

public enum StatusAula {

	AULA_NAO_REALIZADA("Aula não realizada"),
	ALUNO_PRESENTE("Aluno presente"),
	ALUNO_FALTOU("Aluno faltou"),
	PROFESSOR_FALTOU("Professor faltou");
	
	private String descricao;
	
	private StatusAula(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
