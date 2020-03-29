package com.allan.springbootcommon.util.PDFUtil;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

/**
 * 为PDF文档添加水印、页码
 */
public class SetWaterMarkAndPage extends PdfPageEventHelper {


    /**
     * 添加水印
     * @param writer 写入对象
     * @param document 文档对象
     */
    @Override
    public void onStartPage(PdfWriter writer, Document document){
        //获取水印图片的路径
        String markImagePath = "D:\\alibaba.jpg";//水印图片
        float pageHeight = document.getPageSize().getHeight();
        float pageWidth = document.getPageSize().getWidth();

        try {
            Image img = Image.getInstance(markImagePath);//生成水印图片
            final float IMAGE_SIZE = 0.6f;//图片缩放比例，大小0
            float plainWidth = img.getPlainWidth() * IMAGE_SIZE;
            float plainHeight = img.getPlainHeight() * IMAGE_SIZE;
            img.scaleAbsolute(plainWidth, plainHeight);//设置图片大小
            img.setAlignment(Image.UNDERLYING); // 在字下面
            //设置水印图片的坐标。
            img.setAbsolutePosition(pageWidth - plainWidth - 35, pageHeight - plainHeight - 5);
            //image.setRotation(-30);//设置旋转 弧度
            //image.setRotationDegrees(-45);//设置旋转 角度
            //image.scalePercent(50);//设置依照比例缩放，与设置大小作用相同

            //将水印图片加入到文档中,可使用循环添加多个，添加多个时注意每个图片的坐标等属性
            document.add(img);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加水印失败！");
        }
    }

    /**
     * 添加页码等设置
     * @param writer 写入对象
     * @param document 文档对象
     */
    @Override
    public  void onEndPage(PdfWriter writer, Document document) {
        // 页眉、页脚
        PdfContentByte pcb = writer.getDirectContent();
        try {
            pcb.setFontAndSize(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED), 10);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
        pcb.saveState();
        pcb.beginText();
        // Footer
        float bottom = document.bottom(-20);
        pcb.showTextAligned(PdfContentByte.ALIGN_CENTER, "第 " + writer.getPageNumber() + " 页", (document.right() + document.left()) / 2, bottom, 0);
        pcb.endText();

        pcb.restoreState();
        pcb.closePath();
    }
}
