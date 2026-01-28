package com.library.reportservice;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExcelGeneratorService {

    public byte[] generateExcel(List<BookEvent> events) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Book events");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Treść zdarzenia");
        header.createCell(2).setCellValue("Data");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        int rowIdx = 1;
        for (BookEvent event : events) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(event.getId());
            row.createCell(1).setCellValue(event.getMessage());
            row.createCell(2).setCellValue(event.getCreatedAt().format(formatter));
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }
}
