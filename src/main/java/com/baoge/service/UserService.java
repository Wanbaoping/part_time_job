package com.baoge.service;

import com.baoge.entity.User;
import com.baoge.utils.MsgResult;

import java.util.List;

public interface UserService {

    User checkUser(String username, String password);

    /*根据用户id查询用户*/
    User getUserById(Integer id);

    /*根据用户名或者性别，状态查询用户*/
    List<User> searchUser(String username, String sex, String status);

    /*查询用户*/
    List<User> listUser();

    /*修改用户*/
    Integer editUser(User user);


    MsgResult updateUserRole(Integer userId, String roleIds);

    /*删除用户*/
    int deleteUser(int id);

    /*批量删除*/
    int deleteUsers(String ids);

    /*增加用户*/
    int addUser(User user);

    /*根据username查询用户*/
    int searchUserByUserName(String username);

    void addUserRole(Integer id, long roleId);


    User checkUserPasswordById(String password, Integer id);

    int editUserpassword(String password, Integer id);

    List<Integer> getRoleidsById(Integer id);
}
