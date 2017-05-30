package it.polimi.ingsw.gamelogic.cards.additionalinfo;

import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.decorators.requirements.decorators.RequirementsModifier;

/**
 * TODO: JavaDoc
 */
public class RequirementsOnCard extends AdditionalCardInfo {
    private RequirementsModifier requirementsModifier;

    public RequirementsOnCard(String name, RequirementsModifier requirementsModifier) {
        super(name);
        this.requirementsModifier = requirementsModifier;
    }

    @Override
    public void acceptCardVisitor(CardVisitor cardVisitor) {
        /*
        TODO
         */
    }

    public RequirementsModifier getRequirementsModifier() {
        return requirementsModifier;
    }

    public void setRequirementsModifier(RequirementsModifier requirementsModifier) {
        this.requirementsModifier = requirementsModifier;
    }
}
