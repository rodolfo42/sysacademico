package com.prisila.modelo.entidade;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.prisila.modelo.constante.DiaDaSemana;
import com.prisila.modelo.constante.TipoAula;

@Entity
public class Aula implements Cloneable{
	
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Professor professor;
	@ManyToOne
	private Sala sala;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestamp;
	@Enumerated(EnumType.ORDINAL)
	private TipoAula tipoAula;
	@Transient
	private long timestampLong;
	@Transient
	private DiaDaSemana diaDaSemana;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}
	
	public Calendar getTimestamp() {
		return timestamp;
	}
	
	public void setTimestampLong(long timestampLong) {
		this.timestampLong = timestampLong;
	}
	
	public long getTimestampLong() {
		return timestampLong;
	}
	
	public TipoAula getTipoAula() {
		return tipoAula;
	}
	
	public void setTipoAula(TipoAula tipoAula) {
		this.tipoAula = tipoAula;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
