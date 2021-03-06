package com.prisila.modelo.constante;

public enum StatusAula {

	AULA_NAO_REALIZADA("Aula não realizada", "warning", "warning"),
	AULA_REALIZANDO("Aula está sendo realizada", "info", "primary"),
	AULA_REALIZADA("Aula realizada", "success", "success"),
	PROFESSOR_FALTOU("Professor faltou", "error", "danger");
	
	private String descricao;
	private String trClass;
	private String btnClass;
	
	private StatusAula(String descricao, String trClass, String btnClass) {
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
