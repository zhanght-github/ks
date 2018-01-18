package com.xingong.myks.services;

import com.xingong.myks.dao.UserDao;
import com.xingong.myks.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *用户逻辑类
 */

@Service
@Transactional
public class UserService {

    //自动注入
    @Autowired
    UserDao userDao;

    /**
     * 得到单个用户信息，用于用户登录或者app用户页展示详情
     * @param usernmae
     * @return
     */
    public UserEntity getUserInfo(String usernmae){
        return userDao.getUserInfo(usernmae);
    }

    /**
     * 得到 所有员工的信息
     * @return
     */
    public List<UserEntity> getAllUser(){
        return userDao.getAllUser();
    }

    /**
     * 设置员工角色
     * @param userid
     * @param role
     */
    public void setUserRole(int userid,String role){
        UserEntity userEntity = userDao.getUserById(userid);
        userEntity.setRole(role);
        userDao.save(userEntity);
    }
    /**
     * 修改密码
     * @param password
     * @param username
     */
    public void modifyPassword(String password,String username){
        userDao.modifyPassword(password,username);
    }

}
