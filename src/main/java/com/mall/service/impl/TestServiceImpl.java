package com.mall.service.impl;

import com.mall.common.NrosPreconditions;
import com.mall.common.constant.BaseConstant;
import com.mall.common.excel.Bean.FoodCategoryExportDTO;
import com.mall.common.excel.Bean.FoodCategoryVO;
import com.mall.common.excel.ExcelModel;
import com.mall.common.excel.SheetData;
import com.mall.common.utils.EasyExcelListener;
import com.mall.common.utils.EasyExcelUtil;
import com.mall.exception.MyExceptionHandler;
import com.mall.model.dao.TestMapper;
import com.mall.service.TestService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2022/1/11 17:51
 * @Version: V1.0
 **/

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    /**
     * 导入模板数据到数据库
     * @param file
     * @return
     */
    @Override
    public List<FoodCategoryVO> importTemplate(MultipartFile file) {
        ArrayList<FoodCategoryVO> foodCategoryVOArrayList = new ArrayList<>();

        InputStream inputStream = getInputStream(file);
        EasyExcelUtil<FoodCategoryExportDTO> easyExcelUtil = new EasyExcelUtil<>();

        Map<String, List<String>> args = new HashMap<>();
        String[] foodCategories = testMapper.getFoodCategoryList();
        List<String> foodCategoryList = Arrays.asList(foodCategories);
        args.put("foodCategories", foodCategoryList);
        EasyExcelListener<FoodCategoryExportDTO> listener = easyExcelUtil.readExcelWithModel(
                inputStream, FoodCategoryExportDTO.class, args);
        if(listener.getError().size() > 0) {
            NrosPreconditions.isNull(args, "BUSI-EXCEPTION-FILEIMPORT-1001");
            //MyExceptionHandler.publish("BUSI-EXCEPTION-FILEIMPORT-1001");
        } else if (listener.getData().size() > 0) {
            List<FoodCategoryExportDTO> data = listener.getData();
            for (int i = 0; i < data.size(); i++) {
                FoodCategoryExportDTO dto = data.get(i);
                String foodCategory = dto.getFoodCategory();
                if(!foodCategoryList.contains(foodCategory)) {
                    NrosPreconditions.isNull(args, "BUSI-EXCEPTION-FILEIMPORT-1002");
                }
                FoodCategoryVO foodCategoryVO = new FoodCategoryVO();
                BeanUtils.copyProperties(dto, foodCategoryVO);
                foodCategoryVOArrayList.add(foodCategoryVO);
            }
        } else {
            NrosPreconditions.isNull(args, "BUSI-EXCEPTION-FILEIMPORT-1003");
        }
        return foodCategoryVOArrayList;
    }

    /**
     * 下载导入模板
     *
     * @param response
     */

    @Override
    public void getTemplate(HttpServletResponse response) {
        EasyExcelUtil<ExcelModel> excelUtil = new EasyExcelUtil<>();
        String filename = "生鲜种类导入模板";
        response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes(StandardCharsets.UTF_8),
                StandardCharsets.ISO_8859_1) + ".xlsx");
        String[] foodCategoryList = testMapper.getFoodCategoryList();
        FoodCategoryExportDTO dto = new FoodCategoryExportDTO();
        dto.setFoodCategories(foodCategoryList);
        //创建第一个表
        SheetData<FoodCategoryExportDTO> sheet = new SheetData<>("生鲜种类导入模板", 0, FoodCategoryExportDTO.class,
                null, dto);
        //excel中sheet页添加
        List<SheetData<?>> sheets = new ArrayList<>();
        sheets.add(sheet);
        try {
            excelUtil.createDownloadImportExcel(response.getOutputStream(), sheets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InputStream getInputStream(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (StringUtils.isBlank(fileName)) {
            MyExceptionHandler.publish("BUSI-EXCEPTION-SYSTEMMANAGEMENT-1008");
        }
        if (!BaseConstant.EXCEL_FORMAT.contains(fileName.substring(fileName.lastIndexOf(".")))) {
            MyExceptionHandler.publish("BUSI-EXCEPTION-SYSTEMMANAGEMENT-1008");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            MyExceptionHandler.publish("BUSI-EXCEPTION-SYSTEMMANAGEMENT-1009");
        }
        return inputStream;
    }
}
