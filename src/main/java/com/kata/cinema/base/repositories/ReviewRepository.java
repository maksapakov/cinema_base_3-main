package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Transactional
    @Modifying
    @Query("update Review r set r.isModerate = true where r.id = :id")
    void updateIsModerateById(@Param("id") Long id);

    @Query("select r from Review r join fetch r.movie join fetch r.user")
    List<Review> findAllWithJoin();
}