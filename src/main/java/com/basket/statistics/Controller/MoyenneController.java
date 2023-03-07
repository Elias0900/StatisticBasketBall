package com.basket.statistics.Controller;

import com.basket.statistics.Service.MoyenneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/moyenne")
public class MoyenneController {
    @Autowired
    private MoyenneService moyenneService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/points/{joueurId}")
    ResponseEntity<Double> avgPoint(@PathVariable("joueurId") long joueurId) {
       return ResponseEntity.status(HttpStatus.OK).body(moyenneService.avgPoint(joueurId));
    }

    @GetMapping(value = "/passe/{joueurId}")
    ResponseEntity<Double> passe(@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.avgPasse(joueurId));
    }

    @GetMapping(value = "/rebond/{joueurId}")
    ResponseEntity<Double> rebond(@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.avgRebond(joueurId));
    }

    @GetMapping(value = "/deux/{joueurId}")
    ResponseEntity<Double> deuxpts(@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.avgPourcentage2Pts(joueurId));
    }

    @GetMapping(value = "/trois/{joueurId}")
    ResponseEntity<Double> troispoints (@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.avgPourcentage3Pts(joueurId));
    }

    @GetMapping(value = "/lf/{joueurId}")
    ResponseEntity<Double> lf (@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.avgPourcentageLF(joueurId));
    }

    @GetMapping(value = "/shoot/{joueurId}")
    ResponseEntity<Double> tir(@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.shoot(joueurId));
    }
    @GetMapping(value = "/contre/{joueurId}")
    ResponseEntity<Double> contre(@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.contre(joueurId));
    }

    @GetMapping(value = "/inter/{joueurId}")
    ResponseEntity<Double> inter (@PathVariable("joueurId") long joueurId) {
        return ResponseEntity.status(HttpStatus.OK).body(moyenneService.inter(joueurId));
    }
}
