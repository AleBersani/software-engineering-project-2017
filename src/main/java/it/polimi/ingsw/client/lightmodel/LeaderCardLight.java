package it.polimi.ingsw.client.lightmodel;

import java.util.List;

public class LeaderCardLight {
    private String name;
    private String effectDescription;
    private List<String> requirements;

    public LeaderCardLight(String name, String effectDescription, List<String> requirements) {
        this.name = name;
        this.effectDescription = effectDescription;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffectDescription() {
        return effectDescription;
    }

    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }
}
