package com.baoge.service.impl;

import com.baoge.dao.UserDao;
import com.baoge.entity.User;
import com.baoge.service.UserService;
import com.baoge.utils.MD5Utils;
import com.baoge.utils.MsgResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.getMD5(password));
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> searchUser(String username, String sex, String status) {
        return userDao.searchUser(username,sex,status);
    }

    @Override
    public List<User> listUser() {
        return userDao.listUser();
    }

    @Override
    public Integer editUser(User user) {
        if(user.getPassword()==null || user.getPassword()==""){
            return userDao.editUser(user);
        }else {
            user.setPassword(MD5Utils.getMD5(user.getPassword()));
            return userDao.editUser(user);
        }
    }

    @Override
    public MsgResult updateUserRole(Integer userId, String roleIds) {

        // 删除用户下所有的角色
        userDao.deleteUserRole(userId);
        String[] rIds = roleIds.split(",");

        for (String rId : rIds) {
            /*System.out.println(userId);*/
            userDao.addUserRole(userId, Long.parseLong(rId));
        }
        return MsgResult.build(1, "");
    }

    @Override
    public int deleteUser(int id) {
        userDao.deleteUserRole(id);
        userDao.deleteCardImageByUserId(id);
        userDao.deleteResumeByUserId(id);
        userDao.deleteJobByUserId(id);
        userDao.deleteApplicationByUserId(id);
        return userDao.deleteUser(id);
    }

    @Override
    public int deleteUsers(String ids) {
        List<Integer>del_ids=new ArrayList<>();
        String[] str_ids=ids.split("-");
        for (String string:str_ids){
            del_ids.add(Integer.parseInt(string));
        }
        /*System.out.println("del:"+del_ids);*/
        if (del_ids.contains(1)){
            return 0;
        }else {
            userDao.deleteUserRoles(del_ids);
            return userDao.deleteUsers(del_ids);
        }
    }

    @Override
    public int addUser(User user) {
        user.setPassword(MD5Utils.getMD5(user.getPassword()));
        return userDao.addUser(user);
    }

    @Override
    public int searchUserByUserName(String username) {
        return userDao.searchUserByUserName(username);
    }

    @Override
    public void addUserRole(Integer userId, long roleId) {
       userDao.addUserRole(userId,roleId);
    }

    @Override
    public User checkUserPasswordById(String password, Integer id) {
        password=MD5Utils.getMD5(password);
        return userDao.checkUserPasswordById(password,id);
    }

    @Override
    public int editUserpassword(String password, Integer id) {
        password=MD5Utils.getMD5(password);
        return userDao.editUserpassword(password,id);
    }

    @Override
    public List<Integer> getRoleidsById(Integer id) {
        return userDao.getRoleidsById(id);
    }

}

