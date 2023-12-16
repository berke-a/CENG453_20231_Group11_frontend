package com.example.ceng453_20231_group11_frontend.models;

import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
public class Dice {
    Integer die1;
    Integer die2;

    Integer diceTotal;

    public void roll() {
        Random random = new Random();
        this.die1 = random.nextInt(6) + 1;
        this.die2 = random.nextInt(6) + 1;
        this.diceTotal = this.die1 + this.die2;
    }

    public String getDie1() {
        return this.die1.toString();
    }

    public String getDie2() {
        return this.die2.toString();
    }

    public String getDiceTotal() {
        return this.diceTotal.toString();
    }
}
