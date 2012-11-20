package com.prisila.modelo.constante;

public enum DiaDaSemana {
	
	DOMINGO("Domingo",1), 
	SEGUNDAFEIRA("Segunda-Feira",2), 
	TERCAFEIRA("Terça-Feira",3), 
	QUARTAFEIRA("Quarta-Feira",4), 
	QUINTAFEIRA("Quinta-Feira",5), 
	SEXTAFEIRA("Sexta-Feira",6), 
	SABADO("Sábado",7);
	
	private final String nome;
	private final int codigo;
	
	private DiaDaSemana(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}

	public int getCodigo() {
		return codigo;
	}
}
