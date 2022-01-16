package com.mall.common.excel.Bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 导入Bean类
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/1/12 15:15
 * @Version: V1.0
 **/
@ApiModel("生鲜种类详情")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCategoryVO {

    @ApiModelProperty("生鲜种类")
    private String foodCategory;

   @ApiModelProperty("生鲜介绍详情")
    private String detail;

}
