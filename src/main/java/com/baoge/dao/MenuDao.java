package com.baoge.dao;

import com.baoge.entity.Menu;
import com.baoge.utils.MenuTree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface MenuDao {

    List<Menu> getALLMenuList();

    List<MenuTree> getListALLMenuList();

    List<Menu> getALLMenuListByUserId(Integer userId);

    List<Menu> getMenuListAllByUserId(Integer userId);
    /*
    增加菜单
    */
    int addMenu(Menu menu);

    /*通过menuId查找菜单*/
    Menu getMenuById(int id);

    /**
     * 删除菜单
     */
    int deleteMenu(int id);
    /*修改菜单*/
    int editMenu(Menu menu);
    /**
     * 根据菜单id删除角色菜单中的记录
     */
    Integer deleteRoleMenu(int menu_id);


    List<Menu> getMenuListByRoleId(int roleId);



}