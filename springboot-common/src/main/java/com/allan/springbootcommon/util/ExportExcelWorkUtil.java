package com.allan.springbootcommon.util;

import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 导出工具类
 *
 * @author yingou
 * @create 2018-05-15 09:04
 **/
public class ExportExcelWorkUtil {


    /**
     * 数据导出到Excle表格
     *
     * @param response   请求
     * @param contents   数据
     * @param header     表格头
     * @param columnths  单元格宽度
     * @param headerName 文件名称
     */
    public static void exportExcleWork(HttpServletResponse response,
                                       List<List<String>> contents,
                                       String[] header,
                                       Short[] columnths,
                                       String headerName) {
        Workbook wb = CreateExcelUtils.createExcel(contents,
                header, columnths, headerName);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String((headerName + ".xls").getBytes("GBK"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            wb.write(out);
            out.flush(); // 清空缓存区
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 数据导出，方法二（导出的表格样式不同）
     *
     * @param title        文件的标题
     * @param headers      表格标题行
     * @param dataList     导出数据
     * @param columnWidths 单元格宽度
     * @param response     请求
     */
    public static void exportExcle(List<Object[]> dataList, HttpServletResponse response, String title, String[] headers, Short[] columnWidths) {
        OutputStream out = null;
        try {
            // 防止中文乱码
            String headStr = "attachment; filename=\"" + new String((title + ".xls").getBytes("gb2312"),
                    "ISO8859-1") + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            ExportExcel export = new ExportExcel(title, headers, dataList, columnWidths);
            export.export(out);
            out.flush(); // 清空缓存区
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();//关闭流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
