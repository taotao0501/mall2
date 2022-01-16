package com.mall.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/1/11 14:52
 * @Version: V1.0
 **/

public class BaseConstant {

    private BaseConstant() {}
    /**
     * Excel文件格式
     */
    public static final List<String> EXCEL_FORMAT = Arrays.asList(".xlsx", ".xls");

    /**
     * 错误信息规则
     */
    public static final String EXCEL_ERROR = "^(.*)(\\(错误:)(.*)(\\))$";
}
