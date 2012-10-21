package modelo.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AulaMatricula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	private Aula aula;
	@Id
	@ManyToOne
	private Matricula matricula;
	private boolean presenca;
	
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
	
	public boolean isPresenca() {
		return presenca;
	}
	
	public void setPresenca(boolean presenca) {
		this.presenca = presenca;
	}
}
