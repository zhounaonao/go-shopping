package com.znn.commons.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.znn.commons.service.BaseService;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;


/**
 * @author 周闹闹
 * @version 1.0
 * 自制的Excel工具
 */
public class ExcelUtils {

    public static <T> DemoReadListener<T> readExcel(String filePath, Class<T> clazz) {
        return readExcel(filePath, clazz, null);
    }

    public static <T> DemoReadListener<T> readExcel(InputStream inputStream, Class<T> clazz) {
        return readExcel(inputStream, clazz, null);
    }

    public static <T> DemoReadListener<T> readExcel(String filePath, Class<T> clazz, BaseService<T> service) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return readExcel(fileInputStream, clazz, service);
    }

    public static <T> DemoReadListener<T> readExcel(InputStream inputStream, Class<T> clazz, BaseService<T> service) {

        DemoReadListener<T> readListener;
        if (service != null) {
            readListener = new DemoReadListener<>(service);
        } else {
            readListener = new DemoReadListener<>();
        }

        EasyExcel.read(inputStream,
                clazz,
                readListener).doReadAll();
        return readListener;
    }

    public static String getPath() {
        return System.getProperty("user.dir");
    }

}
