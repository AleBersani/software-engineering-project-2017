package it.polimi.ingsw.gamelogic.cards;

import it.polimi.ingsw.gamelogic.basics.CardCost;
import it.polimi.ingsw.gamelogic.basics.FlashEffect;

public class FlashEffectLeaderCard extends LeaderCard {
    private FlashEffect flashEffect;

    public FlashEffectLeaderCard(String name, CardCost cardCost, FlashEffect flashEffect) {
        super(name, cardCost);
        this.flashEffect = flashEffect;
    }

    /*
    TODO: methods
     */

    public FlashEffect getFlashEffect() {
        return flashEffect;
    }

    public void setFlashEffect(FlashEffect flashEffect) {
        this.flashEffect = flashEffect;
    }
}
