package it.polimi.ingsw.gamelogic.cards.developmentcards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.FlashEffect;

import java.util.List;

public class DevelopmentCard {
    private CardInformation cardInformation;
    private List<Goods> costs;
    private FlashEffect instantEffect;

    public DevelopmentCard(CardInformation cardInformation, List<Goods> costs, FlashEffect instantEffect) {
        this.cardInformation = cardInformation;
        this.costs = costs;
        this.instantEffect = instantEffect;
    }

    /**
     * Add only one cost to the list
     * @param costToAdd cost added to the list of costs
     */
    public void addCost(Goods costToAdd) {
        costs.add(costToAdd);
    }

    public CardInformation getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(CardInformation cardInformation) {
        this.cardInformation = cardInformation;
    }

    public List<Goods> getCosts() {
        return costs;
    }

    public void setCosts(List<Goods> costs) {
        this.costs = costs;
    }

    public FlashEffect getInstantEffect() {
        return instantEffect;
    }

    public void setInstantEffect(FlashEffect instantEffect) {
        this.instantEffect = instantEffect;
    }
}
