package com.prisila.session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class SessionFactoryProvider implements ComponentFactory<SessionFactory> {
	
	private SessionFactory factory;
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void abrir() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		try {
			configuration.configure();
			this.factory = configuration.buildSessionFactory();
		} catch (Exception e) {
			LOG.error("erro ao construir o session factory", e);
		}
	}
	
	public SessionFactory getInstance() {
		return this.factory;
	}
	
	@PreDestroy
	public void fechar() {
		try {
			this.factory.close();
		} catch(Exception e) {
			LOG.error("erro ao fechar o session factory", e);
		}
	}
}