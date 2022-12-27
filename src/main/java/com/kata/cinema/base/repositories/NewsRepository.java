package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Transactional
    @Modifying
    @Query("update News n set n.isModerate = true where n.id = :id")
    void updateIsModerateById(@Param("id") Long id);

}
