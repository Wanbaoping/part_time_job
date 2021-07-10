package com.baoge.dao;

import com.baoge.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface RoleDao {
    List<Role> listRole();

    List<Role> getRoleListByUserId(Integer id);

    List<Role> searchRole(@Param("roleName") String roleName);

    Integer addRole(Role role);

    int deleteRoles(List<Integer> del_ids);

    void deleteRoleMenus(List<Integer> del_ids);

    void deleteroleMenu(int id);

    int deleteRole(int id);

    Role getRoleById(int id);

    int editRole(Role role);

    /*根据角色id删除该角色对应的菜单*/
    void deleteMenusByRoleId(Integer roleId);

    void addRoleMenus(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);
}
