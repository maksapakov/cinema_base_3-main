package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.models.enums.RedactorStatus;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RedactorCommentRepository extends JpaRepository<RedactorComment, Long> {

    @Modifying
    @Query("update RedactorComment r set r.comment = :comment where r.news.id = :id")
    int updateCommentByNews(@Param("comment") String comment, @Param("id") Long id);

    @Query("select rc from RedactorComment rc join fetch rc.news join fetch rc.user join fetch rc.review where rc.news.id = :id")
    RedactorComment findRedactorCommentByNews_Id(@Param("id") Long id);
}