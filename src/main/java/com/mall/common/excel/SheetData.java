package com.mall.common.excel;

/**
 * @author wangzhipeng
 * @date 2021/12/29
 */

import lombok.Data;

import java.util.List;

/**
 * @author jreyson
 */
@Data
public class SheetData<T extends ExcelModel> {

    private String sheetName;
    private int sheetNo;
    private Class<?> clazz;
    private List<?> data;
    private T t;

    public SheetData() {
    }

    public SheetData(String sheetName, int sheetNo, Class<?> clazz, List<?> data, T t) {
        this.sheetName = sheetName;
        this.sheetNo = sheetNo;
        this.clazz = clazz;
        this.data = data;
        this.t = t;
    }

}

