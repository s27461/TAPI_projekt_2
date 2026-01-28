package com.library.reportservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final BookEventRepository repository;
    private final ExcelGeneratorService excelService;

    public ReportController(BookEventRepository repository,
                            ExcelGeneratorService excelService) {
        this.repository = repository;
        this.excelService = excelService;
    }

    @GetMapping("/book-events")
    public ResponseEntity<byte[]> exportBookEvents(
            @RequestParam int year,
            @RequestParam int month
    ) throws Exception {

        YearMonth ym = YearMonth.of(year, month);

        LocalDateTime from = ym.atDay(1).atStartOfDay();
        LocalDateTime to = LocalDateTime.now();

        List<BookEvent> events =
                repository.findByCreatedAtBetween(from, to);

        byte[] excel = excelService.generateExcel(events);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=book-events-" + year + "-" + month + ".xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excel);
    }

}
