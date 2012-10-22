package com.prisila.modelo.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.prisila.modelo.constante.TipoAula;




@Entity
public class Matricula {
	
	@Id
	@GeneratedValue
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date data;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Responsavel responsavel;
	@ManyToOne
	private Curso curso;
	@ElementCollection
	private List<TipoAula> listaTipoAula;
	private boolean ativo;
	
	public Matricula() {
		this.listaTipoAula = new ArrayList<TipoAula>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
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
	
	public List<TipoAula> getListaTipoAula() {
		return listaTipoAula;
	}
	
	public void setListaTipoAula(List<TipoAula> listaTipoAula) {
		this.listaTipoAula = listaTipoAula;
	}
	
	public void adicionaVinculo(TipoAula tipoAula) {
		this.listaTipoAula.add(tipoAula);
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
