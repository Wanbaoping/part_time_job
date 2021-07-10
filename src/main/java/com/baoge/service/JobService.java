package com.baoge.service;

import com.baoge.entity.Job;

import java.util.List;

public interface JobService {

    List<Job> getJobPlaceAndType(String place, String type);

    /*根据job_id查询*/
    Job getJobInfoByid(int id);

    List<Job> getJobsBynews();

    List<Job> getallJob(Integer userid);

    List<Job> getJobByUserAndstatus(Integer userid, int i);

    Integer releaseJob(Job job, Integer user_id);

    Integer editJob(Job job);

    Integer deleteJobById(int id);

    List<Job> allJobAct();

    List<Job> searchJob(String uname, String name, Integer status);

    Integer setStatus(Integer id, int i);

    Job getJobInfoByid2(Integer id);
}
