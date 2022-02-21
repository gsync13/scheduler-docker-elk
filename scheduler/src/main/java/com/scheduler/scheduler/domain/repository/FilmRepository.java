package com.scheduler.scheduler.domain.repository;

import com.scheduler.scheduler.domain.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer>{

}
