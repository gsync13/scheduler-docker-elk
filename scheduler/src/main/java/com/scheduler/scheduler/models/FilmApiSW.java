package com.scheduler.scheduler.models;

import lombok.Data;

import java.util.List;

@Data
public class FilmApiSW {

	private String title;
	
	private String director;
	
	private String producer;
	
	private String url;
	
	private List<String> characters;
	
	private List<String> planets;

}
