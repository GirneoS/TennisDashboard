package com.ozhegov.tennis.model.dto;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class CurrentMatches {
    private static HashMap<UUID, Match> currentMatch = new HashMap<>();

    public static void putMatch(UUID uuid, Match match){
        currentMatch.put(uuid, match);
    }
    public static Match getMatchDTO(UUID uuid){
        return currentMatch.get(uuid);
    }
}
