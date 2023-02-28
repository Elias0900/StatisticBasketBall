package com.basket.statistics.Service;

import com.basket.statistics.MapperDto.DtoConvertisseur;
import com.basket.statistics.Repo.MatchRepo;
import com.basket.statistics.dto.MatchDTO;
import com.basket.statistics.entities.Match;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MatchImpl implements MatchService{
    @Autowired
    private MatchRepo repo;
    @Override
    public List<MatchDTO> getAll() {
        List<Match> jList = repo.findAll();
        List<MatchDTO> MatchDTOList = new ArrayList<>();
        for (Match j : jList){
            MatchDTOList.add(DtoConvertisseur.convert(j, MatchDTO.class));
        }
        return MatchDTOList;
    }

    @Override
    public MatchDTO saveOrUpdate(MatchDTO j) {
        Match Match = DtoConvertisseur.convert(j, Match.class);
        Match = repo.saveAndFlush(Match);
        return DtoConvertisseur.convert(Match,MatchDTO.class);
    }

    @Override
    public MatchDTO findById(long id) {
        Optional<Match> j = repo.findById(id);
        if (j.isPresent()) {
            return DtoConvertisseur.convert(j, MatchDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void suppressionMatch(long id) {
        repo.deleteById(id);

    }

}

