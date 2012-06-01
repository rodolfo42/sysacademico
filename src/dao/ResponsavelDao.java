package dao;

import modelo.entidade.Responsavel;

import org.hibernate.Session;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class ResponsavelDao extends Dao<Responsavel> {

	public ResponsavelDao(Session session) {
		super(session);
	}
}
