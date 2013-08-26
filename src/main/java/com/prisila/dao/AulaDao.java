package com.prisila.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;

import com.prisila.modelo.constante.StatusAula;
import com.prisila.modelo.entidade.Aula;

@Component
public class AulaDao extends Dao<Aula> {
	
	public AulaDao(Session session) {
		super(session);
	}
	
	/**
	 * Método que busca uma aula através de alguns dados:<br>
	 * - Timestamp<br>
	 * - Professor<br>
	 * - Sala
	 * @param aula
	 * @return aula, se ela não for encontrada, retorna <u>null</u>
	 */
	public Aula buscarUma(Aula aula){
		adicionarCriterion(Restrictions.eq("timestamp", aula.getTimestamp()));
		adicionarCriterion(Restrictions.or(
				Restrictions.eq("professor.id", aula.getProfessor().getId()), 
				Restrictions.eq("sala.id", aula.getSala().getId()))
		);
		return buscarUm();
	}
	
	@SuppressWarnings("unchecked")
	public List<Aula> getAulasOcorrendoAgora(){
		Calendar dataAtual = Calendar.getInstance();
		
		StringBuilder qry = new StringBuilder();
		qry.append("from Aula a")
		   .append(" where date(a.timestamp) = date(:data) ")
		   .append(" and time(a.timestamp) <= time(:hora)");
		
		return (List<Aula>) getSession().createQuery(qry.toString())
												 .setCalendarDate("data", dataAtual)
												 .setTime("hora", dataAtual.getTime())																	  
												 .list();
	}
	
	@Override
	public void salvar(Aula aula) {
		aula.setStatusAula(StatusAula.AULA_NAO_REALIZADA);
		
		super.salvar(aula);
	}
	
}
