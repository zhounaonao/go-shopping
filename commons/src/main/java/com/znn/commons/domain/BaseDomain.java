package com.znn.commons.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author 周闹闹
 * @version 1.0
 */
@Data
public class BaseDomain {
    private Date updateTime;
    private Long updateUserId;
    private Date createTime;
    private Long createUserId;
    @ExcelProperty("备注")
    private String remark;
}
