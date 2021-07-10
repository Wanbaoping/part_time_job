package com.baoge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInfo {

    private Integer id;

    private String loginName;

    private String loginIp;

    private Integer loginStatus;

    private String loginMsg;
    //转换到页面上的时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;

    public LogInfo(String loginName,String loginIp, Integer loginStatus,String loginMsg,Date loginTime){
        this(null,loginName,loginIp,loginStatus,loginMsg,loginTime);
    }
}

