package it.polimi.ingsw.client.lightmodel;

public class ExcommunicationTileLight {
    private String name;
    private String effectDescription;

    public ExcommunicationTileLight(String name, String effectDescription) {
        this.name = name;
        this.effectDescription = effectDescription;
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
}
