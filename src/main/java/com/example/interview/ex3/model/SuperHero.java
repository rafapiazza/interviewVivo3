package com.example.interview.ex3.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class SuperHero {
    private String code;
    private String name;
    private ArrayList<Lap> laps;

    private int totalTime = 0;

    public SuperHero(String name, String code) {
        this.name = name;
        this.code = code;
        this.laps = new ArrayList<>();
    }
}
