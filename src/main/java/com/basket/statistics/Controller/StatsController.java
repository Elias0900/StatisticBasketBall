package com.basket.statistics.Controller;

import com.basket.statistics.Service.StatsService;
import com.basket.statistics.dto.StatsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stats")
public class StatsController {

    @Autowired
    private StatsService service;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping( value = "/{id}" ,consumes = "application/json", produces = "application/json")
    private ResponseEntity<StatsDTO> newStats(@RequestBody StatsDTO jDto, @PathVariable("id") long id){
        StatsDTO statsDTO = service.saveOrUpdate(jDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<List<StatsDTO>> findByJoueurId(@PathVariable("id")long id){
        List<StatsDTO> statsDTO = service.findByJoueurId(id);
        return new ResponseEntity<>(statsDTO,HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<List<StatsDTO>> tousLesStats(){
        List<StatsDTO> StatsDTOList = service.getAll();
        return new ResponseEntity<>(StatsDTOList,HttpStatus.OK);
    }

    @GetMapping(value = "/stat/{id}")
    private ResponseEntity<StatsDTO> findById(@PathVariable("id") long id){
        StatsDTO StatsDTOList = service.findById(id);
        return new ResponseEntity<>(StatsDTOList,HttpStatus.OK);
    }


    @PutMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<StatsDTO> modifierStats(@RequestBody StatsDTO jDto, long id){
        return  new ResponseEntity<>(service.saveOrUpdate(jDto, id),HttpStatus.OK);

    }



    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long>suppression(@PathVariable("id") long id){
        service.suppression(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping( value = "/deuxpoints/{id}" )
    private ResponseEntity<StatsDTO> ajoutdeuxpoints(@PathVariable("id") long id){
        StatsDTO statsDTO = service.pointsMarque(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/rateproche/{id}" )
    private ResponseEntity<StatsDTO> tirRate(@PathVariable("id") long id){
        StatsDTO statsDTO = service.tirRate(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/troispoints/{id}" )
    private ResponseEntity<StatsDTO> troispoints(@PathVariable("id") long id){
        StatsDTO statsDTO = service.tirTroisPoints(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/rateloin/{id}" )
    private ResponseEntity<StatsDTO> rateTroispoints(@PathVariable("id") long id){
        StatsDTO statsDTO = service.tirTroisPointsRate(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/fautes/{id}" )
    private ResponseEntity<StatsDTO> faute(@PathVariable("id") long id){
        StatsDTO statsDTO = service.ajoutFautes(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/bp/{id}" )
    private ResponseEntity<StatsDTO> bp(@PathVariable("id") long id){
        StatsDTO statsDTO = service.ballonPerduAjout(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/contres/{id}" )
    private ResponseEntity<StatsDTO> contres(@PathVariable("id") long id){
        StatsDTO statsDTO = service.ajoutContre(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }
    @PostMapping( value = "/passe/{id}" )
    private ResponseEntity<StatsDTO> passe(@PathVariable("id") long id){
        StatsDTO statsDTO = service.ajoutPasse(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/rebondoff/{id}" )
    private ResponseEntity<StatsDTO> rebondOff(@PathVariable("id") long id){
        StatsDTO statsDTO = service.ajoutRebondOff(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

    @PostMapping( value = "/rebonddeff/{id}" )
    private ResponseEntity<StatsDTO> rebondDeff(@PathVariable("id") long id){
        StatsDTO statsDTO = service.ajoutrebondDeff(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(statsDTO);
    }

}
