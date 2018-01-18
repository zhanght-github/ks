package com.xingong.myks.dao;

import com.xingong.myks.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户持久化类
 */
public interface UserDao extends JpaRepository<UserEntity,Integer>{


    @Query("SELECT t FROM UserEntity t where t.username = ?1")
    public UserEntity getUserInfo(String username);

    @Query("select t from UserEntity  t where t.userid = ?1")
    public UserEntity getUserById(int userid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update user t set t.password = ?1 where t.username = ?2",nativeQuery = true)
    public void modifyPassword(String password,String username);

    @Query("select t from UserEntity t ")
    public List<UserEntity> getAllUser();


}
