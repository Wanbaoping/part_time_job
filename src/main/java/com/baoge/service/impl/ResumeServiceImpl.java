package com.baoge.service.impl;

import com.baoge.dao.ResumeDao;
import com.baoge.entity.Resume;
import com.baoge.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    ResumeDao resumeDao;
    @Override
    public Resume getResumeByUserid(Integer userid) {
        return resumeDao.getResumeByUserid(userid);
    }

    @Override
    public Integer editResume(Resume resume) {
        return resumeDao.editResume(resume);
    }

    @Override
    public Integer addResume(Resume resume, Integer userid) {
        return resumeDao.addResume(resume,userid);
    }
}
