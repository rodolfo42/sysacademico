package com.prisila.modelo.entidade;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

import com.prisila.exception.MatriculaInexistenteNaSessao;

@SessionScoped
@Component
public class MatriculaSessao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Matricula matricula;
	private boolean precisaSalvar;
	
	public Matricula getMatricula() throws MatriculaInexistenteNaSessao{
		if (matricula == null){
			throw new MatriculaInexistenteNaSessao("A matricula da sessao esta null");
		}
		
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public boolean isPrecisaSalvar() {
		return precisaSalvar;
	}

	public void setPrecisaSalvar(boolean precisaSalvar) {
		this.precisaSalvar = precisaSalvar;
	}
}
