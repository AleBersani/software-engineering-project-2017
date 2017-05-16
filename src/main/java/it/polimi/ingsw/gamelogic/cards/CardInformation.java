package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

public class CardInformation {
    private int number;
    private String name;
    private PeriodNumber period;
    private String cardColor;

    public CardInformation(int number, String name, PeriodNumber period, String cardColor) {
        this.number = number;
        this.name = name;
        this.period = period;
        this.cardColor = cardColor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeriodNumber getPeriod() {
        return period;
    }

    public void setPeriod(PeriodNumber period) {
        this.period = period;
    }

    public String getCardColor() {
        return cardColor;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }
}
