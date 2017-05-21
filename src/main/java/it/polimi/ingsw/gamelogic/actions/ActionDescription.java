package it.polimi.ingsw.gamelogic.actions;

/**
 * Class representing the information of a class, with a supplement attribute which describes the additional
 * information of the specific action.
 */
public class ActionDescription<T extends ActionSupplement> {
    private String actionIdentifier;
    private T supplement;

    private ActionDescription() {
        actionIdentifier = "";
        /*
            TODO: instantiate supplement
         */
    }

    public ActionDescription(String actionIdentifier, T supplement) {
        this.actionIdentifier = actionIdentifier;
        this.supplement = supplement;
    }

    public String getActionIdentifier() {
        return actionIdentifier;
    }

    public void setActionIdentifier(String actionIdentifier) {
        this.actionIdentifier = actionIdentifier;
    }

    public T getSupplement() {
        return supplement;
    }

    public void setSupplement(T supplement) {
        this.supplement = supplement;
    }
}
