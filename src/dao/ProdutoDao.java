package dao;

import java.util.List;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class ProdutoDao extends Dao<Produto> {

	public ProdutoDao(Session session) {
		super(session);
	}
	
	public List<Produto> listaTudo() {
		return super.listaTudo(Produto.class);
	}
	
}
