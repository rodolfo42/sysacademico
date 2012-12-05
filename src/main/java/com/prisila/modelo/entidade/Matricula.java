package com.prisila.modelo.entidade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;

@Entity
public class Matricula {
	
	@Id
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.DATE)
	private Calendar data;
	@NotNull
	@ManyToOne
	private Aluno aluno;
	@NotNull
	@ManyToOne
	private Responsavel responsavel;
	@NotNull
	@ManyToOne
	private Curso curso;
	@NotNull
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<EsquemaAula> listaEsquemaAula;
	private boolean ativo;

	public Matricula(){
		listaEsquemaAula = new ArrayList<EsquemaAula>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Calendar getData() {
		return data;
	}
	
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Responsavel getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public void adicionaVinculo(EsquemaAula esquemaAula) {
		this.listaEsquemaAula.add(esquemaAula);
	}

	public List<EsquemaAula> getListaEsquemaAula() {
		return listaEsquemaAula;
	}

	public void setListaEsquemaAula(List<EsquemaAula> listaEsquemaAula) {
		this.listaEsquemaAula = listaEsquemaAula;
	}

	
}
