package com.allan.springbootcommon.util.PDFUtil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.List;

/**
 * PDF样式设计工具类
 *
 * 参考文章：https://blog.csdn.net/the_first_c/article/details/53522713?utm_source=blogxgwz1
 */
public class PDFTool {

    /**
     * 正文body 字体
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Font setChineseFont() throws DocumentException, IOException {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        return FontChinese;
    }

    /**
     * table thead 字体
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Font setTheadFont() throws DocumentException, IOException {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 11, Font.NORMAL);
        return FontChinese;
    }

    /**
     * table tbody 字体
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Font setTbodyFont() throws DocumentException, IOException {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 9, Font.NORMAL);
        return FontChinese;
    }

    /**
     * 文档一号标题
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Font setDheadFont() throws DocumentException, IOException {
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 14, Font.NORMAL);
        return FontChinese;
    }

    /**
     * 获取字符长度（为了让汉字和字母长度统一）
     * @param s
     * @return
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;

    }

    /**
     * @Description 添加行内容
     * @author GuoMing
     * @date 2016年11月29日 下午3:33:06
     * @param document  文档对象
     * @param list      行内容列表
     * @return          返回添加行后的文档对象
     * @throws DocumentException
     */
    public static Document addParagraph(Document document, List<String> list, Font font) throws DocumentException{
        for(String p : list){
            document.add(new Paragraph(p,font));
        }
        return document;
    }


    /**
     * @Description 添加表格
     * @author GuoMing
     * @date 2016年11月29日 下午3:34:27
     * @param document  文档对象
     * @param thead     表头列表
     * @param thead     内容列表
     * @return          添加表格之后的文档对象
     * @throws DocumentException
     */
    public static Document addTable(Document document, PdfPTable table, List<String> thead, List<List<String>> tbody, Font TBFont, Font THFont) throws DocumentException{
        //添加表头
        for(String p:thead){
            PdfPCell cell=new PdfPCell(new Phrase(p,THFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(new BaseColor(216, 218, 220));
            table.addCell(cell);
        }
        //添加表内容
        for(List<String> p:tbody){
            for(String c:p){
                PdfPCell cell=new PdfPCell(new Phrase(c,TBFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
        }
        document.add(table);
        return document;
    }

    /**
     * 设置行距
     * @param document
     * @param i
     * @throws DocumentException
     */
    public static void addParaAfter(Document document,float i) throws DocumentException {
        // TODO Auto-generated method stub
        Paragraph p=new Paragraph();
        p.setSpacingAfter(i);
        document.add(p);
    }

    public static void addParaBefore(Document document,float i) throws DocumentException {
        Paragraph p=new Paragraph();
        p.setSpacingBefore(i);
        document.add(p);
    }

}
