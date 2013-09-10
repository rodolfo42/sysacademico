package com.prisila.modelo.entidade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.prisila.exception.TechnicalException;
import com.prisila.modelo.constante.DiaDaSemana;
import com.prisila.modelo.constante.PeriodoMarcarAula;
import com.prisila.modelo.constante.TipoAula;

@Entity
public class EsquemaAula {

	@Id 
	@GeneratedValue
	private Long id;
	@Enumerated(EnumType.ORDINAL)
	private TipoAula tipoAula;
	@ManyToOne
	private Professor professor;
	@ManyToOne
	private Sala sala;
	@Enumerated(EnumType.ORDINAL)
	private DiaDaSemana diaDaSemana;
	private Long hora;
	
	public EsquemaAula(){
		
	}
	
	public EsquemaAula(TipoAula tipoAula, Professor professor, Sala sala, long horaLong, DiaDaSemana diaDaSemana){
		this.tipoAula = tipoAula;
		this.professor = professor;
		this.sala = sala;
		this.hora = horaLong;
		this.diaDaSemana = diaDaSemana;
	}
	
	public List<Calendar> getAulasParaMarcar(PeriodoMarcarAula periodoMarcarAula) throws TechnicalException{
		final int segundos = 0;
		final int semanaSeguinte = 1;
		Calendar calendarTimestamp = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		Calendar calendarSemana = Calendar.getInstance();
		
		if (hora == 0){
			throw new TechnicalException("Timestamp é zero!");
		}
		calendarTimestamp.setTimeInMillis(hora);
		
		if (diaDaSemana == null){
			throw new TechnicalException("diaDaSemana está null!");
		}
		
		List<Calendar> listaAulasMarcar = new ArrayList<Calendar>();
		
		calendar.set(Calendar.DAY_OF_WEEK, diaDaSemana.getCodigo());
		calendar.add(Calendar.WEEK_OF_MONTH, semanaSeguinte);
		calendar.set(Calendar.HOUR_OF_DAY, calendarTimestamp.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendarTimestamp.get(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, segundos);
		int mesMarcacaoAulas = calendar.get(Calendar.MONTH);
		
		for (int i = 0; i < periodoMarcarAula.getNumeroMeses(); i++) {
			
			while (mesMarcacaoAulas == calendar.get(Calendar.MONTH)){
				listaAulasMarcar.add(calendar);
				calendarSemana = (Calendar) calendar.clone();
				calendarSemana.add(Calendar.WEEK_OF_MONTH, semanaSeguinte);
				calendar = calendarSemana;
			}
			mesMarcacaoAulas = calendar.get(Calendar.MONTH);
		}
		
		return listaAulasMarcar;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoAula getTipoAula() {
		return tipoAula;
	}
	public void setTipoAula(TipoAula tipoAula) {
		this.tipoAula = tipoAula;
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
	public Long getHora() {
		return hora;
	}
	public void setHora(Long hora) {
		this.hora = hora;
	}
	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}
	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public void setHoraLong(long horaLong) {
		this.hora = horaLong;
	}
	
	public String getHoraTexto() {
		if (hora == null) {
			return null;
		}
		Date date = new Date();
		date.setTime(hora);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(date);
	}
}
