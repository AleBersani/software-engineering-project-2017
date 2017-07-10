package it.polimi.ingsw.server.gamecontroller.helpers;

import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Selects the development cards to set on the gameboard for each period
 * @param <T> Generic Development card
 */

public class DevelopmentCardsFilter<T extends DevelopmentCard> {
    private List<T> developmentCards;

    public DevelopmentCardsFilter(List<T> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public List<T> returnDevelopmentCardsForPeriod(int periodNumber) {
        return developmentCards.stream()
                .filter(c -> c.getBasicDevelopmentCard()
                        .getCardInformation()
                        .getPeriod() == PeriodNumber.values()[periodNumber])
                .collect(Collectors.toList());
    }

    public List<T> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(List<T> developmentCards) {
        this.developmentCards = developmentCards;
    }
}
