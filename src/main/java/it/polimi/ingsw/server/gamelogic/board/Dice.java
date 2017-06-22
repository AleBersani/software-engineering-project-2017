package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.server.gamelogic.enums.DiceColor;

/**
 * Class that describes the dices thrown every semiperiod, they define the value of the players' pawns
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
