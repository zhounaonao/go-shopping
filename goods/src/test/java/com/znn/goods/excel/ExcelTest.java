package com.znn.goods.excel;

import com.znn.commons.utils.excel.DemoReadListener;
import com.znn.commons.utils.excel.ExcelUtils;
import com.znn.goods.domain.Goods;
import com.znn.goods.mapper.GoodsMapper;
import com.znn.goods.service.impl.GoodsService;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

/**
 * @author 周闹闹
 * @version 1.0
 */
@SpringBootTest
public class ExcelTest {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsService goodsService;

    @Test
    public void readExcelTest() {
        System.out.println(ExcelUtils.getPath());
        DemoReadListener<Goods> goodsDemoReadListener = ExcelUtils.readExcel("D:\\Program Files\\OneDrive\\Project\\go-shopping\\go-shopping\\excel\\goods.xlsx", Goods.class, goodsService);
    }

    @Test
    public void getPath() throws IOException {
        System.out.println(new File("").getCanonicalPath());
    }

}
