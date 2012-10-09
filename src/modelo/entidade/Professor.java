package modelo.entidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Professor {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String telefone;
	private String celular;
	private String email;
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Curso> listaCurso;
	@OneToMany
	private List<HorarioProfessor> listaHorarioProfessor;
	
	public Professor() {
		listaCurso = new ArrayList<Curso>();
		listaHorarioProfessor = new ArrayList<HorarioProfessor>();
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Curso> getListaCurso() {
		return Collections.unmodifiableList(listaCurso);
	}
	public void setListaCurso(List<Curso> listaCurso) {
		this.listaCurso = listaCurso;
	}
	public void adicionaVinculo(Curso curso){
		this.listaCurso.add(curso);
	}
	public List<HorarioProfessor> getListaHorarioProfessor() {
		return listaHorarioProfessor;
	}
	public void setListaHorarioProfessor(List<HorarioProfessor> listaHorarioProfessor) {
		this.listaHorarioProfessor = listaHorarioProfessor;
	}
	public void adicionaVinculo(HorarioProfessor horarioProfessor){
		this.listaHorarioProfessor.add(horarioProfessor);
	}
}
