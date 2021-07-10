package com.baoge.service.impl;

import com.baoge.dao.RoleDao;
import com.baoge.entity.Role;
import com.baoge.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> listRole() {
        return roleDao.listRole();
    }

    @Override
    public List<Role> getRoleListByUserId(Integer id) {
        return roleDao.getRoleListByUserId(id);
    }

    @Override
    public List<Role> searchRole(String roleName) {
        return roleDao.searchRole(roleName);
    }

    @Override
    public Integer addRole(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public int deleteRoles(String ids) {
        List<Integer> del_ids = new ArrayList<>();
        String[] str_ids = ids.split("-");
        for (String string : str_ids) {
            del_ids.add(Integer.parseInt(string));
        }
        /*System.out.println("del:"+del_ids);*/
        if (del_ids.contains(1)) {
            return 0;
        } else {
            roleDao.deleteRoleMenus(del_ids);
            return roleDao.deleteRoles(del_ids);
        }
    }

    @Override
    public int deleteRole(int id) {
        roleDao.deleteroleMenu(id);
        return roleDao.deleteRole(id);
    }

    @Override
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Override
    public int editRole(Role role) {

        return roleDao.editRole(role);
    }

    @Override
    public void updateRoleMenu(Integer roleId, String menuIds) {
        // 删除角色对应的菜单
        roleDao.deleteMenusByRoleId(roleId);
        if (!("".equals(menuIds))) {
            // 添加菜单
            String[] ids = menuIds.split(",");
            for (String id : ids) {
                roleDao.addRoleMenus(roleId, Integer.parseInt(id));
            }
        }
    }

}
