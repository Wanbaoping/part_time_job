package com.baoge.queryvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationAndResume {

    /*申请表id*/
    private int id;
    /*应聘者id*/
    private Integer userId;
    /*应聘者名字*/
    private String userName;
    /*简历id*/
    private Integer resumeId;
    /**
     * 学校名称
     */
    private String schoolname;
    /**
     * 入学时间
     */
    /*  @DateTimeFormat(pattern ="yyyy-MM-dd")*/
    private String starttime;

    /**
     * 毕业时间
     *//*
    @DateTimeFormat(pattern ="yyyy-MM-dd")*/
    private String endtime;
    /**
     * 学历
     */
    private String education;
    /**
     * 专业
     */
    private String major;
    /**
     * 工作经验
     */
    private String experience;


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
