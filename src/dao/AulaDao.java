package dao;

import modelo.entidade.Aula;

import org.hibernate.Session;

public class AulaDao extends Dao<Aula> {
	
	public AulaDao(Session session) {
		super(session);
	}
	
}
