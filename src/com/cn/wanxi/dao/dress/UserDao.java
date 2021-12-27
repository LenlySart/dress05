package com.cn.wanxi.dao.dress;

import com.cn.wanxi.dao.BaseDaoImpl;
import com.cn.wanxi.model.User;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName UserDao.java
 * @Description TODO
 * @createTime 2021年11月30日 19:25:00
 */
public interface UserDao extends BaseDaoImpl<User> {
    /**
     * 登录查询判断
     * @param user
     * @return
     */
    User getLogin(User user);


    /**
     * 查询密码
     * @param user
     * @return
     */
    User getSelectPass(User user);

    /**
     * 修改新密码
     * @param user
     * @return
     */
    int updatePasswod(User user);
    /**
     * 查询
     * @param
     * @return
     */
    List<User> selcet(User user);


}
