package com.ozhegov.tennis.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "Matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private PlayerDTO player1;
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private PlayerDTO player2;
    @ManyToOne
    @JoinColumn(name = "winner_id")
    private PlayerDTO winner;


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
