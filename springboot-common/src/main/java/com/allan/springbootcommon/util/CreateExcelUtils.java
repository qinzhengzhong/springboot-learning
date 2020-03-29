package com.allan.springbootcommon.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * 导出工具类（设置表格等）
 *
 * @author yingou
 * @create 2018-05-15 09:04
 **/
public class CreateExcelUtils{
	public static Workbook createExcel(List<List<String>> list,String [] header,
			Short[] columnWidths,String headerName){ 
		Workbook wb = new HSSFWorkbook();        
		// 创建第一个sheet（页），并命名        
		Sheet sheet = wb.createSheet(headerName);        
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。 
		if(columnWidths!=null){
			for(int i=0;i<columnWidths.length;i++){
				sheet.setColumnWidth((short) i, columnWidths[i]);
			}
		}else{
			for(int i=0;i<header.length;i++){
				sheet.setColumnWidth((short) i, (short)35.7 * 150);
			}
		}
		// 创建第一行       
		Row row = sheet.createRow((short) 0);        
		// 创建两种单元格格式        
		CellStyle cs = wb.createCellStyle();        
		CellStyle cs2 = wb.createCellStyle();        
		// DataFormat df = wb.createDataFormat();        
		// 创建两种字体       
		Font f = wb.createFont();        
		Font f2 = wb.createFont();        
		// 创建第一种字体样式        
		f.setFontHeightInPoints((short) 10);        
		f.setColor(IndexedColors.RED.getIndex());        
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);        
		// 创建第二种字体样式        
		f2.setFontHeightInPoints((short) 10);        
		f2.setColor(IndexedColors.BLACK.getIndex());        
		f2.setBoldweight(Font.BOLDWEIGHT_BOLD);        
		// 设置第一种单元格的样式        
		cs.setFont(f);        
		cs.setBorderLeft(CellStyle.BORDER_THIN);        
		cs.setBorderRight(CellStyle.BORDER_THIN);        
		cs.setBorderTop(CellStyle.BORDER_THIN);        
		cs.setBorderBottom(CellStyle.BORDER_THIN);        
		// cs.setDataFormat(df.getFormat("#,##0.0"));        
		// 设置第二种单元格的样式       
		cs2.setFont(f2);        
		cs2.setBorderLeft(CellStyle.BORDER_THIN);        
		cs2.setBorderRight(CellStyle.BORDER_THIN);        
		cs2.setBorderTop(CellStyle.BORDER_THIN);        
		cs2.setBorderBottom(CellStyle.BORDER_THIN); 
		
		//设置excel标题
		Cell cell = null;
		for(int i=0;i<header.length;i++){
			cell = row.createCell(i);
			cell.setCellStyle(cs);
			cell.setCellValue(header[i]);
		}
		//填充数据
		for(int i=0;i<list.size();i++){
			Row row2 = sheet.createRow((short) i+1);        
			// 创建两种单元格格式        
			List<String> temp = (List<String>)list.get(i);
			for(int j=0;j<temp.size();j++){
				cell = row2.createCell(j);
				cell.setCellStyle(cs2);
				if(StringUtils.isNotBlank(temp.get(j)))cell.setCellValue(temp.get(j).toString());
			}
		}
		
		return wb;
	}
}
