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
import com.prisila.util.UtString;

@Entity
public class HorarioProfessor {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long horaInicio;
	private Long horaFim;
	@Enumerated(EnumType.ORDINAL)
	private DiaDaSemana diaDaSemana;
	@Transient
	private final int criterioHoraInicio = 1;
	private final int criterioHoraFim = 2;
	
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
	}
	private String getHoraTexto(Long hora) {
		if (hora == null){
			return null;
		}
		Date date = new Date();
		date.setTime(hora);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(date);
	}
	private void setHoraTexto(String hora, int criterio) {
		if (!UtString.isNullOrEmpty(hora)){
			Date date;
			long horaLong = 0;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			try {
				date = simpleDateFormat.parse(hora);
				horaLong = date.getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (criterio == criterioHoraInicio){
				this.horaInicio = horaLong;
			}else{
				if (criterio == criterioHoraFim){
					this.horaFim = horaLong;
				}
			}
		}
	}
}
