package modelo.entidade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import modelo.constante.TipoAula;
import modelo.constante.TipoMatricula;

@Entity
public class Matricula {
	
	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Responsavel responsavel;
	@Enumerated(EnumType.ORDINAL)
	private TipoMatricula tipoMatricula;
	@ManyToOne
	private Curso curso;
	@ElementCollection
	private List<TipoAula> listaTipoAula;
	
	public Matricula(){
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
	public TipoMatricula getTipoMatricula() {
		return tipoMatricula;
	}
	public void setTipoMatricula(TipoMatricula tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
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

}
