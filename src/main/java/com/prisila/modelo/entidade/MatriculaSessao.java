package com.prisila.modelo.entidade;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
@Component
public class MatriculaSessao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Matricula matricula;
	
	public MatriculaSessao() {
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
}
