package com.prisila.scheduler.task;

import org.springframework.scheduling.TaskScheduler;

public interface Task extends Runnable{
	void schedule(TaskScheduler scheduler);
}
