package com.mall.common.utils;

import com.mall.common.excel.ExcelModel;
import com.mall.common.excel.SheetData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * easyExcel工具类
 *
 * @author wangzhipeng
 */
public class EasyExcelUtil<T extends ExcelModel> {
    public EasyExcelUtil() {
    }

    public void writeExcelWithModel(OutputStream outputStream, List<T> dataList, Class<? extends ExcelModel> classT, String sheetName, CellWriteHandler... cellWriteHandlers) {
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
        ExcelWriterBuilder writerBuilder = EasyExcel.write(outputStream, classT);
        ExcelWriterSheetBuilder excelWriterSheetBuilder = writerBuilder.sheet(sheetName).registerWriteHandler(horizontalCellStyleStrategy);
        if (null != cellWriteHandlers && cellWriteHandlers.length > 0) {

            for (CellWriteHandler cellWriteHandler : cellWriteHandlers) {
                excelWriterSheetBuilder.registerWriteHandler(cellWriteHandler);
            }
        }
        excelWriterSheetBuilder.doWrite(dataList);
    }

    public EasyExcelListener<T> readExcelWithModel(InputStream fileInputStream, Class<?> clazz, Map<String, List<String>> arg) {
        EasyExcelListener<T> listener = new EasyExcelListener<>();
        listener.setArg(arg);
        ExcelReader excelReader = EasyExcel.read(fileInputStream, clazz, listener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
        return listener;
    }

    public void createExcel(OutputStream os, List<T> data, T t, String sheetName) {
        List<Integer> columns = t.getEmphasizeColumns();
        HashMap<Integer, String> annotationsMap = t.getAnnotationsMap();
        HashMap<Integer, String[]> dropDownMap = t.getDropDownMap();
        EasyExcelTitleHandler easyExcelTitleHandler = new EasyExcelTitleHandler(columns, IndexedColors.RED.index, annotationsMap, dropDownMap);
        this.writeExcelWithModel(os, data, t.getClass(), sheetName, easyExcelTitleHandler);
    }

    public void createDownloadImportExcel(OutputStream os, List<SheetData<?>> sheets) {
        ExcelWriter excelWriter = EasyExcel.write(os).build();
        sheets.forEach((sheet) -> {
            ExcelModel t = sheet.getT();
            List<Integer> columns = t.getEmphasizeColumns();
            HashMap<Integer, String> annotationsMap = t.getAnnotationsMap();
            HashMap<Integer, String[]> dropDownMap = t.getDropDownMap();
            EasyExcelTitleHandler easyExcelTitleHandler = new EasyExcelTitleHandler(columns, IndexedColors.RED.index, annotationsMap, dropDownMap);
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
            headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            contentWriteCellStyle.setWrapped(true);
            contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
            ExcelWriterSheetBuilder sheetBuilder = EasyExcel.writerSheet(sheet.getSheetNo(), sheet.getSheetName());
            ExcelWriterSheetBuilder excelWriterSheetBuilder = sheetBuilder
                    .registerWriteHandler(easyExcelTitleHandler)
                    .registerWriteHandler(horizontalCellStyleStrategy);
            sheetBuilder.head(sheet.getClazz());
            WriteSheet writeSheet = excelWriterSheetBuilder.build();
            excelWriter.write(sheet.getData(), writeSheet);
        });
        excelWriter.finish();
    }

    public void createSelfDefineExportExcel(OutputStream os, List<SheetData<?>> sheets, List<WriteHandler> handlerList) {
        ExcelWriter excelWriter = EasyExcel.write(os).build();
        sheets.forEach((sheet) -> {
            ExcelModel t = sheet.getT();
            List<Integer> columns = t.getEmphasizeColumns();
            HashMap<Integer, String> annotationsMap = t.getAnnotationsMap();
            HashMap<Integer, String[]> dropDownMap = t.getDropDownMap();
            EasyExcelTitleHandler easyExcelTitleHandler = new EasyExcelTitleHandler(columns, IndexedColors.RED.index, annotationsMap, dropDownMap);
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
            headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            contentWriteCellStyle.setWrapped(true);
            contentWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
            ExcelWriterSheetBuilder sheetBuilder = EasyExcel.writerSheet(sheet.getSheetNo(), sheet.getSheetName());
            if (CollectionUtils.isNotEmpty(handlerList)) {
                for (WriteHandler writeHandler : handlerList) {
                    sheetBuilder.registerWriteHandler(writeHandler);
                }
            }
            ExcelWriterSheetBuilder excelWriterSheetBuilder = sheetBuilder
                    .registerWriteHandler(easyExcelTitleHandler)
                    .registerWriteHandler(horizontalCellStyleStrategy);

            sheetBuilder.head(sheet.getClazz());
            WriteSheet writeSheet = excelWriterSheetBuilder.build();
            excelWriter.write(sheet.getData(), writeSheet);
        });
        excelWriter.finish();
    }
}

