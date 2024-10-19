package com.ozhegov.tennis.service;

import com.ozhegov.tennis.model.Match;
import com.ozhegov.tennis.model.MatchDTO;
import com.ozhegov.tennis.model.Player;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final Match match;
    private final UUID uuid;

    public MatchScoreCalculationService(Match match, UUID uuid) {
        this.match = match;
        this.uuid = uuid;
    }
    /*
* Метод, в котором находится логика подсчета очков.
* выносим все переменные счета матча,
*
* */
    /*
    * Теннис играется до 3-х сетов. Каждый сет состоит из игры до 7-ми геймов при перевесе в 2 гейма.
    * Если получается так, что счет в геймах становится 6:6, то идет тай брейк(т е "укороченный" гейм,
    * в котором счет идет 1,2,3,...). Игрок выигравший тай брейк забирает сет.
    *
    * Обычный гейм идет до 4-х очков(15,30,40,win) при перевесе в 2 очка. Если перевес не достигается(
    * счет становится 40:40), то играется больше-меньше.
    *
    * Тай-брейк идет до 7-ми очков(нормальный подсчет) при перевесе в 2 очка. Если перевес не достигается(6:6),
    * то играется больше-меньше.
    * */

    public void addPoint(long playerId){
        if(match.isTieBreak()) {//проверили матч на тайбрейк
            tieBreak(playerId);
            System.out.println("this match is playing tie-break");
        }else if(match.getPoints1()==40 && match.getPoints2()==40)//проверили не идет ли больше-меньше
            deuce(match, playerId);
        else{//нашли нужного бойца и начислили ему очков
            if(match.getPlayer1().getId()==playerId){
                addPointToPlayer(1);
            }else{
                addPointToPlayer(2);
            }
        }
        int games1 = match.getGames1();
        int games2 = match.getGames2();

        if(games1==7 && games2<=5 || games2==7 && games1<=5){
            if(games1==7)
                match.setSets1(match.getSets1()+1);
            else
                match.setSets2(match.getSets2()+1);
            resetPointsAndGames();
        }

        if(match.getSets1()==2 || match.getSets2()==2)
            finishMatch(match);
    }

    private void addPointToPlayer(int playerNum){
        int points1 = match.getPoints1();
        int points2 = match.getPoints2();
        int games1 = match.getGames1();
        int games2 = match.getGames2();

        if (playerNum==1){
            if(points1<30)
                match.setPoints1(points1+15);
            else if(points1==30)
                match.setPoints1(points1+10);
            else if(points1==40) {
                match.setGames1(games1 + 1);
                resetPoints();
            }
        }else if (playerNum==2){
            if(points2<30)
                match.setPoints2(points2+15);
            else if(points2==30)
                match.setPoints2(points2+10);
            else if(points2==40) {
                match.setGames2(games2 + 1);
                resetPoints();
            }
        }
        if(match.getGames1()==6 && match.getGames2()==6)
            match.setTieBreak(true);
    }


    /*
     *Ситуация больше-меньше
     *Если advantageId обнулен, то счет равный. Ставим переданному игроку больше.
     *Если переданный игрок - это игрок, у которого больше, то обнуляем пойнты и инкрементируем нужный счетчик геймов.
     *Если advantageId определен и забил не переданный игрок - обнуляем advantageId
     * */
    private void deuce(Match match, long playerId){
        long advantageId = match.getAdvantagePlayerId();
        int games1 = match.getGames1();
        int games2 = match.getGames2();

        if(advantageId==0)
            match.setAdvantagePlayerId(playerId);
        else if(advantageId==playerId){
            resetPoints();
            if (match.getPlayer1().getId()==playerId)
                match.setGames1(games1 + 1);
            else
                match.setGames2(games2 + 1);
            match.setAdvantagePlayerId(0);
        }else
            match.setAdvantagePlayerId(0);


    }
    /*
     * method tieBreak
     *
     */

    private void tieBreak(long playerId){
        int points1 = match.getPoints1();
        int points2 = match.getPoints2();
        int sets1 = match.getSets1();
        int sets2 = match.getSets2();

        if(points1==6 && points2==6) {
            deuce(match, playerId);
            if(match.getGames1()==7 || match.getGames2()==7){
                if (match.getGames1()==7)
                    match.setSets1(sets1+1);
                else
                    match.setSets2(sets2+1);

                resetPointsAndGames();
                if(match.getSets1()==2 || match.getSets2()==2)
                    finishMatch(match);
            }
        }else{
            if(match.getPlayer1().getId()==playerId)
                match.setPoints1(points1+1);
            else
                match.setPoints2(points2+1);

            points1 = match.getPoints1();
            points2 = match.getPoints2();
            if(points1==7 && points2<=5 || points2==7 && points1<=5){
                if(points1==7)
                    match.setSets1(sets1+1);
                if(points2==7)
                    match.setSets2(sets2+1);

                resetPointsAndGames();
                if(match.getSets1()==2 || match.getSets2()==2)
                    finishMatch(match);
            }
        }

    }
    private void resetPoints(){
        match.setPoints1(0);
        match.setPoints2(0);
    }
    private void resetPointsAndGames(){
        resetPoints();
        match.setGames1(0);
        match.setGames2(0);
        match.setTieBreak(false);
    }
    /*
    * Метод, в котором определяется победитель, создается dto и загружается в бд
    * */
    private void finishMatch(Match match){
        Player winner;
        if(match.getSets1()==2)
            winner = match.getPlayer1();
        else
            winner = match.getPlayer2();

        MatchDTO dto = new MatchDTO(match.getPlayer1(), match.getPlayer2(), winner);

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
        FinishedMatchesPersistenceService finishedMatchesService = new FinishedMatchesPersistenceService();
        ongoingMatchesService.removeMatch(uuid);
        finishedMatchesService.create(dto);
    }
}
