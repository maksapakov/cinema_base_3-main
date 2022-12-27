package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.models.enums.RedactorStatus;
import com.kata.cinema.base.repositories.NewsRepository;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.dto.NewsDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RedactorNewsRestController {

    private final NewsDtoService newsDtoService;
    private final RedactorCommentRepository redactorCommentRepository;
    private final NewsRepository newsRepository;

    @Autowired
    public RedactorNewsRestController(NewsDtoService newsDtoService,
                                      RedactorCommentRepository redactorCommentRepository,
                                      NewsRepository newsRepository) {
        this.newsDtoService = newsDtoService;
        this.redactorCommentRepository = redactorCommentRepository;
        this.newsRepository = newsRepository;
    }

    //доставать все новости с isModerate=false и status=ACTIVE или null сортировка по дате от самых новых
    @GetMapping("/api/redactor/news")
    public List<NewsResponseDto> getNewsByIsModerateAndRedactorStatus() {
        return newsDtoService.getAllNewsByIsModerateAndRedactorStatus();
    }

    @PostMapping("/api/redactor/news/{id}")
    public ResponseEntity<?> updateNewsIsModerate(@PathVariable Long id,
                                                  @RequestBody RedactorCommentRequestDto redactorCommentRequestDto) {

        RedactorComment redactorComment = redactorCommentRepository.findRedactorCommentByNews_Id(id);

        //проверяю на соответствие условию redactorStatus = RESOLVED
        if (redactorCommentRequestDto.getRedactorStatus().equals(RedactorStatus.RESOLVED)) {

            //устанавливаю поля RedactorComment в соответствии с RedactorCommentRequestDto
            redactorComment.setComment(redactorCommentRequestDto.getComment());
            redactorComment.setRedactorStatus(redactorCommentRequestDto.getRedactorStatus());

            //устанавливаю в новости isModerate = true
            redactorComment.getNews().setIsModerate(true);

            //Изменяю в новости поле isModerate на true и записываю изменения в базу
            newsRepository.updateIsModerateById(id);

            //Сохраняю RedactorComment
            redactorCommentRepository.save(redactorComment);
        } else {
            //Если status "ACTIVE", тогда устанавливаю поля RedactorComment в соответствии с RedactorCommentRequestDto
            redactorComment.setRedactorStatus(redactorCommentRequestDto.getRedactorStatus());
            redactorComment.setComment(redactorCommentRequestDto.getComment());

            //Записываю изменения RedactorComment в базу
            redactorCommentRepository.save(redactorComment);
        }

        return ResponseEntity.ok().build();
    }
}
