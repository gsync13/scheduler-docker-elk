package com.scheduler.scheduler.models;

import com.scheduler.scheduler.domain.model.Film;
import com.scheduler.scheduler.domain.repository.FilmRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class SWAPIRest {

	String url = "https://swapi.dev/api/films/";
	
	@Autowired
	private FilmRepository repository;
	   
    RestTemplate restTemplate = new RestTemplate();

	public ResponseEntity<FilmApiSW> getSWFilms(Integer filmId) throws Exception {
		try { 
			url= url.concat(filmId.toString());
			
			ResponseEntity<FilmApiSW> filmApiSw= restTemplate.exchange(url, HttpMethod.GET,generateHeader(),FilmApiSW.class);
			
			Film film= new Film();
			film.setTitle(filmApiSw.getBody().getTitle());
			film.setDirector(filmApiSw.getBody().getDirector());
			film.setProducer(filmApiSw.getBody().getProducer());
			film.setUrl(filmApiSw.getBody().getUrl());
			
			repository.save(film);
			
			return filmApiSw;
			
   		}catch(Exception e) {
   			throw new Exception("SWAPI out");
   		}
   	}
	
	public HttpEntity<String> generateHeader(){
		
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		return entity;
	}

	
}
