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

import com.prisila.exception.TechnicalException;
import com.prisila.modelo.constante.DiaDaSemana;
import com.prisila.modelo.constante.TipoAula;

@Entity
public class Aula {
	
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
	
	public Calendar getTimestamp() {
		return timestamp;
	}
	
	public void setTimestampLong(long timestampLong) {
		this.timestampLong = timestampLong;
	}
	
	public void formatarAulaDaSemana() throws TechnicalException{
		int segundos = 0;
		int semanaSeguinte = 1;
		Calendar calendarTimestamp = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		
		if (timestampLong == 0){
			throw new TechnicalException("Timestamp é zero!");
		}
		calendarTimestamp.setTimeInMillis(timestampLong);
		
		if (diaDaSemana == null){
			throw new TechnicalException("diaDaSemana está null!");
		}
		
		calendar.set(Calendar.DAY_OF_WEEK, diaDaSemana.getCodigo());
		calendar.add(Calendar.WEEK_OF_MONTH, semanaSeguinte);
		calendar.set(Calendar.HOUR_OF_DAY, calendarTimestamp.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendarTimestamp.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, segundos);
		
		this.timestamp = calendar;
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
	
}
