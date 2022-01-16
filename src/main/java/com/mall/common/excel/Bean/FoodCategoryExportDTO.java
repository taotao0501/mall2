package com.mall.common.excel.Bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.mall.common.excel.ExcelModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/1/11 18:02
 * @Version: V1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategoryExportDTO extends ExcelModel implements Serializable {

    @ExcelIgnore
    String[] foodCategories;

    @ColumnWidth(30)
    @ExcelProperty(value = "食品种类", index = 0)
    private String foodCategory;

    @ColumnWidth(30)
    @ExcelProperty(value = "详情", index = 1)
    private String detail;

    @Override
    public boolean validation(Map<String, List<String>> validationArgs) {
        List<String> foodCategories = validationArgs.getOrDefault("foodCategories", new ArrayList<>());

        boolean result = true;
        //专业值验证
        if(StringUtils.isBlank(this.getFoodCategory()) || !foodCategories.contains(this.getFoodCategory())) {
            result = false;
            this.setFoodCategory(this.getFoodCategory() + "(错误:请使用下拉选的值或使用最新模板)");
        }
        if(StringUtils.isBlank(this.getDetail())) {
            result = false;
            this.setDetail("详情为空");
        }
        return result;
    }

    @Override
    public HashMap<Integer, String> getAnnotationsMap() {
        // 每列的批注
        HashMap<Integer, String> annotationMap = new HashMap<>(7);
        annotationMap.put(0,"必填项，请下载最新的模板，选择下拉列表中生鲜种类");
        annotationMap.put(1, "必填项，请输入生鲜种类对应的详情");
        return annotationMap;
    }

    @Override
    public HashMap<Integer, String[]> getDropDownMap() {
        // 设置下拉框的值
        HashMap<Integer, String[]> dropDownMap = new HashMap<>(2);
        dropDownMap.put(0, foodCategories);
        return dropDownMap;
    }

    @Override
    public List<Integer> getEmphasizeColumns() {
        return Arrays.asList(0,1);
    }

    @Override
    public String toString() {
        return "FoodCategoryExportDTO{}";
    }
}
