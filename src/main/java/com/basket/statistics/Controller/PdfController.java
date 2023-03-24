package com.basket.statistics.Controller;

import com.basket.statistics.Service.GeneratePdfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @Autowired
    private GeneratePdfService service;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping(value = "/total/{joueurId}/{matchId}", produces = "application/octet-stream")
    public ResponseEntity<Resource> totalPDF( @PathVariable("joueurId") Long joueurId, @PathVariable("matchId") Long matchId) throws Exception {
        String outputPdfPath = service.generatePdfTotal(joueurId, matchId);

        File file = new File(outputPdfPath);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=fiche.pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);

    }

}
