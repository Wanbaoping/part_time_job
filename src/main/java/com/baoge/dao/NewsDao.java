package com.baoge.dao;

import com.baoge.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NewsDao {
    List<News> getallNews();

    List<News> listNews();

    Integer addNews(News news);

    int deleteNews(List<Integer> del_ids);

    int deleteNew(Integer id);

    News getNewsById(Integer id);

    int editNews(News news);
}
