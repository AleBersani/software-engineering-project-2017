package it.polimi.ingsw.client.cli.model;

import java.util.Objects;

public class ExcommunicationTileLight {
    private String name;
    private String effectDescription;

    public ExcommunicationTileLight(String name, String effectDescription) {
        this.name = name;
        this.effectDescription = effectDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ExcommunicationTileLight that = (ExcommunicationTileLight) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getEffectDescription(), that.getEffectDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEffectDescription());
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
