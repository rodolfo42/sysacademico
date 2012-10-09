package dao;

import org.hibernate.Session;

import modelo.entidade.Sala;

public class SalaDao extends Dao<Sala> {

	public SalaDao(Session session) {
		super(session);
	}

}
