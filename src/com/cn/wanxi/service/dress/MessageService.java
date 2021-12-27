package com.cn.wanxi.service.dress;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.dress.Message;
import com.cn.wanxi.service.BaseService;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName MessageService.java
 * @Description TODO
 * @createTime 2021年12月17日 11:05:00
 */
public interface MessageService extends BaseService<Message> {

    /**
     * 修改
     * @param message
     * @return
     */
    ResultModel updateField(Message message);
}
