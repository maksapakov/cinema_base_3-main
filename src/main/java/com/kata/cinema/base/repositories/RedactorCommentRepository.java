package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.models.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedactorCommentRepository extends JpaRepository<RedactorComment, Long> {

    @Query("""
            select rc from RedactorComment rc\s
            join fetch rc.news\s
            join fetch rc.user\s
            join fetch rc.review\s
            where rc.news.id = :id
            or rc.review.id = :id""")
    RedactorComment findRedactorCommentByEntity_Id(@Param("id") Long id);

    @Query("""
            select rc.news from RedactorComment rc
            where rc.redactorStatus = 'ACTIVE'\s
            and rc.news.isModerate = false
            order by rc.news.date desc\s
            """)
    List<News> findByNews_IsModerateAndAndRedactorStatus();

    @Query("""
            select rc.review from RedactorComment rc
            join fetch rc.review.user
            where rc.redactorStatus = 'ACTIVE'
            and rc.review.isModerate = false\s
            order by rc.review.date desc
            """)
    List<Review> findByReviewIsModerateAndRedactorStatus();
}