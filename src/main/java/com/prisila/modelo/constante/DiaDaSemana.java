package com.prisila.modelo.constante;

public enum DiaDaSemana {
	
	SEGUNDAFEIRA("Segunda-Feira"), TERCAFEIRA("Ter�a-Feira"), QUARTAFEIRA("Quarta-Feira"), QUINTAFEIRA("Quinta-Feira"), SEXTAFEIRA(
			"Sexta-Feira"), SABADO("S�bado"), DOMINGO("Domingo");
	
	private final String nome;
	
	private DiaDaSemana(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
