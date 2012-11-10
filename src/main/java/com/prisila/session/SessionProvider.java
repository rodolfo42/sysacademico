package com.prisila.session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class SessionProvider implements ComponentFactory<Session> {
	
	private final SessionFactory factory;
	private Session session;
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	public SessionProvider(SessionFactory factory) {
		this.factory = factory;
	}
	
	@PostConstruct
	public void abrirSession() {
		try {
			this.session = factory.openSession();
		} catch(Exception e) {
			LOG.error("Erro ao tentar abrir a sessão", e);
		}
	}
	
	public Session getInstance() {
		return this.session;
	}
	
	@PreDestroy
	public void fecharSession() {
		this.session.close();
	}
	
}