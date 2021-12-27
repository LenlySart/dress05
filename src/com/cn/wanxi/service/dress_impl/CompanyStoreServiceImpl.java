package com.cn.wanxi.service.dress_impl;

import com.cn.wanxi.dao.dress.CompanyStoreDao;
import com.cn.wanxi.dao.impl.CompanyStoreDaoImpl;
import com.cn.wanxi.model.company.CompanyStore;
import com.cn.wanxi.service.dress.CompanyStoreService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyStoreServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月16日 19:51:00
 */
public class CompanyStoreServiceImpl implements CompanyStoreService {
    /**
     * 查询所有
     * @return
     */
    @Override
    public List<CompanyStore> getCompanyStore() {
        CompanyStoreDao companyStoreDao = new CompanyStoreDaoImpl();
        return companyStoreDao.getCompanyStore();
    }

    /**
     * 查询门店图片
     * @return
     */
    @Override
    public List<CompanyStore> getCompanyStoreList() {
        CompanyStoreDao companyStoreDao = new CompanyStoreDaoImpl();
        return companyStoreDao.getCompanyStoreList();
    }
}
