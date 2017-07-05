package it.polimi.ingsw.server.gamelogic.cards.development;

import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.GeneralColor;

import java.util.Objects;

/**
 * Class that describes the basic information of each Development card
 */
public class CardInformation {
    private int number;
    private String name;
    private PeriodNumber period;
    private GeneralColor cardColor;

    public CardInformation(int number, String name, PeriodNumber period, GeneralColor cardColor) {
        this.number = number;
        this.name = name;
        this.period = period;
        this.cardColor = cardColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CardInformation that = (CardInformation) o;
        return getNumber() == that.getNumber() &&
                Objects.equals(getName(), that.getName()) &&
                getPeriod() == that.getPeriod() &&
                getCardColor() == that.getCardColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName(), getPeriod(), getCardColor());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PeriodNumber getPeriod() {
        return period;
    }

    public void setPeriod(PeriodNumber period) {
        this.period = period;
    }

    public GeneralColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(GeneralColor cardColor) {
        this.cardColor = cardColor;
    }
}
