package com.mall.model.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/1/11 17:52
 * @Version: V1.0
 **/

@Repository
public interface TestMapper {

    String[] getFoodCategoryList();
}
