package com.allan.springbootcommon.util.PDFUtil;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 导出PDF格式 工具类
 */
public class ExportPDFUtil {
    /**
     * 文件格式
     */
    private static final String PDF_TYPE = ".pdf";

    /**
     * 导出PDF格式
     * @param pdfName 文件名称
     * @param columnTitle  标题头
     * @param datas  数据
     * @param response
     * @throws Exception
     */
    public static void exportPDF(HttpServletResponse response, String pdfName, String[] columnTitle, AbstractPdfExport datas) throws  Exception{
        //主要是用来解决中文字体的问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
        BaseColor borderColor = new BaseColor(90, 140, 200);//Cell边框颜色
        BaseColor bgColor = new BaseColor(80, 130, 180);//Cell背景色

        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/pdf;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" +
                new String((pdfName + PDF_TYPE).getBytes("gb2312"), "ISO8859-1"));

        // 第一步，创建document对象
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        //rectPageSize = rectPageSize.rotate();//下面页面横置

        //创建document对象并指定边距
        Document document = new Document();
        try {
            setDocumentPro(document);
            /**
             * 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径
             * 第二步,将Document实例和文件输出流用PdfWriter类绑定在一起,从而完成向Document写，即写入PDF文档
             */
            //直接下载文件
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            //添加水印、页码
            writer.setPageEvent(new SetWaterMarkAndPage());
            //第3步,打开文档
            document.open();

            //------------开始写数据-------------------
            Paragraph titleText = new Paragraph(pdfName, FontChinese);// 标题
            titleText.setAlignment(Element.ALIGN_CENTER); // 居中设置
            titleText.setLeading(1f);//设置行间距,设置上面空白宽度
            document.add(titleText);

            //设置导出时间
            /*Font timeFont = new Font(bfChinese, 8, Font.NORMAL);
            Paragraph time = new Paragraph("【导出时间：" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + " 】", timeFont);
            time.setIndentationLeft(400);
            document.add(time);
            //加入空行
            Paragraph blankRow = new Paragraph(8f, " ", FontChinese);
            document.add(blankRow);*/

            //设置导出标题，居中显示
            Font titleFont = new Font(bfChinese, 8, Font.BOLD);
            Paragraph title = new Paragraph(pdfName, titleFont);// 标题
            title.setLeading(1f);//行距
            title.setAlignment(Element.ALIGN_CENTER);//居中
            Paragraph blankTitle = new Paragraph(8f, " ", FontChinese);
            document.add(blankTitle);


            //创建表格
            PdfPTable table = new PdfPTable(columnTitle.length);//列数
            table.setHorizontalAlignment(Element.ALIGN_CENTER);//内容水平居中
            //设置表格列头
            for (String header : columnTitle) {
                PdfPCell headCell = new PdfPCell();
                headCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                headCell.setColspan(columnTitle.length);//表格头合并单元格
                headCell.setBackgroundColor(bgColor);//表格头背景色
                headCell.setBorderColor(borderColor);//边框颜色
                headCell.setVerticalAlignment(Element.ALIGN_CENTER);//垂直居中
                headCell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
                headCell.addElement(new Paragraph(header , FontChinese));
                table.addCell(headCell);
            }
            //写入表格数据
            List<String[]> dataList = datas.settingPdfColumns();
            for (String[] content : dataList){
                for (int i = 0; i < content.length; i++) {
                    PdfPCell dataCell = new PdfPCell();
                    dataCell.setBorderColor(borderColor);//边框颜色
                    dataCell.setVerticalAlignment(Element.ALIGN_CENTER);//垂直居中
                    dataCell.setHorizontalAlignment(Element.ALIGN_CENTER); //水平居中
                    dataCell.addElement(new Paragraph(content[i] , FontChinese));
                    table.addCell(dataCell);
                }
            }

            document.add(table);//向文档中添加内容

        } catch (DocumentException de) {
            de.printStackTrace();
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println(ioe.getMessage());
        }finally {
            //关闭document文档
            document.close();
        }

        System.out.println("***** 生成PDF文件成功，文件名称："+pdfName+" *****");
    }

    /**
     * 设置文档属性  (与文档是否打开没有关联)
     * @param document 文档对象
     */
    private static void setDocumentPro(Document document){
        document.addTitle("geely");
        document.addAuthor("allan qin"); // 作者
        document.addSubject("Subject@iText sample"); // 主题
        document.addKeywords("Keywords@iText");// 关键字
        document.addCreator("allanQin@iText");// 创建者
    }



}
