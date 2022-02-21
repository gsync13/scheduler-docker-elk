package com.scheduler.config;

import com.scheduler.scheduler.domain.model.Film;
import com.scheduler.scheduler.domain.repository.FilmRepository;
import com.scheduler.scheduler.models.FilmApiSW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class ThreadPoolTaskSchedulerExamples {

	@Autowired
	private ThreadPoolTaskScheduler taskScheduler;

//	@Autowired
//	private CronTrigger cronTrigger;
//
//	@Autowired
//	private PeriodicTrigger periodicTrigger;

	RestTemplate restTemplate = new RestTemplate();

	int count = 1;

	@Autowired
	private FilmRepository repository;

	@PostConstruct
	public void scheduleRunnableWithCronTrigger() {
		System.out.println("Start Scheduler");

//		taskScheduler.schedule(new RunnableTask("Current Date"), new Date());
		taskScheduler.scheduleWithFixedDelay(new RunnableTask("Fixed 10 second Delay"), 10000);
//		taskScheduler.scheduleWithFixedDelay(new RunnableTask("Current Date Fixed 1 second Delay"), new Date(), 1000);
//		taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), new Date(), 2000);
//		taskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), 2000);
////		taskScheduler.schedule(new RunnableTask("Cron Trigger"), cronTrigger);
//		taskScheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);
	}

	class RunnableTask implements Runnable {

		private String message;

		public RunnableTask(String message) {
			this.message = message;
		}

		@Override
		public void run() {
			try {
				System.out.println("Runnable Task with " + message + " on thread " + Thread.currentThread().getName());
				getFilmApiSw();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void getFilmApiSw() {
			String url = "https://swapi.dev/api/films/".concat(String.valueOf(count));

			//String url = "https://swapi.dev/api/films/1";

			if(count!=6)count++;else count=1;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

			ResponseEntity<FilmApiSW> filmApiSw = restTemplate.exchange(url, HttpMethod.GET, entity,
					FilmApiSW.class);

			Film film = new Film();
			film.setTitle(filmApiSw.getBody().getTitle());
			film.setDirector(filmApiSw.getBody().getDirector());
			film.setProducer(filmApiSw.getBody().getProducer());
			film.setUrl(filmApiSw.getBody().getUrl());
			repository.save(film);
		}
	}
}