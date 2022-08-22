package com.znn.goods.service;

import com.znn.commons.service.BaseService;
import com.znn.goods.domain.Goods;

import java.util.List;

/**
 * @author 周闹闹
 * @version 1.0
 */
public interface IGoodsService extends BaseService<Goods> {

    List<Goods> list(Integer pageNum, Integer pageSize, String search);

    boolean add(Goods goods);

    boolean update(Goods goods);

    boolean delete(Long goodsId);
}
