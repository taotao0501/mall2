package com.mall.service;

import com.mall.common.excel.Bean.FoodCategoryVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description:
 * @Author: Bentao She
 * @Date: 2022/1/11 17:50
 * @Version: V1.0
 **/

public interface TestService {

    void getTemplate(HttpServletResponse response);

   List<FoodCategoryVO> importTemplate(MultipartFile file);
}
