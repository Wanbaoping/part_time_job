package com.baoge.controller.admin;

import com.baoge.entity.Menu;
import com.baoge.entity.Role;
import com.baoge.service.MenuService;
import com.baoge.service.RoleService;
import com.baoge.utils.MsgResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @RequestMapping("/toRoleManager")
    public String toRoleManager() {

        return "admin/roleManager";
    }

    @RequestMapping(value = "/listRoleAct")
    @ResponseBody
    public MsgResult listRoleAct(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<Role> roles = roleService.listRole();
        PageInfo<Role> info = new PageInfo<>(roles);
        return new MsgResult(0, "", roles, info.getTotal());
    }

    @RequestMapping("/searchRole")
    @ResponseBody
    public MsgResult searchRole(@RequestParam("roleName") String roleName, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<Role> roles = roleService.searchRole(roleName);
        PageInfo<Role> info = new PageInfo<>(roles);
        return new MsgResult(0, "", roles, info.getTotal());
    }

    @RequestMapping("/addRole")
    public String addRole() {
        return "admin/addRole";
    }

    @RequestMapping(value = "/addRoleAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult addRoleAct(Role role, @RequestParam("mids") String mids) {
        Integer retNum = roleService.addRole(role);
        roleService.updateRoleMenu(role.getId(),mids);
        return MsgResult.build(1, "角色添加成功");
    }

    /*删除角色*/
    @RequestMapping(value = "/deleteRoleAct/{id}", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult deleteRoleAct(@PathVariable("id") int id) {
        if (id == 1) {
            return MsgResult.build(0, "该角色禁止删除");
        }
        int retNum = roleService.deleteRole(id);
        if (retNum == 1) {
            return MsgResult.build(1, "删除成功");
        } else {
            return MsgResult.build(0, "删除失败，请重新操作");
        }
    }

    /*批量删除角色*/
    @ResponseBody
    @RequestMapping(value = "deleteRolesAct/{ids}", method = RequestMethod.POST)
    public MsgResult deleteRoleAct(@PathVariable(value = "ids") String ids) {

        if (ids.contains("-")) {
            int retNum = roleService.deleteRoles(ids);
            /*System.out.println("retnum:"+retNum);*/
            if (retNum > 0) {
                return MsgResult.build(1, "删除成功");
            } else {
                return MsgResult.build(0, "删除失败，请重新操作");
            }
        } else {
            return MsgResult.build(0, "删除失败，请重新操作");
        }
    }

    /*来到修改角色页面*/
    @RequestMapping("/editRole/{id}")
    public String editRole(@PathVariable("id") int id, Model model) {
        Role role = roleService.getRoleById(id);
        model.addAttribute("role", role);
        return "admin/editRole";
    }

    /*修改角色*/
    @RequestMapping(value = "/editRoleAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult editRoleAct(Role role) {
        int retNum = roleService.editRole(role);
        if (retNum > 0) {
            return MsgResult.build(1, "修改成功");
        } else {
            return MsgResult.build(0, "修改失败，请重新提交");
        }

    }

    /*分配菜单*/
    @RequestMapping(value = "/setMenus/{id}", method = RequestMethod.GET)
    public String addMenus(@PathVariable("id")int id, Model model) {
        ArrayList<Integer> menuIds = new ArrayList<>();
        List<Menu> menuList = menuService.getMenuListByRoleId(id);
        for (Menu menu : menuList) {
            if (menu.getHref() != null && !("".equals(menu.getHref())))
                menuIds.add(menu.getId());
        }
        /*System.out.println(menuIds);*/
        model.addAttribute("menuIds", StringUtils.join(menuIds, ","));
        model.addAttribute("roleId", id);
        return "admin/setMenus";
    }

    @RequestMapping(value = "/setMenusAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult addMenusAct(@RequestParam("menuIds") String menuIds,
                                 @RequestParam("roleId") Integer roleId) {

        if (menuIds != null ) {
            roleService.updateRoleMenu(roleId, menuIds);
            return MsgResult.build(1, "更新成功");
        }

        return MsgResult.build(0, "参数有误");
    }

}
