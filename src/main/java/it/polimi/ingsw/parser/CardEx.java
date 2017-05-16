package it.polimi.ingsw.parser;

public class CardEx {
    private String name;
    private int period;
    private String color;

    public CardEx(String name, int period, String color) {
        this.name = name;
        this.period = period;
        this.color = color;
    }

    public String toString() {
        return name + " - " + period + " - " + color;
    }
}
