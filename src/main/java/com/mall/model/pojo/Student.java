package com.mall.model.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2021/12/22 18:11
 * @Version: V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class Student {

    @ExcelProperty(value = "学号", index = 0)
    private String id;

    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    @ExcelProperty(value = "性别", index = 2)
    private String gender;

    @ExcelProperty(value = "生日", index = 3)
    private Date birthday;

}
