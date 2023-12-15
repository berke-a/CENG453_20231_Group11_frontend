package com.example.ceng453_20231_group11_frontend.models;

import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class Dice {

    public Integer roll() {
        Random random = new Random();
        Integer die1 = random.nextInt(6) + 1;
        Integer die2 = random.nextInt(6) + 1;
        return die1 + die2;
    }
}
