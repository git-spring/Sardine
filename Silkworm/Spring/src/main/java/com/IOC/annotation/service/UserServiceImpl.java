package com.IOC.annotation.service;

import com.IOC.annotation.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liudw
 * @date 2022/12/12 11:06
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired  // 自动注入
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service ...");
        userDao.add();
    }


}