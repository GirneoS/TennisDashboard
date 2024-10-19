package com.ozhegov.tennis.service;

import com.ozhegov.tennis.dao.MatchDAO;
import com.ozhegov.tennis.model.Match;
import com.ozhegov.tennis.model.MatchDTO;
import com.ozhegov.tennis.model.Player;

import java.util.List;

/*
* Класс, предназначенный для чтения и записи данных о законченных матчах из БД
* */
public class FinishedMatchesPersistenceService {
    public void create(MatchDTO dto){
        MatchDAO dao = new MatchDAO();
        dao.create(dto);
    }
    public List<MatchDTO> getAll(){
        MatchDAO dao = new MatchDAO();

        return dao.getAll();
    }
}
