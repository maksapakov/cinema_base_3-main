package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.models.dto.response.NewsResponseDto;

import java.util.List;

public interface NewsService {

    List<NewsResponseDto> getAllNewsByDate(Long id);

    List<NewsResponseDto> getNewsByDate(Long id);

}
