package com.prisila.modelo.entidade;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.prisila.key.Key;
import com.prisila.modelo.constante.DiaDaSemana;
import com.prisila.modelo.constante.StatusAula;
import com.prisila.modelo.constante.TipoAula;
import com.prisila.util.PropertiesUtil;

@Entity
public class Aula implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Professor professor;
	@ManyToOne
	private Sala sala;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar timestamp;
	@Transient
	private Calendar timestampFim;
	@Enumerated(EnumType.ORDINAL)
	private TipoAula tipoAula;
	@Transient
	private long timestampLong;
	@Transient
	private DiaDaSemana diaDaSemana;
	@Transient
	private static int DURACAO_AULA = Key.ZERO;
	@Enumerated(EnumType.ORDINAL)
	private StatusAula statusAula; 
	@OneToMany(mappedBy="aula")
	private List<AulaMatricula> listaAulaMatricula;
	@ManyToOne
	private Curso curso;
	@Column(name="ic_reposicao")	
	private boolean reposicao;
	private String motivoAusenciaProfessor;
	
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
	public Aula clone() throws CloneNotSupportedException {
		return (Aula) super.clone();
	}
	
	public static final int getDuracaoAula(){
		if (DURACAO_AULA == Key.ZERO){
			PropertiesUtil propertiesUtil = new PropertiesUtil();
			DURACAO_AULA = Integer.parseInt(propertiesUtil.getProperty(Key.DURACAO_AULA));
		}
		
		return DURACAO_AULA;
	}

	public Calendar getTimestampFim() {
		if (timestampFim == null){
			timestampFim = (Calendar) timestamp.clone();
			timestampFim.add(Calendar.MINUTE, getDuracaoAula());
		}
		return timestampFim;
	}

	public List<AulaMatricula> getListaAulaMatricula() {
		return listaAulaMatricula;
	}

	public void setListaAulaMatricula(List<AulaMatricula> listaAulaMatricula) {
		this.listaAulaMatricula = listaAulaMatricula;
	}

	public StatusAula getStatusAula() {
		return statusAula;
	}

	public void setStatusAula(StatusAula statusAula) {
		this.statusAula = statusAula;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void setTimestampTexto(String timestampTexto) {
		Calendar calendar = Calendar.getInstance();
		Date date;
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			date = simpleDateFormat.parse(timestampTexto);
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date();
		}
		
		calendar.setTime(date);
		this.timestamp = calendar;
	}

	public void setReposicao(boolean reposicao) {
		this.reposicao = reposicao;
	}

	public boolean isReposicao() {
		return reposicao;
	}

	public void setMotivoAusenciaProfessor(String motivoAusenciaProfessor) {
		this.motivoAusenciaProfessor = motivoAusenciaProfessor;
	}

	public String getMotivoAusenciaProfessor() {
		return motivoAusenciaProfessor;
	}
	
}
