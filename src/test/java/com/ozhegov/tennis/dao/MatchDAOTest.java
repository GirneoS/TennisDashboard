package com.ozhegov.tennis.dao;

import com.ozhegov.tennis.model.Match;
import com.ozhegov.tennis.model.MatchDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MatchDAOTest {

    @Test
    void getByPlayerName() {
        String name = "Andrew";

        MatchDAO dao = new MatchDAO();
        List<MatchDTO> list = dao.getByPlayerName(name);

        for(MatchDTO match: list){
            assertTrue(match.getPlayer1().getName().equals(name) ||
                    match.getPlayer2().getName().equals(name));
        }
    }
}