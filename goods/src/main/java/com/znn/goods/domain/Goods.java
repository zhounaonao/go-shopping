package com.znn.goods.domain;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.znn.commons.domain.BaseDomain;
import lombok.Data;

/**
 * @author 周闹闹
 * @version 1.0
 */
@Data
@ExcelIgnoreUnannotated
public class Goods extends BaseDomain {
    @ExcelProperty("商品编号")
    private Long goodsId;
    @ExcelProperty("商品名称")
    private String goodsName;
    @ExcelProperty("商品价格")
    private Double price;
    @ExcelProperty("商品规格")
    private String specification;
    @ExcelProperty("类别编号")
    private Long typeId;
    @ExcelProperty("状态")
    private String status;

}
