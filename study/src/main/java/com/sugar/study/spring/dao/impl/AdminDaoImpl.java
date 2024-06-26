package com.sugar.study.spring.dao.impl;

import com.sugar.study.spring.dao.AdminDao;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/19 10:40 AM
 * @Version 1.0
 */
@Component
public class AdminDaoImpl implements AdminDao {

    public boolean login() {
        System.out.println("[AdminDao]  login ");
        return true;
    }
}
