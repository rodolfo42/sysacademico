package com.prisila.modelo.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
	
}
