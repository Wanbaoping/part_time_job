package com.baoge.dao;

import com.baoge.queryvo.Application;
import com.baoge.queryvo.ApplicationAndResume;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplicationDao {
    Integer checkByUerIdAndJobIdAndRecruiterId(@Param("userId")Integer userId, @Param("jobId")String jobId, @Param("recruiterId")Integer recruiterId);

    Integer addApplication(Application application);

    Integer deletePositionById(int id);

    List<ApplicationAndResume> getApplicationAndResume(Integer recruiterId);

    Integer setResumeStatus(@Param("id") int id,@Param("i") int i);

    Integer getApplicationIdByJobId(Integer id);
}
