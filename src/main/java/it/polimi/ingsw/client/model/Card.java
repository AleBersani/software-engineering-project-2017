package it.polimi.ingsw.client.model;

import java.io.Serializable;

public class Card implements Serializable {
    private String name;

    public Card(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
