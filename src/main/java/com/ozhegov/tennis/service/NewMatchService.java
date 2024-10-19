package com.ozhegov.tennis.service;

import com.ozhegov.tennis.dao.PlayerDAO;
import com.ozhegov.tennis.model.CurrentMatches;
import com.ozhegov.tennis.model.Match;
import com.ozhegov.tennis.model.Player;

import java.util.UUID;

public class NewMatchService {
    public UUID startMatch(String playerName1, String playerName2){

        PlayerDAO dao = new PlayerDAO();

        Player player1 = dao.getByName(playerName1);
        Player player2 = dao.getByName(playerName2);

        Match match = new Match(player1,player2);

        UUID uuid = UUID.randomUUID();

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        ongoingMatchesService.addMatch(uuid,match);

        return uuid;
    }
}
