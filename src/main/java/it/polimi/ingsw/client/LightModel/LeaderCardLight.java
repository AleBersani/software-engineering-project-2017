package it.polimi.ingsw.client.LightModel;

public class LeaderCardLight {
    private String name;
    private String effectDescritption;
    private String requirements;

    public LeaderCardLight(String name, String effectDescritption, String requirements) {
        this.name = name;
        this.effectDescritption = effectDescritption;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffectDescritption() {
        return effectDescritption;
    }

    public void setEffectDescritption(String effectDescritption) {
        this.effectDescritption = effectDescritption;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }
}
