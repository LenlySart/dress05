package com.cn.wanxi.service.impl;

import com.cn.wanxi.dao.dress.UserDao;
import com.cn.wanxi.dao.impl.UserDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.User;
import com.cn.wanxi.service.user.UserService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2021年11月30日 17:38:00
 */
public class UserServiceImpl implements UserService {

    /**
     * 登录验证
     * @param user
     * @return
     */
    @Override
    public ResultModel login(User user) {
    //创建一个result对象
//        获取code验证码
        String code =user.getCode();
//        判断验证码并把验证码全部转成大写【toUpperCase()】来判断是否相同
        if (!code.toUpperCase().equals(user.getSessionCode())){
//        error-code如果验证码正确，需要查询数据库，根据用户名和密码判断是否正确
            return ResultModel.noLogin();
        }
//            创建dao层实现impl接口A
        UserDao userDao = new UserDaoImpl();
//            定义一个model去获取dao层创来的数据
        User model = userDao.getLogin(user);
//            定义一个布尔类型的数据来获取user并判断是否为空
        Boolean isHave = model != null;
//            判断用户名和密码是否正确
        if (!isHave){
            return ResultModel.notLogin();
        }
        //不为空且那么表示成功
        return ResultModel.inLogin();
    }


    /**
     * 用户列表查询
     * @param user
     * @return
     */
    @Override
    public ResultModel getAll(User user) {

        UserDao userDao = new UserDaoImpl();
//        获取dao层查询的数据
        //        查询总条数
        int count = userDao.getCount(user);
        List<User> list = userDao.findAll(user);
        return  ResultModel.getModel(count,list);
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public ResultModel add(User user) {
        String msg="";
        //        创建model对象
        UserDao userDao = new UserDaoImpl();
        //        获取dao层查询的数据
        int count = userDao.add(user);
        if (count>0){
            msg="success";
            return ResultModel.getModel(msg);
        }else {
            //不为空且那么表示成功
            msg = "error";
            return ResultModel.getModel(msg);
        }
    }

    /**
     * 分页查询
     * @return
     */
    @Override
    public ResultModel getCount(User user) {
//        创建dao层调用getCount方法
        UserDao userDao = new UserDaoImpl();
        int count = userDao.getCount(user);
//       返回信息给前端
        return ResultModel.getModel(count);
    }

    /**
     * 以id查询
     * @param user
     * @return
     */
    @Override
    public ResultModel findById(User user) {
//        创建dao层调用findById方法
        UserDao userDao = new UserDaoImpl();
        User model = userDao.findById(user);
//       返回信息给前端
        return ResultModel.getModel(model);

    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public ResultModel update(User user) {
        //        创建model对象
        UserDao userDao = new UserDaoImpl();
        //        获取dao层查询的数据
        int count = userDao.update(user);
        return ResultModel.getModel(count);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @Override
    public ResultModel updatePassword(User user) {
//        查出数据库中的密码与输入的旧密码对比
        //        创建model对象
        UserDao userDao = new UserDaoImpl();
        //        获取dao层查询的数据
        User model = userDao.getSelectPass(user);
        if (!model.getPassword().equals(user.getPassword())){
            return ResultModel.notLogin();
        }
        int count = userDao.updatePasswod(user);

        return ResultModel.getModel(count);
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    @Override
    public ResultModel deleteId(User user) {
        //        创建model对象
        UserDao userDao = new UserDaoImpl();
        //        获取dao层查询的数据
        User model = userDao.selectDele(user);
        if (model.getStatus()!=0){
            return ResultModel.notLogin();
        }
        int id = model.getId();
        User in = new User();
        in.setStatus(1);
        in.setId(id);
        int count = userDao.deleteId(in);
        return ResultModel.getModel(count);
    }

    /**
     * 查询没有被删除的页数
     * @param user
     * @return
     */
    @Override
    public ResultModel getStatusCount(User user) {
        //        创建dao层调用getCount方法
        UserDao userDao = new UserDaoImpl();
        int count = userDao.getCountStatus(user);
//       返回信息给前端
        return ResultModel.getModel(count);
    }

    /**
     * 用户状态
     * @param user
     * @return
     */
    @Override
    public ResultModel getState(User user) {
        //        创建dao层调用findById方法
        UserDao userDao = new UserDaoImpl();
        User model = userDao.findById(user);
        int state = model.getState();
        int id = model.getId();
        if (state==1){
            User in = new User();
            in.setState(2);
            in.setId(id);
            int count = userDao.updateState(in);
            return ResultModel.getModel(count);
        }else if (state==2) {
            User in = new User();
            in.setState(1);
            in.setId(id);
            int count = userDao.updateState(in);
            return ResultModel.getModel(count);
        }
        return null;
    }

    /**
     * 模糊查询
     * @param user
     * @return
     */
    @Override
    public ResultModel getfindAll(User user) {
        UserDao userDao = new UserDaoImpl();
//        获取dao层查询的数据
        //        查询总条数
        int count = userDao.getCountStatus(user);
        List<User> model = userDao.selcet(user);
        return  ResultModel.getModel(count,model);
    }
}
