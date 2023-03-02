package com.basket.statistics.Controller;

import com.basket.statistics.Service.EquipeService;
import com.basket.statistics.dto.EquipeDTO;
import com.basket.statistics.dto.JoueurDTO;
import com.basket.statistics.exception.EquipeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipe")
public class EquipeController {
    @Autowired
    private EquipeService service;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    private ResponseEntity<List<EquipeDTO>> getAll(){
        List<EquipeDTO> equipeDTOS = service.findAll();
        return new ResponseEntity<>(equipeDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<EquipeDTO> getById(@PathVariable("id")long id) throws EquipeException{
        EquipeDTO equipeDTO = service.findById(id);
        return new ResponseEntity<>(equipeDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<EquipeDTO> nouvelEquipe(@RequestBody EquipeDTO e) throws EquipeException {
        EquipeDTO equipeDTO = service.saveOrUpdate(e) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(equipeDTO);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<EquipeDTO> modifierJoueur(@RequestBody EquipeDTO eDto) throws EquipeException{
        return  new ResponseEntity<>(service.saveOrUpdate(eDto),HttpStatus.OK);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long>suppression(@PathVariable("id") long id){
        service.suppression(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }


}
