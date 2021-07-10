package com.baoge.service;

import com.baoge.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getallNews();

    List<News> listNews();

    Integer addNews(News news);

    int deleteNews(String ids);

    int deleteNew(Integer id);

    News getNewsById(Integer id);

    int editNews(News news);
}
