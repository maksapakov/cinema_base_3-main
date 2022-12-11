package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieByName(String name);
}
