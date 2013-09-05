package com.prisila.modelo.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.validator.NotEmpty;

@Entity
public class Curso {
	
	@Id
	@GeneratedValue
	private Long id;
	@NotEmpty
	private String nome;
	@ManyToMany(mappedBy = "listaCurso", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Professor> listaProfessor;
	private int duracaoAula; //duracao em minutos
	@Transient
	private int duracaoAulaMilisegundos;
	private static final int VL_CONVERSOR_SEGUNDOS = 60;
	private static final int VL_CONVERSOR_MILISEGUNDOS = 1000;
	
	public Curso() {
		listaProfessor = new ArrayList<Professor>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Professor> getListaProfessor() {
		return Collections.unmodifiableList(listaProfessor);
	}
	
	public void setListaProfessor(List<Professor> listaProfessor) {
		this.listaProfessor = listaProfessor;
	}
	
	public void adicionaVinculo(Professor professor) {
		this.listaProfessor.add(professor);
	}

	public void setDuracaoAula(int duracaoAula) {
		this.duracaoAula = duracaoAula;
		setDuracaoAulaMilisegundos(duracaoAula);
	}

	public int getDuracaoAula() {
		return duracaoAula;
	}

	public int getDuracaoAulaMilisegundos() {
		if (duracaoAulaMilisegundos == 0){
			setDuracaoAulaMilisegundos(this.duracaoAula);
		}
		return duracaoAulaMilisegundos;
	}
	
	private void setDuracaoAulaMilisegundos(int duracaoAula){
		this.duracaoAulaMilisegundos = (duracaoAula * VL_CONVERSOR_SEGUNDOS) * VL_CONVERSOR_MILISEGUNDOS;
	}
	
}
