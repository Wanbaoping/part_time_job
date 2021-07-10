package com.baoge.dao;

import com.baoge.entity.LogInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface LogInfoDao {
    void addLogInfo(LogInfo logInfo);

    /*列出全部日志*/
    List<LogInfo> listLogInfo();
    /*搜索日志*/
    List<LogInfo> searchLogInfo(@Param(value = "loginName") String loginName, @Param(value = "loginStatus") String loginStatus, @Param(value = "start_time") String start_time, @Param(value = "end_time") String end_time);

    int deleteLogInfos(List<Integer> del_ids);


}
