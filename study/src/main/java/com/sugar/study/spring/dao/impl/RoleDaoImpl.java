package com.sugar.study.spring.dao.impl;

import com.sugar.study.spring.dao.RoleDao;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author sugar
 * @Date 2020/8/19 10:42 AM
 * @Version 1.0
 */
@Component
public class RoleDaoImpl implements RoleDao {
    public boolean findAll() {
        System.out.println("[RoleDao] findAll()");
        return true;
    }
}
