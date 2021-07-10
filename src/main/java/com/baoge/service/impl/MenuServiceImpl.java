package com.baoge.service.impl;

import com.baoge.dao.MenuDao;
import com.baoge.entity.Menu;
import com.baoge.service.MenuService;
import com.baoge.utils.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> getALLMenuList() {
        return tree(menuDao.getALLMenuList()/*,0*/);
    }

    @Override
    public List<MenuTree> getListALLMenuList() {
        return Menutree(menuDao.getListALLMenuList());
    }

    @Override
    public List<Menu> getALLMenuListByUserId(Integer userId, boolean istree) {
        if (istree) {
            return tree(menuDao.getALLMenuListByUserId(userId)/*,0*/);
        } else {
            return menuDao.getALLMenuListByUserId(userId);
        }

    }

    @Override
    public List<Menu> getMenuListAllByUserId(Integer userId) {
        return menuDao.getMenuListAllByUserId(userId);
    }


    @Override
    public int addMenu(Menu menu) {
        return menuDao.addMenu(menu);
    }

    @Override
    public Menu getMenuById(int id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public int editMenu(Menu menu) {
        return menuDao.editMenu(menu);
    }

    @Override
    public int deleteMenu(int id) {
        menuDao.deleteRoleMenu(id);
        return menuDao.deleteMenu(id);
    }

    @Override
    public List<Menu> getMenuListByRoleId(int roleId) {
        return menuDao.getMenuListByRoleId(roleId);
    }


    // 获取树形菜单列表
    private static List<Menu> tree(List<Menu> menus/*,Integer pid*/) {

        List<Menu> treeMenus = new LinkedList<>();
        // 获取一级级菜单
        for (Menu menu : menus) {
            if (menu.getPid() == null) {
                treeMenus.add(menu);

                // 获取二级菜单
                for (Menu menu1 : menus) {
                    if (menu1.getPid()!=null && !menu.equals(menu1) && menu1.getPid().equals(menu.getId())) {
                        menu.getChild().add(menu1);

                        // 获取三级菜单
                        for (Menu menu2 : menus) {
                            if (menu2.getPid()!=null && !menu.equals(menu2) && !menu1.equals(menu2) && menu2.getPid().equals(menu1.getId())) {
                                menu2.setChild(null);
                                menu1.getChild().add(menu2);
                            }
                        }
                    }
                }
            }
        }
        return treeMenus;

    }


    private static List<MenuTree> Menutree(List<MenuTree> menuTrees) {

        List<MenuTree> retmenus = new LinkedList<>();
        // 获取一级级菜单
        for (MenuTree menuTree : menuTrees) {
            if (menuTree.getParentId() == null) {
                retmenus.add(menuTree);

                // 获取二级菜单
                for (MenuTree menuTree1 : menuTrees) {
                    if (menuTree1.getParentId()!=null && !menuTree.equals(menuTree1) && menuTree1.getParentId().equals(menuTree.getId())) {
                        menuTree.getChildren().add(menuTree1);
                        // 获取三级菜单
                        for (MenuTree menuTree2 : menuTrees) {
                            if (menuTree2.getParentId()!=null && !menuTree.equals(menuTree2) && !menuTree1.equals(menuTree2) && menuTree2.getParentId().equals(menuTree1.getId())) {
                                menuTree2.setChildren(null);
                                menuTree1.getChildren().add(menuTree2);
                            }
                        }
                    }
                }
            }
        }
        return retmenus;

    }
}
