package com.prisila.scheduler.factory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class TaskSchedulerFactory implements ComponentFactory<TaskScheduler> {

	private ThreadPoolTaskScheduler scheduler;
	
	@PostConstruct
	public void create(){
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
	}
	
	@Override
	public TaskScheduler getInstance() {
		return scheduler;
	}
	
	@PreDestroy
	public void destroy(){
		scheduler.destroy();
	}

}
