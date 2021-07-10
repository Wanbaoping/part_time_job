package com.baoge.service;

import com.baoge.entity.LogInfo;

import java.util.List;

public interface LogInfoService {

    void addLogInfo(LogInfo logInfo);

    /*列出全部日志*/
    List<LogInfo> listLogInfo();

    /*搜索日志*/
    List<LogInfo> searchLogInfo(String loginName, String loginStatus, String start_time, String end_time);

    /*批量删除日志*/
    int deleteLogInfos(String ids);


}
