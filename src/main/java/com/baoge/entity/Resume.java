package com.baoge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    /*id*/
    private Integer id;
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

    /**
     * 用户
     */
    private User user;

}
