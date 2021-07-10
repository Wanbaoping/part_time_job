package com.baoge.dao;

import com.baoge.queryvo.Application;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserPositionDao {
    List<Application> getAllApplication(Integer id);

    List<Application> getPassApplication(Integer id);

    List<Application> getnoPassApplication(Integer id);

    List<Application> getCheckingApplication(Integer id);
}
