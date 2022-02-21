package com.scheduler.scheduler.controller;

import com.scheduler.scheduler.models.FilmApiSW;
import com.scheduler.scheduler.models.SWAPIRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test")
public class TestController {

	@Autowired
	private SWAPIRest swapi;
	
	@GetMapping(value="/{film-id}")
	public ResponseEntity<FilmApiSW> getFilm(@PathVariable("film-id") Integer filmId) throws Exception{
		return ResponseEntity.ok().body(swapi.getSWFilms(filmId).getBody());
	}
}
