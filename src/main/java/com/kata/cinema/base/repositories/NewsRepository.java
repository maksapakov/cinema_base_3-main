package com.kata.cinema.base.repositories;

import com.kata.cinema.base.models.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select n from News n \n" +
            "where n.isModerate = false \n" +
            "and (n.redactorStatus = 'ACTIVE' \n" +
            "    or n.redactorStatus is null)\n" +
            "order by n.date desc ")
    List<News> findListAllNewsByIsModerateAndRedactorStatus();
}
