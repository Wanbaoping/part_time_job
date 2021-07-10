package com.baoge.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    /*申请表id*/
    private int id;
    /*应聘者id*/
    private Integer userId;
    /*招聘者id*/
    private Integer recruiterId;
    /*招聘者名字*/
    private String recruiterName;
    /*职位id*/
    private String jobId;
    /*职位名字*/
    private String jobName;
    /*类型*/
    private String jobType;
    /*地点*/
    private String jobPlace;
    /*所需人数*/
    private Integer jobPersonnum;
    /**
     * 详情
     */
    private String jobIinformation;
    /**
     * 薪水
     */
    private String jobSalary;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 职位申请状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;

}
