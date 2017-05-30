package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
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
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        /*
        TODO
         */
    }

    public List<RequirementsModifier> getRequirementsModifiers() {
        return requirementsModifiers;
    }

    public void setRequirementsModifiers(List<RequirementsModifier> requirementsModifiers) {
        this.requirementsModifiers = requirementsModifiers;
    }
}
