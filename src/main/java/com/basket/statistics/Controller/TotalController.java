package com.basket.statistics.Controller;

import com.basket.statistics.Service.TotalService;
import com.basket.statistics.exception.TotalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
