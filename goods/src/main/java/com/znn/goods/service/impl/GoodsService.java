package com.znn.goods.service.impl;

import com.znn.commons.service.BaseService;
import com.znn.goods.domain.Goods;
import com.znn.goods.mapper.GoodsMapper;
import com.znn.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 周闹闹
 * @version 1.0
 */
@Service
public class GoodsService implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> list(Integer pageNum, Integer pageSize, String search) {
        Integer start = pageSize * (pageNum - 1);
        return goodsMapper.list(start, pageSize, search);
    }

    @Override
    public boolean add(Goods goods) {
        return goodsMapper.add(goods) > 0;
    }

    @Override
    public boolean update(Goods goods) {
        return goodsMapper.update(goods) > 0;
    }

    @Override
    public boolean delete(Long goodsId) {
        return goodsMapper.delete(goodsId) > 0;
    }

    @Override
    public boolean saveData(List<Goods> list) {
        return goodsMapper.addList(list) > 0;
    }

    @Override
    public int total() {
        return goodsMapper.total();
    }
}
