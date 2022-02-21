package com.scheduler.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@ComponentScan(basePackages = "com.scheduler.config")
@ComponentScan(basePackages = "com.scheduler.scheduledExecuter")
public class SchedulerApplication {

//	@Autowired
//	private static SWAPIRest swapi;
	
	public static void main(String[] args) {
		SpringApplication.run(SchedulerApplication.class, args);
		System.out.println("Started Application");


	}

}
