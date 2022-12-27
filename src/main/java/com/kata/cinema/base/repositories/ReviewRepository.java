package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Review;
import com.kata.cinema.base.models.enums.RedactorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r \n" +
            "join fetch r.movie \n" +
            "join fetch r.user\n" +
            "where r.isModerate = false \n" +
            "order by r.date desc")
    List<Review> findByIsModerateFalseAndRedactorStatusEqualsOrRedactorStatusNull();

    @Query("select r from Review r join fetch r.movie join fetch r.user")
    List<Review> findAllWithJoin();
}