package com.ozhegov.tennis.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
public class Match implements Serializable {
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.sets1=0;
        this.games1=0;
        this.points1=0;
        this.sets2=0;
        this.games2=0;
        this.points2=0;
        this.advantagePlayerId=0;
    }

    private Long id;

    private Player player1;
    private Player player2;
    private Player winner;

    private int sets1;
    private int games1;
    private int points1;
    private int sets2;
    private int games2;
    private int points2;
    private long advantagePlayerId;
    private boolean tieBreak;

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", winner='" + winner + '\'' +
                ", points1=" + points1 + '\t' +
                ", points2=" + points2 +
                '}';
    }
}
