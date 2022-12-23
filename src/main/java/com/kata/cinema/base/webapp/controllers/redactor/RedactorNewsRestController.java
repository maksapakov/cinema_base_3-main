package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.mappers.RedactorCommentDtoMapper;
import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.models.enums.RedactorStatus;
import com.kata.cinema.base.service.dto.NewsDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RedactorNewsRestController {

    private final NewsDtoService newsDtoService;
    private final RedactorCommentDtoMapper redactorCommentDtoMapper;

    @Autowired
    public RedactorNewsRestController(NewsDtoService newsDtoService,
                                      RedactorCommentDtoMapper redactorCommentDtoMapper) {
        this.newsDtoService = newsDtoService;
        this.redactorCommentDtoMapper = redactorCommentDtoMapper;
    }

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    @GetMapping("/api/redactor/news")
    public List<NewsResponseDto> getNewsByIsModerateAndRedactorStatus() {
        return newsDtoService.getAllNewsByIsModerateAndRedactorStatus();
    }

    @PostMapping("/api/redactor/news/{id}")
    public ResponseEntity<?> updateNewsIsModerate(@PathVariable Long id,
                                                  @RequestBody RedactorCommentRequestDto redactorCommentRequestDto) {
        //Получаю новость по id
        News updateNewsIsModerate = newsDtoService.getNewsById(id);

        //проверяю на соответствие условию redactorStatus = RESOLVED
        if (updateNewsIsModerate.getRedactorStatus().equals(RedactorStatus.RESOLVED)) {

            //Если всё нормально, то устанавливаю в новости isModerate = true
            updateNewsIsModerate.setIsModerate(true);
            //Записываю изменения в базу
            newsDtoService.updateNews(updateNewsIsModerate);
        } else {
            newsDtoService.updateNews(updateNewsIsModerate);
        }
        //Здесь не понимаю, в моём понимании, я устанавливаю сущности параметры @RequestBody
        return ResponseEntity.ok().build();
    }
}
