package com.znn.goods.mapper;

import com.znn.goods.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 周闹闹
 * @version 1.0
 */
@Mapper
public interface GoodsMapper {

    List<Goods> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("search") String search);

    int add(Goods goods);

    int update(Goods goods);

    int delete(Long goodsId);

    int addList(List<Goods> list);

    int total();
}
