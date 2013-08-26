package com.prisila.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.prisila.modelo.constante.StatusAulaAluno;

@Entity
public class AulaMatricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Aula aula;
	@ManyToOne
	private Matricula matricula;
	@Enumerated(EnumType.ORDINAL)
	private StatusAulaAluno statusAulaAluno;
	private String motivoAusencia;
	
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

	public StatusAulaAluno getStatusAulaAluno() {
		return statusAulaAluno;
	}

	public void setStatusAulaAluno(StatusAulaAluno statusAulaAluno) {
		this.statusAulaAluno = statusAulaAluno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMotivoAusencia(String motivoAusencia) {
		this.motivoAusencia = motivoAusencia;
	}

	public String getMotivoAusencia() {
		return motivoAusencia;
	}

}
