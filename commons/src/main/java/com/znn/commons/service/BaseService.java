package com.znn.commons.service;

import java.util.List;

/**
 * @author 周闹闹
 * @version 1.0
 */
public interface BaseService<T> {

    /**
     * 保存一个数组到数据库
     * @param list
     * @return
     */
    boolean saveData(List<T> list);

    int total();

}
