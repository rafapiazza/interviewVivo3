package com.example.interview.ex3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Race {
    //    private Long raceId;
    private List<SuperHero> superHeroes;
}
