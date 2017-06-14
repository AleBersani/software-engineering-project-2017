package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that describes the requirements modifier of a card
 */
public class RequirementsOnCard extends AdditionalCardInfo {
    private List<RequirementsModifier> requirementsModifiers;

    public RequirementsOnCard(String name, RequirementsModifier requirementsModifier) {
        super(name);
        requirementsModifiers = new ArrayList<>();
        requirementsModifiers.add(requirementsModifier);
    }

    public RequirementsOnCard(String name, List<RequirementsModifier> requirementsModifiers) {
        super(name);
        this.requirementsModifiers = requirementsModifiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        RequirementsOnCard that = (RequirementsOnCard) o;
        return Objects.equals(getRequirementsModifiers(), that.getRequirementsModifiers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRequirementsModifiers());
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        cardVisitor.visitAdditionalCardInfo(this);
    }

    public List<RequirementsModifier> getRequirementsModifiers() {
        return requirementsModifiers;
    }

    public void setRequirementsModifiers(List<RequirementsModifier> requirementsModifiers) {
        this.requirementsModifiers = requirementsModifiers;
    }
}
