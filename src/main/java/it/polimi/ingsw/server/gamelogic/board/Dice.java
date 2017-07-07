package it.polimi.ingsw.server.gamelogic.board;

import it.polimi.ingsw.shared.model.DiceColor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Dice dice = (Dice) o;
        return getValue() == dice.getValue() &&
                getDiceColor() == dice.getDiceColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDiceColor(), getValue());
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
