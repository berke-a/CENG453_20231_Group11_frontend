package com.example.ceng453_20231_group11_frontend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@Getter
@NoArgsConstructor
public class Dice {
    private Integer die1 = 0;
    private Integer die2 = 0;

    private Integer diceTotal = 0;

    public void roll() {
        Random random = new Random();
        this.die1 = random.nextInt(6) + 1;
        this.die2 = random.nextInt(6) + 1;
        this.diceTotal = this.die1 + this.die2;
    }

    public String getStringDie1() {
        return this.die1.toString();
    }

    public String getStringDie2() {
        return this.die2.toString();
    }

    public String getStringDieTotal() {
        return this.diceTotal.toString();
    }
}
