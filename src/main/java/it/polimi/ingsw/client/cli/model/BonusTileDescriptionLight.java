package it.polimi.ingsw.client.cli.model;

/**
 * Created by Champ on 23/06/2017.
 */
public class BonusTileDescriptionLight {
    private String bonusTileIdentifier;
    private String description;

    public BonusTileDescriptionLight(String bonusTileIdentifier, String description) {
        this.bonusTileIdentifier = bonusTileIdentifier;
        this.description = description;
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
