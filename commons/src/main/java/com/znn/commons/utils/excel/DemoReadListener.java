package com.znn.commons.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.znn.commons.service.BaseService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 周闹闹
 * @version 1.0
 */
@Slf4j
@Data
public class DemoReadListener<T> implements ReadListener<T> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;

    private int total = 0;

    private List<T> list;

    // 保存数据库的服务
    private BaseService<T> service;

    /**
     * 不保存数据库方案
     */
    public DemoReadListener() {
        list = new ArrayList<>();
    }

    /**
     * 保存数据库的方案，需要实现BaseService并且传入相应的保存数据库service
     * 当内存中数据量超过BATCH_COUNT之后就执行保存入数据库
     *
     * @param service
     */
    public DemoReadListener(BaseService<T> service) {
        this.service = service;
        list = new ArrayList<>(BATCH_COUNT);
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param t
     * @param analysisContext
     */
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
//        log.info("解析到一条数据:{}", t);
        list.add(t);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (service != null && list.size() >= BATCH_COUNT) {
            saveData();
            this.total = total + BATCH_COUNT;
            // 清空list
            list = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (service != null) {
            saveData();
        }
        this.total = total + list.size();
        log.info("共计" + total + "条数据解析完毕");
    }

    private void saveData() {
        // 判断参数是否是调用者的子类（父类）BaseService.class.isAssignableFrom(（子类）service)
        // 如果是基础service的子类，则调用保存方法
        service.saveData(list);
    }
}
