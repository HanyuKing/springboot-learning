package com.test.service;

import com.test.dao.UserDao;
import com.test.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: laizc
 * @date: created in 2022/1/13
 * @desc:
 **/
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Transactional
    public void insertName(String name) {
        User user = new User();
        user.setAge(28);
        user.setName(name);
        user.setSubmitTime(new Date());
        userDao.insert(user);
        System.out.println(user);
    }

    /**
     * 走索引表示行锁，该条数据会被锁住，不能修改。
     * 其他数据可以修改
     * @param id
     */
    //@Transactional
    public void updateById(Long id) {
        User user = new User();
        user.setId(id);
        user.setAge(23);
        user.setSubmitTime(new Date());
        userDao.updateByPrimaryKeySelective(user);
        System.out.println("update time: " + System.currentTimeMillis() + ": " + user);
    }

    /**
     * 不走索引，走表锁。整个表的数据都不能修改
     * @param name
     */
    @Transactional
    public void updateByName(String name) {
        User user = new User();
        user.setName(name);
        user.setAge(11);
        userDao.updateByNameSelective(user);
        System.out.println(user.getAge());
    }


    /**
     * 读锁是不会影响读取数据，但是表锁都不能修改
     * @param id
     */
    @Transactional
    public void selectById(Long id) {
        User user = userDao.selectByPrimary(id);
        System.out.println(user);
    }

    public void updateAgeBatch(List<Long> idList, int age) {
        System.out.println("updateAgeBatch start: " + System.currentTimeMillis());
        userDao.updateAgeBatch(idList, age);
        System.out.println("updateAgeBatch end: " + System.currentTimeMillis());
    }

    @Transactional
    public void updateIdBetween(Long minId, Long maxId) throws InterruptedException {
        System.out.println("updateIdBetween time: " + System.currentTimeMillis());
        userDao.updateIdBetween(minId, maxId);
        System.out.println("updateIdBetween time: " + System.currentTimeMillis());
        Thread.currentThread().join();
    }
}
