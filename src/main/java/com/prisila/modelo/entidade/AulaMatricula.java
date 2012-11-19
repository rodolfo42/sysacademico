package com.prisila.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.prisila.modelo.constante.StatusAula;

@Entity
public class AulaMatricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	private Aula aula;
	@Id
	@ManyToOne
	private Matricula matricula;
	@Enumerated(EnumType.ORDINAL)
	private StatusAula statusAula;
	
	public Aula getAula() {
		return aula;
	}
	
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	public Matricula getMatricula() {
		return matricula;
	}
	
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public StatusAula getStatusAula() {
		return statusAula;
	}

	public void setStatusAula(StatusAula statusAula) {
		this.statusAula = statusAula;
	}
	
}
