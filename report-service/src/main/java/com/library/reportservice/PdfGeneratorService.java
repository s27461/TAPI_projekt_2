package com.library.reportservice;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfGeneratorService {

    private static final String OUTPUT_DIR = "report-pdfs";

    public void generatePdf(BookEvent event) throws Exception {
        File folder = new File(OUTPUT_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String filePath = OUTPUT_DIR + File.separator + "event_" + event.getId() + ".pdf";

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();
        document.add(new Paragraph(event.getMessage()));
        document.close();

        System.out.println("PDF wygenerowany: " + filePath);
    }
}
