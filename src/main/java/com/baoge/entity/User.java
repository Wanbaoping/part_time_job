package com.baoge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*主键id*/
    private Integer id;
    /* 用户名*/
    private String username;
    /*密码*/
    private String password;
    /*名字*/
    private String name;
    /*性别（0：男 1：女）*/
    private Integer sex;
    /*电子邮件*/
    private String email;
    /*电话*/
    private String phone;
    /* 角色 */
    private List<Role> roles = new ArrayList<>();
    /*  状态(0：启用  1：禁止 ）*/
    private Integer status;

}
