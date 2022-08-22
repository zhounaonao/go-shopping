package com.znn.goodsEs.controller;

import com.znn.commons.domain.AjaxResult;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 周闹闹
 * @version 1.0
 */
@RestController
@RequestMapping("/es")
public class GoodsEsController {

    @Autowired
    private RestHighLevelClient restClient;

    @GetMapping("/list")
    public String list() {
        return "/es/list";
    }


    // Goods表数据批量导入到es中
    @GetMapping("/bulk")
    public AjaxResult bulk() throws IOException {
        BulkRequest request = new BulkRequest("goods");

        restClient.bulk(request, RequestOptions.DEFAULT);
        return AjaxResult.success();
    }

}
