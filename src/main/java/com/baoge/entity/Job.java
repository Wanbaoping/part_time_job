package com.baoge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private String type;
    /**
     * 人数
     */
    private String personnum;
    /**
     * 地点
     */
    private String place;
    /**
     * 详情
     */
    private String information;
    /**
     * 薪水
     */
    private String salary;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 发布人
     */
    private User user;
    /**
     * 创建时间
     */
    /**
    转换到页面上的时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    /**
     * 审核状态0：通过，1不通过
     */
    private Integer status;

}
