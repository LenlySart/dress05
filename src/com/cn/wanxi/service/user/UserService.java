package com.cn.wanxi.service.user;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.User;
import com.cn.wanxi.service.BaseService;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName UserService.java
 * @Description TODO
 * @createTime 2021年11月30日 17:37:00
 */
public interface UserService extends BaseService<User> {
    //    查询列表
    ResultModel getAll(User user);
    //    登录方法
    ResultModel login(User user);
    //    修改密码
    ResultModel updatePassword(User user);
}
