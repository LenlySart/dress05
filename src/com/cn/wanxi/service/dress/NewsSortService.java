package com.cn.wanxi.service.dress;

import com.cn.wanxi.enums.ResultModel;
import com.cn.wanxi.model.news.NewsSort;

/**
 * @author Dshzs月
 * @version 1.0.0
 * @ClassName NewsSortService.java
 * @Description TODO
 * @createTime 2021年12月22日 14:03:00
 */
public interface NewsSortService {
    /**
     * 查询所有
     * @param newsSort
     * @return
     */
    ResultModel getFindAll(NewsSort newsSort);
}
