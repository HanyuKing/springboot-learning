package com.test.dao;

import com.test.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: laizc
 * @date: created in 2022/1/13
 * @desc:
 **/
public interface UserDao {

    User selectByPrimary(Long id);

    void insert(User user);

    void updateByPrimaryKeySelective(User user);

    void updateByNameSelective(User user);

    void updateAgeBatch(@Param("idList") List<Long> idList, @Param("age") int age);

    void updateByPrimaryKey(User user);

    void updateIdBetween(@Param("min") Long min, @Param("max") Long max);

}
