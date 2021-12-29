package com.cn.wanxi.service.company.impl;

import com.cn.wanxi.dao.company.CompanyStylistDao;
import com.cn.wanxi.dao.company.impl.CompanyStylistDaoImpl;
import com.cn.wanxi.model.company.CompanyStylist;
import com.cn.wanxi.service.company.CompanyStylistService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyStylistServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月16日 19:05:00
 */
public class CompanyStylistServiceImpl implements CompanyStylistService {
    @Override
    public List<CompanyStylist> getCompanyStylist() {
        CompanyStylistDao companyStylistDao = new CompanyStylistDaoImpl();
        return companyStylistDao.getCompanyStylist();
    }
}
