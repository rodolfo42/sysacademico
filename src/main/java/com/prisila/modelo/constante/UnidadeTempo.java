package com.prisila.modelo.constante;

public enum UnidadeTempo {
	
	SEGUNDO(1),
	MINUTO(60),
	HORA(60*60),
	DIA(60*60*24),
	MES(60*60*24*30),
	ANO(60*60*24*365);
	
	private int segundos;

	private UnidadeTempo(int segundos) {
		this.segundos = segundos;
	}
	
	public int getSegundos() {
		return segundos;
	}
	
	public long getMilisegundos() {
		return segundos * 1000;
	}
}