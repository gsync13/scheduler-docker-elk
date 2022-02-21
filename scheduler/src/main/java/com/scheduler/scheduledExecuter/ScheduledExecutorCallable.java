package com.scheduler.scheduledExecuter;

import com.scheduler.scheduler.domain.model.Film;
import com.scheduler.scheduler.domain.repository.FilmRepository;
import com.scheduler.scheduler.models.FilmApiSW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.concurrent.*;

public class ScheduledExecutorCallable {

	RestTemplate restTemplate = new RestTemplate();

	int count = 1;

	@Autowired
	private FilmRepository repository;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        
        Callable<Integer> task2 = () -> 10;

        task1();

        //run this task after 5 seconds, nonblock for task3, returns a future
        ScheduledFuture<Integer> schedule = ses.schedule(task2, 5, TimeUnit.SECONDS);

        task3();

        ScheduledExecutorCallable s = new ScheduledExecutorCallable();
        s.getFilmApiSw();
        
        // block and get the result
        System.out.println(schedule.get());

        System.out.println("shutdown!");

        ses.shutdown();

    }

    public static void task1() {
        System.out.println("Running task1...");
    }

    public static void task3() {
        System.out.println("Running task3...");
    }

	public void getFilmApiSw() {
		RestTemplate restTemplate = new RestTemplate();

		String url = "https://swapi.dev/api/films/1";
//		String url = "https://swapi.dev/api/films/".concat(String.valueOf(count));
		// String url = "https://swapi.dev/api/films/1";

//		if (count != 6)
//			count++;
//		else
//			count = 1;

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
	}
    
	
}
