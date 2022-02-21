package com.scheduler.scheduler.task;

import com.scheduler.scheduler.models.SWAPIRest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RunnableTask implements Runnable {
	private String message;

	@Autowired
	private SWAPIRest swapi;

	public RunnableTask(String message) {
		this.message = message;
	}

	@Override
	public void run() {

		try {
			System.out.println(
					new Date() + " Runnable Task with " + message + " on thread " + Thread.currentThread().getName());
			
			swapi.getSWFilms(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
