package com.baoge.controller.admin;

import com.baoge.entity.Role;
import com.baoge.entity.User;
import com.baoge.service.RoleService;
import com.baoge.service.UserService;
import com.baoge.utils.MsgResult;
import com.baoge.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping("/toUserManager")
    public String toUserManager() {
        return "admin/userManager";
    }

    @RequestMapping(value = "/listUserAct")
    @ResponseBody
    public MsgResult listUserAct(int page, int limit) {
        PageHelper.startPage(page, limit);
        List<User> users = userService.listUser();
        PageInfo<User> info = new PageInfo<>(users);
        return new MsgResult(0, "", users, info.getTotal());
    }

    @RequestMapping("/searchUser")
    @ResponseBody
    public MsgResult searchUser(@RequestParam(name = "username") String username, @RequestParam(name = "sex") String sex, @RequestParam(name = "status") String status, int page, int limit) {

        PageHelper.startPage(page, limit);
        List<User> users = userService.searchUser(username, sex, status);
        PageInfo<User> info = new PageInfo<>(users);
        return new MsgResult(0, "", users, info.getTotal());
    }

    @RequestMapping("/addUser")
    public String addUser(Model model) {
        List<Role> roles = roleService.listRole();
        model.addAttribute("roles", roles);
        return "admin/addUser";
    }

    @RequestMapping(value = "/addUserAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult addUserAct(User user, String roleIds) {
        Integer retNum = userService.addUser(user);
        if (retNum > 0) {
            if (roleIds != null && !("".equals(roleIds))) {
                userService.updateUserRole(user.getId(), roleIds);
            }
            return MsgResult.build(1, "添加成功");
        } else {
            return MsgResult.build(0, "添加失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "deleteUserAct", method = RequestMethod.POST)
    public MsgResult deleteUserAct(@RequestParam(name = "id") int id) {

        if (id == 1) {
            return MsgResult.build(0, "该用户禁止删除");
        }

        int retNum = userService.deleteUser(id);
        if (retNum == 1) {
            return MsgResult.build(1, "删除成功");
        } else {
            return MsgResult.build(0, "删除失败，请重新操作");
        }
    }

    /*批量删除*/
    @ResponseBody
    @RequestMapping(value = "deleteUsersAct/{ids}", method = RequestMethod.POST)
    public MsgResult deleteUsersAct(@PathVariable(value = "ids") String ids) {

        if (ids.contains("-")) {
            int retNum = userService.deleteUsers(ids);
            /*System.out.println("retnum:"+retNum);*/
            if (retNum > 0) {
                return MsgResult.build(1, "删除成功");
            } else {
                return MsgResult.build(0, "删除失败，请重新操作");
            }
        } else {
            Integer id = Integer.parseInt(ids);
            int retNum = userService.deleteUser(id);
            /*System.out.println("ret2:"+retNum);*/
            if (retNum > 0) {
                return MsgResult.build(1, "删除成功");
            } else {
                return MsgResult.build(0, "删除失败，请重新操作");
            }
        }

    }

    /*编辑用户资料*/
    @RequestMapping(value = "/editUser/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {


        List<Integer> tmpList = new ArrayList<>();

        List<Role> roleListByUserId = roleService.getRoleListByUserId(id);
        for (Role role : roleListByUserId) {
            tmpList.add(role.getId());
        }
        model.addAttribute("checkedRoles", tmpList);

        List<Role> roles = roleService.listRole();
        model.addAttribute("roles", roles);

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "admin/editUser";
    }

    @RequestMapping(value = "/editUserAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult editUserAct(User user, String roleIds) {
        /*System.out.println("editUserAct:" + user);*/
        int retNum = userService.editUser(user);
        if (retNum > 0) {
            if (roleIds != null && !("".equals(roleIds))) {
                userService.updateUserRole(user.getId(), roleIds);
            }
            return MsgResult.build(1, "修改成功");
        } else {
            return MsgResult.build(0, "修改失败，请重新提交");
        }

    }

    /*来到用户基本资料*/
    @RequestMapping("/profile")
    public String profile(HttpSession session, Model model) {

        User userSubject = UserUtils.getSubjectUser(session);
        User user = userService.getUserById(userSubject.getId());
        session.setAttribute("loginUser", user);
        model.addAttribute("user", user);
        List<Integer> tmpList = new ArrayList<>();

        List<Role> roleListByUserId = this.roleService.getRoleListByUserId(user.getId());
        for (Role role : roleListByUserId) {
            tmpList.add(role.getId());
        }
        model.addAttribute("checkedRoles", tmpList);

        List<Role> roles = this.roleService.listRole();
        model.addAttribute("roles", roles);
        return "admin/profile";
    }

    /*修改用户资料*/
    @RequestMapping(value = "/profileAct", method = RequestMethod.POST)
    @ResponseBody
    public MsgResult profileAct(User user) {
        user.setStatus(null);
        try {
            Integer retNum = userService.editUser(user);
            if (retNum == 1) {

                return MsgResult.build(1, "修改成功");
            } else {
                return MsgResult.build(0, "修改失败，请重新提交");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return MsgResult.build(0, "修改失败，请重新提交");
        }
    }

}
