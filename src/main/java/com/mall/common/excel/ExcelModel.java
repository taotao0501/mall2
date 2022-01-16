package com.mall.common.excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/1/11 14:30
 * @Version: V1.0
 **/

public abstract class  ExcelModel {
    public ExcelModel() {
    }

    public abstract boolean validation(Map<String, List<String>> var1);

    public abstract HashMap<Integer, String> getAnnotationsMap();

    public abstract HashMap<Integer, String[]> getDropDownMap();

    public abstract List<Integer> getEmphasizeColumns();
}
