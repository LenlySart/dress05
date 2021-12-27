package com.cn.wanxi.service.dress_impl;

import com.cn.wanxi.dao.dress.MessageDao;
import com.cn.wanxi.dao.impl.MessageDaoImpl;
import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.Message;
import com.cn.wanxi.service.dress.MessageService;

import java.util.List;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName MessageServiceImpl.java
 * @Description TODO
 * @createTime 2021年12月17日 11:05:00
 */
public class MessageServiceImpl implements MessageService {
    /**
     * 添加
     * @param message
     * @return
     */
    @Override
    public ResultModel add(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        int count = messageDao.add(message);
        return ResultModel.getModel(count);
    }

    @Override
    public ResultModel getCount(Message message) {
        return null;
    }

    /**
     * 以id查询
     * @param message
     * @return
     */
    @Override
    public ResultModel findById(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        Message count = messageDao.findById(message);
        return ResultModel.getModel(count);
    }

    /**
     * 更新
     * @param message
     * @return
     */
    @Override
    public ResultModel update(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        int count = messageDao.update(message);
        return ResultModel.getModel(count);
    }

    /**
     * 删除
     * @param message
     * @return
     */
    @Override
    public ResultModel deleteId(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        int id =message.getId();
        Message in = new Message();
        in.setStatus(1);
        in.setId(id);
        int count = messageDao.deleteId(in);
        return ResultModel.getModel(count);
    }

    /**
     * 查询没被删除的总条数
     * @param message
     * @return
     */
    @Override
    public ResultModel getStatusCount(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        int count = messageDao.getCountStatus(message);
        return ResultModel.getModel(count);
    }

    /**
     * 状态
     * @param message
     * @return
     */
    @Override
    public ResultModel getState(Message message) {
        return null;
    }

    /**
     * 查询所有
     * @param message
     * @return
     */
    @Override
    public ResultModel getfindAll(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        List<Message> list = messageDao.findAll(message);
        return ResultModel.getModel(list);
    }

    /**
     * 以名字更新
     * @param message
     * @return
     */
    @Override
    public ResultModel updateField(Message message) {
        MessageDao messageDao = new MessageDaoImpl();
        int result = messageDao.updateField(message);
        return ResultModel.getModel(result == 1 ? "success" : "error");
    }
}
