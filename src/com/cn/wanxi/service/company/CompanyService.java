package com.cn.wanxi.service.company;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.company.Company;

/**
 * 业务逻辑层
 */
public interface CompanyService {
//    公司信息
     Company getCompanyModle();

    /**
     * 查询所有
     * @param company
     * @return
     */
    ResultModel getfindAll(Company company);

    /**
     * 以id查询
     * @param company
     * @return
     */
    ResultModel getfindById(Company company);

    /**
     * 更新
     * @param company
     * @return
     */
    ResultModel update(Company company);
}