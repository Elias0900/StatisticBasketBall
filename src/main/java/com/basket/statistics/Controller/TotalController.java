package com.basket.statistics.Controller;

import com.basket.statistics.Service.TotalService;
import com.basket.statistics.exception.TotalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhtmltopdf.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
@RequestMapping("api/total")
public class TotalController {

    @Autowired
    private TotalService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/{id}/match/{matchId}")
    private ResponseEntity<Double> totalPoint(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.totalPoint(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/passe/{id}/match/{matchId}")
    private ResponseEntity<Double> passe(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.totalPasse(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/rebond/{id}/match/{matchId}")
    private ResponseEntity<Double> rebond(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.totalRebond(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/inter/{id}/match/{matchId}")
    private ResponseEntity<Double> interception(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.totalInterc(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/contre/{id}/match/{matchId}")
    private ResponseEntity<Double> contres(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.totalContre(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/deuxpoints/{id}/match/{matchId}")
    private ResponseEntity<Double> deuxpoints(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.pourcentageDeuxPts(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }


    @GetMapping(value = "/troispoints/{id}/match/{matchId}")
    private ResponseEntity<Double> troispoints(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.pourcentageTroisPts(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/pourcentagelf/{id}/match/{matchId}")
    private ResponseEntity<Double> pourcentageLF(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.pourcentageLF(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }


    @GetMapping(value = "/pourcentage/{id}/match/{matchId}")
    private ResponseEntity<Double> pourcentage(@PathVariable("id") long id, @PathVariable("matchId") long matchId) throws TotalException {
        double totalDTO = service.pourcentage(id, matchId);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/total-joueur/{totalId}", produces = "application/octet-stream")
    public ResponseEntity<Resource> generateBulletinByStudentAndPromo(@PathVariable("totalId") long totalId) throws Exception {

        String outputPdfPath = service.generatePdf(totalId);

        File f = new File(outputPdfPath);
        Path path = Paths.get(f.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bulletinEtudiant"+totalId+".pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok().headers(headers).contentLength(f.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM).body((Resource) resource);
    }


}
