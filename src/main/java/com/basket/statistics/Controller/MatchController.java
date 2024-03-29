package com.basket.statistics.Controller;

import com.basket.statistics.Service.MatchService;
import com.basket.statistics.dto.JoueurDTO;
import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.exception.EquipeException;
import com.basket.statistics.exception.MatchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private ResponseEntity<List<MatchDTO>> tousLesMatchs() {
        List<MatchDTO> MatchDTOList = service.getAll();
        return new ResponseEntity<>(MatchDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<MatchDTO> findById(@Param("id") long id) throws MatchException {
        MatchDTO MatchDTOList = service.findById(id);
        return new ResponseEntity<>(MatchDTOList, HttpStatus.OK);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    private ResponseEntity<MatchDTO> modifierMatch(@RequestBody MatchDTO jDto) throws MatchException {
        return new ResponseEntity<>(service.saveOrUpdate(jDto), HttpStatus.OK);

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Long> suppression(@PathVariable("id") long id) {
        service.suppressionMatch(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping(value = "/scoredomicile2pts/{id}/joueur/{jid}")
    private ResponseEntity<Integer> domicile(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer2Point(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/scoreext2pts/{id}/joueur/{jid}")
    private ResponseEntity<Integer> exterieur(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer2PointExt(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/scoredomicile3pts/{id}/joueur/{jid}")
    private ResponseEntity<Integer> domicile3(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer3Point(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/scoreext3pts/{id}/joueur/{jid}")
    private ResponseEntity<Integer> ext3(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer3PointExt(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/scoredomicile1pts/{id}/joueur/{jid}")
    private ResponseEntity<Integer> domicileLF(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer1Point(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/scoreext1pts/{id}/joueur/{jid}")
    private ResponseEntity<Integer> extLF(@PathVariable("id") long id, @PathVariable("jid") long jid) {
        int totalDTO = service.marquer1PointExt(id, jid);
        return ResponseEntity.status(HttpStatus.OK).body(totalDTO);
    }

    @GetMapping(value = "/team/{matchId}")
    private ResponseEntity<List<JoueurDTO>> joueurDom(@PathVariable("matchId") long matchId) throws MatchException {
        List<JoueurDTO> joueurDTOS = service.getJoueurDomByMatch(matchId);
        return new ResponseEntity<>(joueurDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/equipe/{id}")
    private ResponseEntity<List<MatchDTO>> findByEquipeId(@PathVariable("id") long id) throws EquipeException {
        List<MatchDTO> matchDTOS = service.findAllByEquipeId(id);
        return new ResponseEntity<>(matchDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/team/ext/{matchId}")
    private ResponseEntity<List<JoueurDTO>> joueurExt(@PathVariable("matchId") long matchId) throws MatchException {
        List<JoueurDTO> joueurDTOS = service.getJoueurExtByMatch(matchId);
        return new ResponseEntity<>(joueurDTOS, HttpStatus.OK);
    }

}
