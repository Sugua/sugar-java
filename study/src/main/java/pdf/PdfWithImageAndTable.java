package pdf;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.InputStream;

public class PdfWithImageAndTable {
    public static void main(String[] args) {
        String dest = "pdf/报账单[5236485]-九机才子汇店[523645]-费用告知单.pdf";
        try {
            File f = new File("pdf");
            if (f.exists()){
                System.out.println("lala");
                f.mkdirs();

            }
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A3);
            PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
            document.setFont(font);

            // 添加图片
//            ImageData imageData = ImageDataFactory.create("path/to/image.jpg");
//            Image image = new Image(imageData);
//            document.add(image);

            // 添加表格
//            float[] pointColumnWidths = {150F, 150F, 150F, 150F, 150F, 150F, 150F, 150F, 150F, 150F, 150F, 150F, 150F};
            Table table = new Table(UnitValue.createPercentArray(13)).useAllAvailableWidth();
            writeHeader(table);

            for (int i = 0; i < 130; i++) {
                table.addCell(String.valueOf(i)) ;
            }

            writerEnd(table);
            document.add(table);


            document.close();
            pdf.close();
            writer.close();

            System.out.println("PDF with Image and Table Created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writerEnd(Table table) {
        Cell blank = new Cell(2, 13).add(new Paragraph("\n\t"));
        blank.setBorder(Border.NO_BORDER);
        blank.setTextAlignment(TextAlignment.RIGHT);

        table.addCell(blank);

        Cell confirmCell = new Cell(1, 13).add(new Paragraph("确认人：\t\t\t\t\t"));
        confirmCell.setBorder(Border.NO_BORDER);
        confirmCell.setTextAlignment(TextAlignment.RIGHT);
        table.addCell(confirmCell);


        Cell date = new Cell(1, 13).add(new Paragraph("日期：\t\t\t\t\t"));
        date.setBorder(Border.NO_BORDER);
        date.setTextAlignment(TextAlignment.RIGHT);
        table.addCell(date);

    }

    private static void writeHeader(Table table) {
        System.out.println("write header ");

        Paragraph title = new Paragraph("渠道费用告知单");
        title.setBold();
        title.setFontSize(15);
        Cell titleCell = new Cell(1, 13).add(title);
        titleCell.setBorder(Border.NO_BORDER);
        titleCell.setTextAlignment(TextAlignment.CENTER);
        titleCell.setBold();
        table.addHeaderCell(titleCell);

        Paragraph unit = new Paragraph("单位：元");
        unit.setFontSize(7);
        Cell unitCell = new Cell(1, 13).add(unit);
        unitCell.setBorder(Border.NO_BORDER);
        unitCell.setTextAlignment(TextAlignment.RIGHT);
        table.addHeaderCell(unitCell);

        table.addHeaderCell(new Cell(2,1).add(new Paragraph("账期")));
        table.addHeaderCell(new Cell(2,1).add(new Paragraph("地市")));
        table.addHeaderCell(new Cell(2,1).add(new Paragraph("渠道编码")));
        table.addHeaderCell(new Cell(2,1).add(new Paragraph("渠道名称")));

        table.addHeaderCell(new Cell(1,5).add(new Paragraph("应发")));
        table.addHeaderCell(new Cell(1,2).add(new Paragraph("积金池")));

        table.addHeaderCell(new Cell(2,1).add(new Paragraph("税费")));
        table.addHeaderCell(new Cell(2,1).add(new Paragraph("实发")));

        table.addHeaderCell("CHBN基础业务");
        table.addHeaderCell("星级积分激励");
        table.addHeaderCell("房租积分激励");
        table.addHeaderCell("分公司激励");
        table.addHeaderCell("分公司扣罚");
        table.addHeaderCell("积金池扣留");
        table.addHeaderCell("积金池补贴");
        table.setTextAlignment(TextAlignment.CENTER);
        table.setVerticalAlignment(VerticalAlignment.BOTTOM);


    }
}
