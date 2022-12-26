package com.kata.cinema.base.webapp.controllers.redactor;

import com.kata.cinema.base.mappers.RedactorCommentMapper;
import com.kata.cinema.base.models.dto.request.RedactorCommentRequestDto;
import com.kata.cinema.base.models.dto.response.NewsResponseDto;
import com.kata.cinema.base.models.entity.News;
import com.kata.cinema.base.models.entity.RedactorComment;
import com.kata.cinema.base.models.enums.RedactorStatus;
import com.kata.cinema.base.repositories.RedactorCommentRepository;
import com.kata.cinema.base.service.dto.NewsDtoService;
import com.kata.cinema.base.service.dto.RedactorCommentDtoService;
import com.kata.cinema.base.service.dto.impl.RedactorCommentDtoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class RedactorNewsRestController {

    private final NewsDtoService newsDtoService;
    private final RedactorCommentMapper redactorCommentMapper;

    private final RedactorCommentDtoService redactorCommentDtoService;
    private final RedactorCommentRepository redactorCommentRepository;

    @Autowired
    public RedactorNewsRestController(NewsDtoService newsDtoService,
                                      RedactorCommentMapper redactorCommentMapper, RedactorCommentDtoService redactorCommentDtoService,
                                      RedactorCommentRepository redactorCommentRepository) {
        this.newsDtoService = newsDtoService;
        this.redactorCommentMapper = redactorCommentMapper;
        this.redactorCommentDtoService = redactorCommentDtoService;
        this.redactorCommentRepository = redactorCommentRepository;
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
//        News news = newsDtoService.getNewsById(id);
        RedactorComment redactorComment = redactorCommentRepository.findRedactorCommentByNews_Id(id);

        //проверяю на соответствие условию redactorStatus = RESOLVED
        if (redactorCommentRequestDto.getRedactorStatus().equals(RedactorStatus.RESOLVED)) {

            redactorComment.setComment(redactorComment.getComment());
            redactorComment.setRedactorStatus(redactorCommentRequestDto.getRedactorStatus());
            redactorComment.getNews().setIsModerate(true);

            //Если всё нормально, то устанавливаю в новости isModerate = true
//            news.setIsModerate(true);


            //Записываю изменения в базу
//            newsDtoService.updateNews(news);
            redactorCommentRepository.save(redactorComment);
        } else {
            redactorComment.setRedactorStatus(redactorCommentRequestDto.getRedactorStatus());
            redactorComment.setComment(redactorCommentRequestDto.getComment());
            redactorCommentRepository.save(redactorComment);
        }
        //Здесь не понимаю, в моём понимании, я устанавливаю сущности параметры @RequestBody
        return ResponseEntity.ok().build();
    }
}
