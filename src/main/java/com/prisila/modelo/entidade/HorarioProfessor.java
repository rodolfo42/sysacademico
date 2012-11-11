package com.prisila.modelo.entidade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.prisila.modelo.constante.DiaDaSemana;
import com.prisila.util.StringUtil;

@Entity
public class HorarioProfessor implements Comparable<HorarioProfessor> {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long horaInicio;
	private Long horaFim;
	@Enumerated(EnumType.ORDINAL)
	private DiaDaSemana diaDaSemana;
	@Transient
	private final int criterioHoraInicio = 1;
	@Transient
	private final int criterioHoraFim = 2;
	@Transient
	private String nomeDiaDaSemana;
	@Transient
	private String horaInicioTexto;
	@Transient
	private String horaFimTexto;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHoraInicioTexto() {
		return getHoraTexto(horaInicio);
	}
	
	public void setHoraInicioTexto(String horaInicio) {
		setHoraTexto(horaInicio, criterioHoraInicio);
	}
	
	public String getHoraFimTexto() {
		return getHoraTexto(horaFim);
	}
	
	public void setHoraFimTexto(String horaFim) {
		setHoraTexto(horaFim, criterioHoraFim);
	}
	
	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}
	
	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
		this.nomeDiaDaSemana = diaDaSemana.getNome();
	}
	
	private String getHoraTexto(Long hora) {
		if (hora == null) {
			return null;
		}
		Date date = new Date();
		date.setTime(hora);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(date);
	}
	
	private void setHoraTexto(String hora, int criterio) {
		if (!StringUtil.isNullOrEmpty(hora)) {
			Date date;
			long horaLong = 0;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			try {
				date = simpleDateFormat.parse(hora);
				horaLong = date.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (criterio == criterioHoraInicio) {
				this.horaInicio = horaLong;
			} else {
				if (criterio == criterioHoraFim) {
					this.horaFim = horaLong;
				}
			}
		}
	}
	
	public Long getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
		this.horaInicioTexto = getHoraInicioTexto();
	}
	
	public Long getHoraFim() {
		return horaFim;
	}
	
	public void setHoraFim(Long horaFim) {
		this.horaFim = horaFim;
		this.horaFimTexto = getHoraFimTexto();
	}
	
	public String getNomeDiaDaSemana() {
		return nomeDiaDaSemana;
	}
	
	@Override
	public int compareTo(HorarioProfessor horarioProfessor) {
		return this.diaDaSemana.ordinal() - horarioProfessor.diaDaSemana.ordinal();
	}
	
}
