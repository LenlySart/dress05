package com.cn.wanxi.dao.company;

import com.cn.wanxi.model.company.CompanyStore;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyStoreDao.java
 * @Description TODO
 * @createTime 2021年12月16日 19:53:00
 */
public interface CompanyStoreDao {
    /**
     * 查询所有
     * @return
     */
    List<CompanyStore> getCompanyStore();

    /**
     * 查询门店图片
     * @return
     */
    List<CompanyStore> getCompanyStoreList();
}
