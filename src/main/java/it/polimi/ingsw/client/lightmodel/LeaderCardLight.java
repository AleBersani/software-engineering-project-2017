package it.polimi.ingsw.client.lightmodel;

import java.util.List;
import java.util.Objects;

public class LeaderCardLight {
    private String name;
    private String effectDescription;
    private List<String> requirements;

    public LeaderCardLight(String name, String effectDescription, List<String> requirements) {
        this.name = name;
        this.effectDescription = effectDescription;
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LeaderCardLight that = (LeaderCardLight) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getEffectDescription(), that.getEffectDescription()) &&
                Objects.equals(getRequirements(), that.getRequirements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEffectDescription(), getRequirements());
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
