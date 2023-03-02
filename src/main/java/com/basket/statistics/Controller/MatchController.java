package com.basket.statistics.Controller;

import com.basket.statistics.Service.MatchService;
import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.exception.MatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/match")
public class MatchController {
    @Autowired
    private MatchService service;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<MatchDTO> newMatch(@RequestBody MatchDTO jDto) throws MatchException {
        MatchDTO MatchDTO = service.saveOrUpdate(jDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(MatchDTO);
    }


    @GetMapping
    private ResponseEntity<List<MatchDTO>> tousLesMatchs(){
        List<MatchDTO> MatchDTOList = service.getAll();
        return new ResponseEntity<>(MatchDTOList,HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<MatchDTO> modifierMatch(@RequestBody MatchDTO jDto) throws MatchException {
        return  new ResponseEntity<>(service.saveOrUpdate(jDto),HttpStatus.OK);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long>suppression(@PathVariable("id") long id){
        service.suppressionMatch(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping(value = "/scoredomicile/{id}/joueur/{jid}")
    private ResponseEntity<Integer> domicile(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer2Point(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

}
