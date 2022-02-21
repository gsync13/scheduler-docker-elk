package com.scheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;


@EnableAsync
@EnableScheduling
@Configuration
@ComponentScan(
		  basePackages="com.baeldung.taskscheduler",
		  basePackageClasses={ThreadPoolTaskSchedulerExamples.class})
public class ThreadPoolTaskSchedulerConfig {
	
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
    	System.out.println("Begin Task Scheduler");
        ThreadPoolTaskScheduler threadPoolTaskScheduler
          = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix(
          "ThreadPoolTaskScheduler");
         
        System.out.println("End Task Scheduler");
        return threadPoolTaskScheduler;
    }
    
}
