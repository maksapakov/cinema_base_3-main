package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Review;
import com.kata.cinema.base.models.enums.RedactorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.isModerate = false and (r.redactorStatus = 'ACTIVE' or r.redactorStatus is null) order by r.date desc")
    List<Review> findByIsModerateFalseAndRedactorStatusEqualsOrRedactorStatusNull();
}