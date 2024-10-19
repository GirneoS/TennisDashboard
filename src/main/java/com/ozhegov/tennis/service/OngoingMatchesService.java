package com.ozhegov.tennis.service;

import com.ozhegov.tennis.model.Match;

import java.util.*;

public class OngoingMatchesService {
    private static Map<UUID,Match> ongoingMatches = new HashMap<>();

    public Match get(UUID uuid){
        return ongoingMatches.get(uuid);
    }
    public void addMatch(UUID uuid, Match match){
        ongoingMatches.put(uuid, match);
    }
    public void removeMatch(UUID uuid){
        ongoingMatches.remove(uuid);
    }
    public boolean containsMatch(UUID uuid){return ongoingMatches.containsKey(uuid);}
}
