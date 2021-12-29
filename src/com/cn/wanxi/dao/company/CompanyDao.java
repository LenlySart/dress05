package com.cn.wanxi.dao.company;

import com.cn.wanxi.dao.BaseDaoImpl;
import com.cn.wanxi.model.company.Company;

/**
 * @title 公司信息
 * @description
 * @author admin  Dshzs月
 * @updateTime 2021/11/22 16:01
 * @throws
 */
public interface CompanyDao extends BaseDaoImpl<Company>{
    /**
     * 公司信息展示
     * @return
     */
    Company getCompanyList();
}
