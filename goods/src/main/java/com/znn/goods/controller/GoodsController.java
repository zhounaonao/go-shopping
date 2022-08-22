package com.znn.goods.controller;

import ch.qos.logback.core.util.FileUtil;
import com.znn.commons.domain.AjaxResult;
import com.znn.commons.service.BaseService;
import com.znn.commons.utils.excel.DemoReadListener;
import com.znn.commons.utils.excel.ExcelUtils;
import com.znn.goods.domain.Goods;
import com.znn.goods.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author 周闹闹
 * @version 1.0
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    // 查询分页
    @GetMapping("/list")
    public AjaxResult list(@RequestParam("pageNum") Integer pageNum,
                           @RequestParam("pageSize") Integer pageSize,
                           @RequestParam(value = "search", required = false) String search) {
        AjaxResult ajaxResult = AjaxResult.success(goodsService.list(pageNum, pageSize, search));
        ajaxResult.put("total", goodsService.total());
        return ajaxResult;
    }

    // 新增
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Goods goods) {
        if (goodsService.add(goods)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    // 修改
    @PutMapping("/update")
    public AjaxResult update(@RequestBody Goods goods) {
        if (goodsService.update(goods)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    // 删除
    @DeleteMapping("/delete")
    public AjaxResult delete(@RequestParam Long goodsId) {
        if (goodsService.delete(goodsId)) {
            return AjaxResult.success();
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 上传excel到数据库中
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/excel")
    public AjaxResult excel(@RequestParam("file") MultipartFile file) throws IOException {
        DemoReadListener<Goods> goodsDemoReadListener =
                ExcelUtils.readExcel(file.getInputStream(), Goods.class, goodsService);
        return AjaxResult.success();
    }

}
