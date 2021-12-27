package com.cn.wanxi.dao.dress;

import com.cn.wanxi.dao.BaseDaoImpl;
import com.cn.wanxi.model.dress.Message;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName MessageDao.java
 * @Description TODO
 * @createTime 2021年12月17日 11:27:00
 */
public interface MessageDao extends BaseDaoImpl<Message> {
    /**
     * 以value更新
     * @param message
     * @return
     */
    int updateField(Message message);
}
