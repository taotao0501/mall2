package com.mall.controller;

import com.mall.common.ApiRestResponse;
import com.mall.common.EmployeeReportResult;
import com.mall.common.ResultVO;
import com.mall.common.excel.Bean.FoodCategoryVO;
import com.mall.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: Bentao She
 * @Email: harrypotterandsbt@gmail.com
 * @Date: 2021/9/6 17:16
 * @Version: V1.0
 **/

@RestController
@RequestMapping("/test")
@Api(tags = "测试Excel导入导出")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation("模板")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        EmployeeReportResult employeeReportResult = new EmployeeReportResult(1, "王", "13938521006");
        EmployeeReportResult employeeReportResult1 = new EmployeeReportResult(1, "王", "13938521006");
        //2.创建工作簿
        ArrayList<EmployeeReportResult> list = new ArrayList<>();
        list.add(employeeReportResult);
        list.add(employeeReportResult1);
        XSSFWorkbook workbook = new XSSFWorkbook();

        //3.构造sheet
        String[] titles = {"编号", "姓名", "手机","学历","住址"};
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        AtomicInteger headersAi = new AtomicInteger();
        for (String title : titles) {
            org.apache.poi.ss.usermodel.Cell cell = row.createCell(headersAi.getAndIncrement());
            cell.setCellValue(title);
        }
        AtomicInteger datasAi = new AtomicInteger(1);
        Cell cell = null;
        for (EmployeeReportResult report : list) {
            org.apache.poi.ss.usermodel.Row dataRow = sheet.createRow(datasAi.getAndIncrement());
            //编号
            org.apache.poi.ss.usermodel.Cell cell1 = dataRow.createCell(0);
            cell1.setCellValue(report.getUserId());

            //姓名
            cell1 = dataRow.createCell(1);
            cell1.setCellValue(report.getUserName());

            //手机
            cell1 =  dataRow.createCell(2);
            cell1.setCellValue(report.getMobile());
        }
        String fileName = URLEncoder.encode("2021-人员信息.xlsx", "UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("content-disposition", "attachment;filename=" + new
                String(fileName.getBytes("ISO8859-1")));
        response.setHeader("filename", fileName);
        workbook.write(response.getOutputStream());
    }

//    @GetMapping("/exportByEasyExcel")
//    public void exportByEasyExcel();

    @ApiOperation("下载模板")
    @GetMapping("/export-template")
    public void exportTemplate(HttpServletResponse response) {
        testService.getTemplate(response);
    }

    @ApiOperation("批量导入生鲜种类")
    @PostMapping("/import-template")
    public ResultVO<List<FoodCategoryVO>> importTemplate(@RequestParam("file") @Valid MultipartFile file) {
        List<FoodCategoryVO> res = testService.importTemplate(file);
        if (CollectionUtils.isEmpty(res)) {
            res = new ArrayList<>();
        }
        return new ResultVO<List<FoodCategoryVO>>().success(res);
    }

}