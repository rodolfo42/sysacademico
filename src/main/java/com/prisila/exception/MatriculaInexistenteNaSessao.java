package com.prisila.exception;

public class MatriculaInexistenteNaSessao extends TechnicalException{

	private static final long serialVersionUID = 1L;
	
	public MatriculaInexistenteNaSessao() {
		super();
	}

	public MatriculaInexistenteNaSessao(String message, Throwable cause) {
		super(message, cause);
	}

	public MatriculaInexistenteNaSessao(String message) {
		super(message);
	}

	public MatriculaInexistenteNaSessao(Throwable cause) {
		super(cause);
	}
}
