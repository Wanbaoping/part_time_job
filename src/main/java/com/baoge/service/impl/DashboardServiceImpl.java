package com.baoge.service.impl;

import com.baoge.dao.UserPositionDao;
import com.baoge.queryvo.Application;
import com.baoge.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    UserPositionDao userPositionDao;

    @Override
    public List<Application> getAllApplication(Integer id) {
        return userPositionDao.getAllApplication(id);
    }

    @Override
    public List<Application> getPassApplication(Integer id) {
        return userPositionDao.getPassApplication(id);
    }

    @Override
    public List<Application> getnoPassApplication(Integer id) {
        return userPositionDao.getnoPassApplication(id);
    }

    @Override
    public List<Application> getCheckingApplication(Integer id) {
        return userPositionDao.getCheckingApplication(id);
    }

}
