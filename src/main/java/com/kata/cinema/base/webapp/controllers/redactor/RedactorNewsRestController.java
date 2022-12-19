package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.service.dto.NewsDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RedactorNewsRestController {

    private final NewsDtoService newsDtoService;

    @Autowired
    public RedactorNewsRestController(NewsDtoService newsService) {
        this.newsDtoService = newsService;
    }

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    @GetMapping("/api/redactor/news")
    public List<NewsResponseDto> getNewsByIsModerateAndRedactorStatus() {
        return newsDtoService.getAllNewsByIsModerateAndRedactorStatus();
    }

    @PatchMapping("/api/redactor/news/{id}")
    public void updateNewsIsModerate(@PathVariable Long id, @RequestBody RedactorCommentRequestDto redactorCommentRequestDto) {

        if (redactorCommentRequestDto.getRedactorStatus().toString().equals("RESOLVED")) {

            News updateNewsIsModerate = newsDtoService.getNewsById(id);
            updateNewsIsModerate.setIsModerate(true);
            newsDtoService.udateNews(updateNewsIsModerate);
        }
    }
}
