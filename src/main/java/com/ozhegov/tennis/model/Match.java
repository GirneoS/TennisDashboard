package com.ozhegov.tennis.model.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "Matches")
@Getter
@Setter
@NoArgsConstructor
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
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;
    @Transient
    private int sets1;
    @Transient
    private int games1;
    @Transient
    private int points1;
    @Transient
    private int sets2;
    @Transient
    private int games2;
    @Transient
    private int points2;

    @Override
    public String toString() {
        return "MatchDTO{" +
                "id=" + id +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", winner='" + winner + '\'' +
                '}';
    }
}
