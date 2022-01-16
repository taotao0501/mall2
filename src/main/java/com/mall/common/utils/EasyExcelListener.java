package com.mall.common.utils;

import com.mall.common.excel.ExcelModel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhipeng
 * @date 2021/12/29
 */
public class EasyExcelListener<T extends ExcelModel> extends AnalysisEventListener<T> {

    /**
     *  用于保存正常的数据
     */
    private List<T> data = Collections.synchronizedList(new ArrayList<>());

    /**
     *  用于保存异常数据
     */
    private List<T> error = Collections.synchronizedList(new ArrayList<>());

    private Map<String, List<String>> arg;

    @Override
    public void invoke(T o, AnalysisContext analysisContext) {
        if(o.validation(arg)) {
            data.add(o);
        }else {
            error.add(o);
            System.out.println("error--" + o);
        }
    }

    /**
     *  excel 读取完之后的操作
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//        analysisContext.
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getError() {
        return error;
    }

    public void setError(List<T> error) {
        this.error = error;
    }

    public void setArg(Map<String, List<String>> arg) {
        this.arg = arg;
    }
}
