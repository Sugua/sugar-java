package pdf;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TableMergeCellsExample {
    public static void main(String[] args) {
        String dest = "example_with_image_and_table.pdf";

        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdf = new PdfDocument(writer);

            PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

            Document document = new Document(pdf, PageSize.A3);
            document.setFont(font);
            // 创建一个包含4行3列的Table
            Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
            // 添加单元格并填充内容
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    Paragraph paragraph = new Paragraph("Cell " + (i + 1) + "," + (j + 1));
                    table.addCell(new Cell().add(paragraph));
                }
            }
            Paragraph paragraph = new Paragraph("合并单元格");

            table.addCell(new Cell(1,2).add(paragraph));
            table.addCell("lie2");
            table.addCell("lie3");
            document.add(table);

            document.close();
            System.out.println("PDF with Image and Table Created!");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // 合并第一行的两个单元格
//        table.mergeCells(0, 1, 0, 2);

        // 合并第二行的全部三个单元格
//        table.mergeCells(1, 0, 1, 2);

        // 在此处添加table到Document中
    }
}