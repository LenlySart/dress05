package com.cn.wanxi.service.company.impl;

import com.cn.wanxi.dao.company.CompanyDao;
import com.cn.wanxi.dao.company.impl.CompanyDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.company.Company;
import com.cn.wanxi.service.company.CompanyService;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName CompanyServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月26日 16:36:00
 */
public class CompanyServiceImpl implements CompanyService {


    @Override
    public Company getCompanyModle() {
        Company company = new Company();
//        创建一个redis对象
        Jedis jedis = new Jedis();
        jedis.select(0);
        boolean isHave = jedis.exists("companyName");
        if (isHave) {
            company.setAddress(jedis.get("companyAddress"));
            company.setEmail(jedis.get("companyEmail"));
            company.setPhone(jedis.get("companyPhone"));
            company.setQq(jedis.get("companyQQ"));
            company.setName(jedis.get("companyName"));
            company.setProFile(jedis.get("companyProFile"));
            company.setImgHref(jedis.get("companyImgHref"));
//            System.out.println("redis");

        }else {
        CompanyDao companyDao = new CompanyDaoImpl();
        company = companyDao.getCompanyList();
        jedis.set("companyName", company.getName());
        jedis.set("companyEmail", company.getEmail());
        jedis.set("companyPhone", company.getPhone());
        jedis.set("companyQQ", company.getQq());
        jedis.set("companyAddress", company.getAddress());
        jedis.set("companyProFile", company.getProFile());
        jedis.set("companyImgHref", company.getImgHref());
//            System.out.println("mysql");
        }
        return company;
    }


    /**
     * 查询所有
     *
     * @param company
     * @return
     */
    @Override
    public ResultModel getfindAll(Company company) {
        CompanyDao companyDao = new CompanyDaoImpl();
        List<Company> list = companyDao.findAll(company);
        return ResultModel.getModel(list);
    }

    /**
     * 以id查询
     *
     * @param company
     * @return
     */
    @Override
    public ResultModel getfindById(Company company) {
        CompanyDao companyDao = new CompanyDaoImpl();
        Company model = companyDao.findById(company);
        return ResultModel.getModel(model);
    }

    /**
     * 更新
     *
     * @param company
     * @return
     */
    @Override
    public ResultModel update(Company company) {
        CompanyDao companyDao = new CompanyDaoImpl();
        int count = companyDao.update(company);
        if(count>0){
            Jedis jedis  = new Jedis();
            jedis.select(0);
            jedis.del("companyName");
        }
        return ResultModel.getModel(count);
    }


}
