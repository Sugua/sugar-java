package pdf;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.itextpdf.kernel.pdf.PdfName.BaseFont;

@Slf4j
public class PdfHeaderFooterEvent implements IEventHandler {

//    private final static String FONT_PATH = "C:\Windows\Fonts\simsun.ttc,0";

//    private final static BaseFont BASE_FONT = PdfFontFactory.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

//    private final static String logoPath = "D:\Users\admin\Downloads\logo.png";

    //总页码使用的模板对象
    public PdfFormXObject totalNumTemplate = null ;

    /**
     * 事件处理
     */
    @Override
    public void handleEvent(Event event){
        log.info("<<<<<<<<<<<<<");
        final PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        final PdfDocument pdfDoc = docEvent.getDocument();
        final Document doc = new Document(pdfDoc);
        final PdfPage page = docEvent.getPage();
        final Rectangle pageSize = page.getPageSize();
        final int pageNumber = pdfDoc.getPageNumber(page);
        final float pdfHeight = pageSize.getHeight();

        try{
            this.addPageHeader(doc, pdfHeight);
        }catch(Exception e){
            log.error("添加页眉出错", e);
        }

        try{
            this.addPageFooter(doc, pdfDoc, page, pdfHeight);
        }catch(Exception e){
            log.error("添加页脚出错", e);
        }

//        closeDocument(pdfDoc);
    }

    /**
     * 页眉
     */
    private void addPageHeader(Document document, float pdfHeight){
        //创建字体
        PdfFont textFont = null;
        try {
            textFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        float width = PageSize.A4.getWidth()-60;
        //表格 一行两列
        Table table = new Table(2);
        table.setWidth(width);

        //logo
//        Image logo = new Image(ImageDataFactory.create(logoPath));
//        table.addCell(new Cell().add(logo).setHeight(40).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));

        //名称
        Paragraph nameP = new Paragraph("test").setFont(textFont).setFontSize(10f);
        table.addCell(new Cell().add(nameP).setHorizontalAlignment(HorizontalAlignment.RIGHT).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setBorderTop(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));

        //设置表格的位置 页眉处
        table.setFixedPosition(document.getLeftMargin()-10, pdfHeight-document.getTopMargin()-40, table.getWidth());
        document.add(table);
    }

    /**
     * 页脚
     */
    private void addPageFooter(Document document, PdfDocument pdf, PdfPage page, float pdfHeight){
        Rectangle pageSize = page.getPageSize();
        //创建字体
        PdfFont textFont = null;
        try {
            textFont = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        float width = PageSize.A4.getWidth()-60;
        //表格 一行两列
        Table table = new Table(3);
        table.setWidth(width);

        //导入人
        Paragraph nameP = new Paragraph("admin").setFont(textFont).setFontSize(10f);
        table.addCell(new Cell().add(nameP).setBorderBottom(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));

        //页码 居右
        if(null == totalNumTemplate){
            totalNumTemplate = new PdfFormXObject(new Rectangle(0,0,20,20));
        }
        int pageNum = pdf.getPageNumber(page);
        PdfCanvas canvasP = new PdfCanvas(page);
        Canvas canvas = new Canvas(canvasP, pageSize);
        canvas.setFontSize(10f);
        Paragraph pageP = new Paragraph(String.valueOf(pageNum)).add(" / ").setFont(textFont).setFontSize(10f);
        canvas.showTextAligned(pageP, 295, 10, TextAlignment.RIGHT);
        canvas.close();
        canvasP.addXObjectAt(totalNumTemplate, 300f, 7f);
        canvasP.release();

        //日期
        Paragraph dateP = new Paragraph("2022/09/30").setFont(textFont).setFontSize(10f);
        table.addCell(new Cell().add(dateP).setBorderBottom(Border.NO_BORDER).setBorderLeft(Border.NO_BORDER).setBorderRight(Border.NO_BORDER));
        table.setFixedPosition(document.getLeftMargin()-10, pdfHeight-document.getBottomMargin()-40, table.getWidth());
        document.add(table);
    }

    /**
     * 文档关闭  手动调用
     */
    public void closeDocument(PdfDocument docment){
        //修改总页码  居左
        if(null == totalNumTemplate){
            totalNumTemplate = new PdfFormXObject(new Rectangle(0,0,20,20));
        }
        Canvas canvas = new Canvas(totalNumTemplate, docment);
        canvas.setFontSize(10f);
        canvas.showTextAligned(Integer.toString(docment.getNumberOfPages()),0,3f,TextAlignment.LEFT);
        canvas.close();
    }
}
