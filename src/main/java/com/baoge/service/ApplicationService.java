package com.baoge.service;

import com.baoge.queryvo.Application;
import com.baoge.queryvo.ApplicationAndResume;

import java.util.List;

public interface ApplicationService {
    Integer checkByUerIdAndJobIdAndRecruiterId(Integer userId, String jobId, Integer recruiterId);

    Integer addApplication(Application application);

    Integer deletePositionById(int id);

    List<ApplicationAndResume> getApplicationAndResume(Integer recruiterId);

    Integer setResumeStatus(Integer id, int i);

    Integer getApplicationIdByJobId(Integer id);
}
