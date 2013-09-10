package com.prisila.modelo.constante;

public enum PeriodoMarcarAula {
	MENSAL(1, "Mensal"),
	SEMESTRAL(6, "Semestral"),
	ANUAL(12, "Anual");
	
	int numeroMeses;
	String nome;

	PeriodoMarcarAula(int numeroMeses, String nome){
		this.numeroMeses = numeroMeses;
		this.nome = nome;
	}
	
	public int getNumeroMeses(){
		return this.numeroMeses;
	}

	public String getNome() {
		return nome;
	}
}
