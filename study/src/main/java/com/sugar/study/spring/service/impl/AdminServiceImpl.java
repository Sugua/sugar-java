package com.sugar.study.spring.service.impl;

import com.sugar.study.spring.dao.AdminDao;
import com.sugar.study.spring.dao.RoleDao;
import com.sugar.study.spring.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/19 10:44 AM
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Resource
    private RoleDao roleDao;


    public AdminServiceImpl setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
        return this;
    }

    public boolean login() {
        this.adminDao.login();
        this.roleDao.findAll();
        return true;
    }
}
