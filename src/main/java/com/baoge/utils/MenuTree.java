package com.baoge.utils;

import java.util.LinkedList;
import java.util.List;

public class MenuTree {
    private Integer id;
    private String title;
    private String icon;
    private String href;
    private String target;
    private Integer parentId;
    private Integer visible;
    private Integer sort;//排序
    //复选树的必要属性
    private String checkArr = "0";//选中就是1
    private List<MenuTree> children = new LinkedList<>();

    public MenuTree() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCheckArr() {
        return checkArr;
    }

    public void setCheckArr(String checkArr) {
        this.checkArr = checkArr;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuTree{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", href='" + href + '\'' +
                ", target='" + target + '\'' +
                ", parentId=" + parentId +
                ", visible=" + visible +
                ", sort=" + sort +
                ", checkArr='" + checkArr + '\'' +
                ", children=" + children +
                '}';
    }
}
