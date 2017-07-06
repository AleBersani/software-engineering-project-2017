package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.cards.development.DevelopmentCard;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;

import java.util.List;
import java.util.stream.Collectors;

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
