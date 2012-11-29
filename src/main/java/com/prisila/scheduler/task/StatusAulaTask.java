package com.prisila.scheduler.task;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class StatusAulaTask implements Task{

	//Essa schedule vai rodar às 06h00 da manhã
	//Sem nenhum dia do mês especifico
	//Todos os meses do ano
	//De segunda a sexta
	private String cronExpressionParaMudarStatusAula = "0 0 6 ? * MON-FRI";
	
	@Override
	public void run() {
		
	}

	@Override
	public void schedule(TaskScheduler scheduler) {
		scheduler.schedule(this, new CronTrigger(cronExpressionParaMudarStatusAula));
	}

}
