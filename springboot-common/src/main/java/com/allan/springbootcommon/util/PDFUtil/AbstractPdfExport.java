package com.allan.springbootcommon.util.PDFUtil;

import java.util.List;

/**
 * pdf导出抽象类
 *
 * @author qinzz
 * @create 2018-10-19 15:29
 **/
public abstract class AbstractPdfExport<T> {
    private List<T> data;

    public AbstractPdfExport(List<T> data){
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 将data数据转换为字符串数组列表
     * @return
     */
    public abstract List<String[]> settingPdfColumns();
}
