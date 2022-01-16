package com.mall.common.utils;

import com.mall.common.constant.BaseConstant;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.util.StyleUtil;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;


/**
 * <pre>
 *  操作标题行单元格颜色
 * </pre>
 *
 * @author wangzhipeng
 */
@Slf4j
public class EasyExcelTitleHandler implements CellWriteHandler {

    /**
     操作列
     */
    private final List<Integer> columnIndex;
    /**
     颜色
     */
    private final Short colorIndex;
    /**
     批注<列的下标,批注内容>
     */
    private final HashMap<Integer,String> annotationsMap;
    /**
     下拉框值
     */
    private final HashMap<Integer,String[]> dropDownMap;

    public EasyExcelTitleHandler(List<Integer> columnIndex, Short colorIndex, HashMap<Integer, String> annotationsMap, HashMap<Integer, String[]> dropDownMap) {
        this.columnIndex = columnIndex;
        this.colorIndex = colorIndex;
        this.annotationsMap = annotationsMap;
        this.dropDownMap = dropDownMap;
    }

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
        if(isHead){
            // 设置列宽
            Sheet sheet = writeSheetHolder.getSheet();
            writeSheetHolder.getSheet().getRow(0).setHeight((short)(1.8*256));
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            Drawing drawing = sheet.createDrawingPatriarch();

            // 设置标题字体样式
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontName("宋体");
            headWriteFont.setFontHeightInPoints((short)14);
            headWriteFont.setBold(true);
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
            if (CollectionUtils.isNotEmpty(columnIndex) &&
                    colorIndex != null &&
                    columnIndex.contains(cell.getColumnIndex())) {
                // 设置字体颜色
                headWriteFont.setColor(colorIndex);
            }
            headWriteCellStyle.setWriteFont(headWriteFont);
            headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            CellStyle cellStyle = StyleUtil.buildHeadCellStyle(workbook, headWriteCellStyle);
            cell.setCellStyle(cellStyle);

            if (null != annotationsMap && annotationsMap.containsKey(cell.getColumnIndex())) {
                // 批注内容
                String context = annotationsMap.get(cell.getColumnIndex());
                // 创建绘图对象
                Comment comment=drawing.createCellComment(new XSSFClientAnchor(0, 0, 0,0, (short) cell.getColumnIndex(), cell.getRowIndex(), (short) cell.getColumnIndex()+2,  (short) cell.getRowIndex()+2));
                comment.setString(new XSSFRichTextString(context));
                cell.setCellComment(comment);
            }

            if(null != dropDownMap && !dropDownMap.isEmpty() &&
                    dropDownMap.containsKey(cell.getColumnIndex())){
                String[] data = dropDownMap.get(cell.getColumnIndex());
                DataValidationHelper dvHelper = sheet.getDataValidationHelper();
                DataValidationConstraint dvConstraint = dvHelper.createExplicitListConstraint(data);
                CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, cell.getColumnIndex(), cell.getColumnIndex());
                DataValidation validation = dvHelper.createValidation(dvConstraint, addressList);
                validation.createPromptBox("提示", "只能选择下拉框里的值");
                validation.setSuppressDropDownArrow(true);
                validation.setShowPromptBox(true);
                validation.setShowErrorBox(true);
                validation.setEmptyCellAllowed(false);
                sheet.addValidationData(validation);
                dropDownMap.remove(cell.getColumnIndex());
            }
        }
        // cell 匹配到错误信息规则，cell 字体设置为红色
        Pattern pat = Pattern.compile(BaseConstant.EXCEL_ERROR);
        cell.setCellType(CellType.STRING);
        if(pat.matcher(cell.getStringCellValue()).matches()) {
            Workbook workbook = writeSheetHolder.getSheet().getWorkbook();
            CellStyle cellStyle1 = workbook.createCellStyle();
            Font cellFont = workbook.createFont();
            assert colorIndex != null;
            cellFont.setColor(colorIndex);
            cellStyle1.setFont(cellFont);
            cell.setCellStyle(cellStyle1);
        }
    }
}
