package com.cn.wanxi.service.dress;

import com.cn.wanxi.model.company.CompanyStore;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyStoreService.java
 * @Description TODO
 * @createTime 2021年12月16日 19:51:00
 */
public interface CompanyStoreService {
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
