package com.baoge.service.impl;

import com.baoge.dao.JobDao;
import com.baoge.entity.Job;
import com.baoge.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobserviceImpl implements JobService {
    @Autowired
    JobDao jobDao;

    @Override
    public List<Job> getJobPlaceAndType(String place, String type) {
        if ("all".equals(place)) {
            if ("all".equals(type)) {
                place = "";
                type = "";
                return jobDao.getJobPlaceAndType(place, type);
            } else {
                place = "";
                return jobDao.getJobPlaceAndType(place, type);
            }
        } else {
            if ("all".equals(type)) {
                type = "";
                return jobDao.getJobPlaceAndType(place, type);
            } else {
                return jobDao.getJobPlaceAndType(place, type);
            }
        }

    }

    @Override
    public Job getJobInfoByid(int id) {
        return jobDao.getJobInfoByid(id);
    }

    @Override
    public List<Job> getJobsBynews() {
        return jobDao.getJobsBynews();
    }

    @Override
    public List<Job> getallJob(Integer userid) {
        return jobDao.getallJob(userid);
    }

    @Override
    public List<Job> getJobByUserAndstatus(Integer userid, int i) {
        return jobDao.getJobByUserAndstatus(userid, i);
    }

    @Override
    public Integer releaseJob(Job job, Integer user_id) {
        job.setCreatetime(new Date());
        job.setStatus(1);
        return jobDao.releaseJob(job, user_id);
    }

    @Override
    public Integer editJob(Job job) {
        job.setCreatetime(new Date());
        job.setStatus(1);
        return jobDao.editJob(job);
    }

    @Override
    public Integer deleteJobById(int id) {

        return jobDao.deleteJobById(id);
    }

    @Override
    public List<Job> allJobAct() {
        return jobDao.allJobAct();
    }

    @Override
    public List<Job> searchJob(String uname, String name, Integer status) {
        return jobDao.searchJob(uname, name, status);
    }

    @Override
    public Integer setStatus(Integer id, int i) {
        return jobDao.setStatus(id, i);
    }

    @Override
    public Job getJobInfoByid2(Integer id) {
        return jobDao.getJobInfoByid2(id);
    }

}
