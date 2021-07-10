package com.baoge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPosition {
    private Integer id;
    /**
     * 申请人
     */
    private Integer user_id;
    /**
     * 职位
     */
    private Integer job_id;
    /**
     * 招聘者UserID
     */
    private Integer recruiter_id;
    /**
     * 职位申请状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createtime;
}
