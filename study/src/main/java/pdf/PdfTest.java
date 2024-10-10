package pdf;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
@Slf4j
public class PdfTest{

    public static void main(String[] args){
        String pdfPath = "test.pdf";

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(pdfPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdfDoc = new PdfDocument(writer);
        PdfHeaderFooterEvent event = new PdfHeaderFooterEvent();
        pdfDoc.addEventHandler(PdfDocumentEvent.END_PAGE, event);
        Document document = new Document(pdfDoc);
        for (int i = 0; i < 10*10 ; i++) {
            document.add(new Paragraph("test  contents"));

        }
        log.info(">>>>>>>>>>>");
        event.closeDocument(pdfDoc);
        document.close();


    }
}

