package it.polimi.ingsw.gamelogic.board;

import it.polimi.ingsw.gamelogic.enums.DiceColor;

/**
 * TODO: JavaDoc
 */
public class Dice {
    private DiceColor diceColor;
    private int value;

    public Dice(DiceColor diceColor, int value) {
        this.diceColor = diceColor;
        this.setValue(value);
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
