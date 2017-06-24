package it.polimi.ingsw.client.cli.model;

import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;

import java.util.Objects;

public class BonusTileDescriptionLight {
    private String bonusTileIdentifier;
    private String description;

    public BonusTileDescriptionLight(String bonusTileIdentifier, String description) {
        this.bonusTileIdentifier = bonusTileIdentifier;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BonusTileDescriptionLight that = (BonusTileDescriptionLight) o;
        return Objects.equals(getBonusTileIdentifier(), that.getBonusTileIdentifier()) &&
               Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBonusTileIdentifier(), getDescription());
    }

    public String getBonusTileIdentifier() {
        return bonusTileIdentifier;
    }

    public void setBonusTileIdentifier(String bonusTileIdentifier) {
        this.bonusTileIdentifier = bonusTileIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
