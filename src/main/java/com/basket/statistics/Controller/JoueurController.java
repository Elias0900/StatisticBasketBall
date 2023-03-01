package com.basket.statistics.Controller;

import com.basket.statistics.Service.JoueurService;
import com.basket.statistics.dto.JoueurDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/joueur")
public class JoueurController {
    @Autowired
    private JoueurService service;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<JoueurDTO> newJoueur(@RequestBody JoueurDTO jDto){
        JoueurDTO joueurDTO = service.saveOrUpdate(jDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(joueurDTO);
    }


    @GetMapping
    private ResponseEntity<List<JoueurDTO>> tousLesJoueurs(){
        List<JoueurDTO> joueurDTOList = service.getAll();
        return new ResponseEntity<>(joueurDTOList,HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    private ResponseEntity<JoueurDTO> findById(@PathVariable("id") long id){
        JoueurDTO joueurDTOList = service.findById(id);
        return new ResponseEntity<>(joueurDTOList,HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<JoueurDTO> modifierJoueur(@RequestBody JoueurDTO jDto){
        return  new ResponseEntity<>(service.saveOrUpdate(jDto),HttpStatus.OK);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long>suppression(@PathVariable("id") long id){
        service.suppressionJoueur(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

}
