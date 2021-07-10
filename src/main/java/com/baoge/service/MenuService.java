package com.baoge.service;

import com.baoge.entity.Menu;
import com.baoge.utils.MenuTree;

import java.util.List;

public interface MenuService {
    /*
     * 查询所有菜单
     *
     * */
    List<Menu> getALLMenuList();

    List<MenuTree> getListALLMenuList();

    /*
     * 根据用户ID查询到3级菜单
     *
     * */
    List<Menu> getALLMenuListByUserId(Integer userId, boolean istree);

    /**
     * 根据用户id查询菜单列表(全部)
     */
    List<Menu> getMenuListAllByUserId(Integer userId);

    /*
     增加菜单
    * */
    int addMenu(Menu menu);

    /*
    通过menuId查找菜单
    */
    Menu getMenuById(int id);

    /*修改菜单*/
    int editMenu(Menu menu);
    /**
     * 删除菜单
     *
     */
    int deleteMenu(int id);

    /*根据角色id查询菜单*/
    List<Menu> getMenuListByRoleId(int roleId);



}
