package it.polimi.ingsw.client.model;

import java.io.Serializable;

public class Card implements Serializable {
    private String name;
    private int numberOfCouncilPrivilege;

    public Card(String name) {
        this.name = name;
        numberOfCouncilPrivilege = 0;
    }

    public Card(String name, int numberOfCouncilPrivilege) {
        this.name = name;
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", numberOfCouncilPrivilege=" + numberOfCouncilPrivilege +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCouncilPrivilege() {
        return numberOfCouncilPrivilege;
    }

    public void setNumberOfCouncilPrivilege(int numberOfCouncilPrivilege) {
        this.numberOfCouncilPrivilege = numberOfCouncilPrivilege;
    }
}
