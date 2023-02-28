package com.basket.statistics.Controller;

import com.basket.statistics.Service.TotalService;
import com.basket.statistics.dto.TotalDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/total")
public class TotalController {

    @Autowired
    private TotalService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/{id}")
    private ResponseEntity<Integer> totalPoint(@PathVariable("id") long id) {
        int totalDTO = service.totalPoint(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }

    @GetMapping(value = "/passe/{id}")
    private ResponseEntity<Integer> passe(@PathVariable("id") long id) {
        int totalDTO = service.totalPasse(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }

    @GetMapping(value = "/rebond/{id}")
    private ResponseEntity<Integer> rebond(TotalDTO jDto, @PathVariable("id") long id) {
        int totalDTO = service.totalRebond(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }

    @GetMapping(value = "/deuxpoints/{id}")
    private ResponseEntity<Double> deuxpoints(@PathVariable("id") long id) {
        double totalDTO = service.pourcentageDeuxPts(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }


    @GetMapping(value = "/troispoints/{id}")
    private ResponseEntity<Double> troispoints(@PathVariable("id") long id) {
        double totalDTO = service.pourcentageTroisPts(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }

    @GetMapping(value = "/pourcentagelf/{id}")
    private ResponseEntity<Double> pourcentageLF(@PathVariable("id") long id) {
        double totalDTO = service.pourcentageLF(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }


    @GetMapping(value = "/pourcentage/{id}")
    private ResponseEntity<Double> pourcentage(@PathVariable("id") long id) {
        double totalDTO = service.pourcentage(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(totalDTO);
    }

}
