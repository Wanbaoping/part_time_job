package com.baoge.service;

import com.baoge.entity.Role;

import java.util.List;

public interface RoleService {
    /*查询所有角色*/
    List<Role> listRole();
    /*根据用户id查询角色*/
    List<Role> getRoleListByUserId(Integer id);

    /*搜索role*/
    List<Role> searchRole(String roleName);

    Integer addRole(Role role);

    int deleteRoles(String ids);

    int deleteRole(int id);

    Role getRoleById(int id);

    int editRole(Role role);

    void updateRoleMenu(Integer roleId, String menuIds);
}
