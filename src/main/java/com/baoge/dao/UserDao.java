package com.baoge.dao;

import com.baoge.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /*根据用户id查询用户*/
    User getUserById(Integer id);

    /*根据用户名或者性别，状态查询用户*/
    List<User> searchUser(@Param(value = "username") String username, @Param(value = "sex") String sex, @Param(value = "status") String status);

    /*查询用户*/
    List<User> listUser();

    /*修改用户*/
    Integer editUser(User user);

    /*增加用户的角色*/
    void addUserRole(@Param("userId") Integer userId, @Param("roleId") Long roleId);

    /*删除用户下所有的角色*/
    void deleteUserRole(@Param("userId") Integer userId);

    /*根据id删除用户*/
    int deleteUser(int id);

    /*批量删除用户*/
    int deleteUsers(List<Integer> del_ids);

    int addUser(User user);

    /*批量删除用户所对应的角色*/
    void deleteUserRoles(List<Integer> del_ids);

    int searchUserByUserName(String username);

    User checkUserPasswordById(@Param("password") String password, @Param("id")Integer id);

    int editUserpassword(@Param("password") String password, @Param("id")Integer id);

    List<Integer> getRoleidsById(Integer id);

    void deleteCardImageByUserId(int id);

    void deleteResumeByUserId(int id);

    void deleteJobByUserId(int id);

    void deleteApplicationByUserId(int id);
}
