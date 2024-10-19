package com.ozhegov.tennis.service;

import com.ozhegov.tennis.model.Match;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final NewMatchService newMatchService = new NewMatchService();
    private final UUID uuid = newMatchService.startMatch("Egor","Igor");
    Match match = ongoingMatchesService.get(uuid);
    MatchScoreCalculationService calculationService = new MatchScoreCalculationService(match, uuid);


    @Test
    void firstPoint1PlayerWhileDeuceShouldNotFinishGame(){
        var playerId1 = match.getPlayer1().getId();
        var calculator = new MatchScoreCalculationService(match, uuid);

        match.setPoints1(40);
        match.setPoints2(40);
        int games1 = match.getGames1();

        calculator.addPoint(playerId1);

        var afterPointMatch = ongoingMatchesService.get(uuid);
        assertTrue(afterPointMatch.getGames1() == games1);
    }

    @Test
    void firstPoint2PlayerWhileDeuceShouldNotFinishGame(){
        var playerId2 = match.getPlayer2().getId();

        match.setPoints1(40);
        match.setPoints2(40);
        int games2 = match.getGames2();

        calculationService.addPoint(playerId2);

        var afterPointMatch = ongoingMatchesService.get(uuid);
        assertTrue(afterPointMatch.getGames2() == games2);
    }

    @Test
    void point1PlayerAfterFortyWithoutDeuceAndTieBreakShouldFinishGame(){
        var playerId1 = match.getPlayer1().getId();
        match.setPoints1(40);
        match.setPoints2(0);

        int games1 = match.getGames1();
        calculationService.addPoint(playerId1);

        var matchAfterPoint = ongoingMatchesService.get(uuid);
        assertEquals(1, matchAfterPoint.getGames1() - games1);
    }
    @Test
    void point2PlayerAfterFortyWithoutDeuceAndTieBreakShouldFinishGame(){
        var playerId2 = match.getPlayer2().getId();

        match.setPoints2(40);
        match.setPoints1(0);

        int games2 = match.getGames2();
        calculationService.addPoint(playerId2);

        var matchAfterPoint = ongoingMatchesService.get(uuid);
        assertEquals(1, matchAfterPoint.getGames2() - games2);
    }
    @Test
    void startTieBreakWhenGamesEqual6() {
        match.setGames2(5);
        match.setGames1(6);

        match.setPoints2(40);

        calculationService.addPoint(match.getPlayer2().getId());

        assertTrue(match.isTieBreak());
    }

}