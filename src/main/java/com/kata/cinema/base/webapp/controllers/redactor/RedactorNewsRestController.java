package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.service.dto.NewsDtoService;
import com.kata.cinema.base.service.entity.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class RedactorNewsRestController {

    private final NewsDtoService newsDtoService;

    public RedactorNewsRestController(NewsDtoService newsService) {
        this.newsDtoService = newsService;
    }

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    @GetMapping("/api/redactor/news")
    public List<NewsResponseDto> getNewsByIsModerateAndRedactorStatus() {
        return newsDtoService.getAllNewsByIsModerateAndRedactorStatus();
    }
}
