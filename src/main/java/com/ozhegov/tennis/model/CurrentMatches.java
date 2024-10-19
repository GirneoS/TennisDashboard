package com.ozhegov.tennis.model;

import com.ozhegov.tennis.model.Match;
import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class CurrentMatches {
    private static HashMap<UUID, Match> currentMatch = new HashMap<>();
    private static HashMap<Match,Long> advantageMatches = new HashMap<>();

    public static void putMatch(UUID uuid, Match match){
        currentMatch.put(uuid, match);
    }
    public static Match getMatchDTO(UUID uuid){
        return currentMatch.get(uuid);
    }
    public static void putAvantageMatch(Match match, long id){advantageMatches.put(match,id);}
    public static long getAdvantagePlayer(Match match){
        return advantageMatches.get(match);
    }
}
