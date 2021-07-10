package com.baoge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;//角色描述
    private List<User> users =new ArrayList<>();
    private List<Menu> menus =new ArrayList<>();
}
