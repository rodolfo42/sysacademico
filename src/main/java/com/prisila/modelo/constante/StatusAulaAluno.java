package com.prisila.modelo.constante;

public enum StatusAulaAluno {
	AULA_NAO_REALIZADA("Aula não realizada", "warning", "warning"),
	ALUNO_FALTOU("Aluno faltou", "error", "danger"),
	ALUNO_PRESENTE("Aluno presente", "success", "success");
	
	private String descricao;
	private String trClass;
	private String btnClass;
	
	private StatusAulaAluno(String descricao, String trClass, String btnClass) {
		this.descricao = descricao;
		this.trClass = trClass;
		this.btnClass = btnClass;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTrClass() {
		return trClass;
	}

	public String getBtnClass() {
		return btnClass;
	}
}
