package dao;

import modelo.entidade.Sala;

import org.hibernate.Session;

public class SalaDao extends Dao<Sala> {

	public SalaDao(Session session) {
		super(session);
	}

}
