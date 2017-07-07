package it.polimi.ingsw.client.model;

import it.polimi.ingsw.shared.model.DiceColor;

import java.io.Serializable;

public class DiceLight implements Serializable {
    private DiceColor diceColor;
    private int value;

    public DiceLight(DiceColor diceColor, int value) {
        this.diceColor = diceColor;
        this.value = value;
    }

    public DiceColor getDiceColor() {
        return diceColor;
    }

    public void setDiceColor(DiceColor diceColor) {
        this.diceColor = diceColor;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
