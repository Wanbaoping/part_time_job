package com.baoge.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer id;
    private String title;
    private String icon;
    private String href;
    private String target;
    private Integer pid;
    private Integer visible;//0显示，1不显示
    private Integer sort;//排序
    private List<Menu> child = new LinkedList<>();
    private List<Role> roles = new ArrayList<>();
    public Menu(String title, String href){
        this(null,title,null,href,null,null,null,null,null,null);
    }
}
