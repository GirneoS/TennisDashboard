package com.ozhegov.tennis.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "Players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO implements Serializable {
    @Id
    private long id;
    private String name;

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
