package com.IOC.annotation.dao;


import org.springframework.stereotype.Repository;

/**
 * @author liudw
 * @date 2022/12/12 11:23
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("UserDao add ...");
    }
}