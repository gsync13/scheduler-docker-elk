package com.scheduler.scheduledExecuter;

import com.scheduler.scheduler.domain.model.Film;
import com.scheduler.scheduler.domain.repository.FilmRepository;
import com.scheduler.scheduler.models.FilmApiSW;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ScheduledExecutorRepeat {

	private static int count = 1;

	@Autowired
	private FilmRepository repository;

	@PostConstruct
	public void executeScheduler() {
		System.out.println("Start Scheduler");

		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		ScheduledExecutorService ses2 = Executors.newScheduledThreadPool(3);

		Runnable task1 = () -> {
			count++;
			log.info("Running...task1 - count : {}" , count);
			log.trace("Logging at TRACE level");
			log.debug("Logging at DEBUG level");
			log.info("Logging at INFO level");
			log.warn("Logging at WARN level");
			log.error("Logging at ERROR level");
		};

		Runnable task2 = () -> {
			count++;
			log.info("Running...task1 - count : {} " , count);
			log.trace("Logging at TRACE level");
			log.debug("Logging at DEBUG level");
			log.info("Logging at INFO level");
			log.warn("Logging at WARN level");
			log.error("Logging at ERROR level");
		};

		// init Delay = 5, repeat the task every 1 second
		ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(task1, 5, 5, TimeUnit.SECONDS);
		ScheduledFuture<?> scheduledFuture2 = ses2.scheduleAtFixedRate(task2, 5, 5, TimeUnit.SECONDS);

		try {
			while (true) {
				System.out.println("count :" + count);
				Thread.sleep(2000);
				getFilmApiSw();
				if (count == 4) {
					System.out.println("Count is 4, cancel the scheduledFuture!");
					System.out.println("1: Scheduler finished?" + scheduledFuture.isDone());
					scheduledFuture.cancel(true);
					System.out.println("2: Scheduler finished?" + scheduledFuture.isDone());
					ses.shutdown();
					System.out.println("3: Scheduler finished?" + scheduledFuture.isDone());
					break;
				}
				if (count == 6) {
					System.out.println("5: Scheduler finished 2?" + scheduledFuture2.isDone());
					scheduledFuture2.cancel(true);
					ses2.shutdown();
					System.out.println("6: Scheduler finished 2?" + scheduledFuture2.isDone());
					break;
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void getFilmApiSw() {
		RestTemplate restTemplate = new RestTemplate();
		if (count >= 6)
			count = 1;
		String url = "https://swapi.dev/api/films/".concat(String.valueOf(count));

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<FilmApiSW> filmApiSw = restTemplate.exchange(url, HttpMethod.GET, entity, FilmApiSW.class);

		Film film = new Film();
		film.setTitle(filmApiSw.getBody().getTitle());
		film.setDirector(filmApiSw.getBody().getDirector());
		film.setProducer(filmApiSw.getBody().getProducer());
		film.setUrl(filmApiSw.getBody().getUrl());
		repository.save(film);

		System.out.println("film -->" + filmApiSw.getBody());
		log.info("film --> {}" , filmApiSw.getBody());
	}

}
