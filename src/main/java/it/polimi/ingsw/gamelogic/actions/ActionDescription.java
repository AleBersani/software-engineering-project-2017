package it.polimi.ingsw.gamelogic.actions;

/**
 * Abstract Class representing the most basic information of an Action.
 * In order to determine the right action additional information are needed, which is why it's declared as abstract.
 */
public abstract class ActionDescription {
    protected String actionIdentifier;

    public ActionDescription() {
        actionIdentifier = "";
    }

    public ActionDescription(String actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
    }

    /**
     * Methods to pick out the combination of all String object that identify the action
     * @return actionIdentifier
     */
    public abstract String getConcatenatedActionIdentifier();

    public String getActionIdentifier() {
        return actionIdentifier;
    }

    public void setActionIdentifier(String actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
    }
}
