package com.baoge.service.impl;

import com.baoge.dao.LogInfoDao;
import com.baoge.entity.LogInfo;
import com.baoge.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    LogInfoDao logInfoDao;
    @Override
    public void addLogInfo(LogInfo logInfo) {
        logInfoDao.addLogInfo(logInfo);
    }

    @Override
    public List<LogInfo> listLogInfo() {
        return logInfoDao.listLogInfo();
    }

    @Override
    public List<LogInfo> searchLogInfo(String loginName, String loginStatus, String start_time, String end_time) {
        return logInfoDao.searchLogInfo(loginName,loginStatus,start_time,end_time);
    }

    @Override
    public int deleteLogInfos(String ids) {
        List<Integer>del_ids=new ArrayList<>();
        String[] str_ids=ids.split("-");
        for (String string:str_ids){
            del_ids.add(Integer.parseInt(string));
        }
        return logInfoDao.deleteLogInfos(del_ids);
    }
}
