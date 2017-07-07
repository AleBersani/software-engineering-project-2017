package it.polimi.ingsw.server.gamelogic.actions.description;

import it.polimi.ingsw.server.gamelogic.actions.ActionVisitor;
import it.polimi.ingsw.shared.model.PawnColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This Class describes an action concerning the activation of an Action Space on the Board.
 * It can be set up by the player using the effect of a Card (Leader or Development).
 */
public class BoardAction implements ActionDescription {
    private BasicAction basicAction;

    private PawnColor pawnColor;
    private int numberOfServants;
    private int positionMultipleCostChosen;
    private List<Integer> positionExchangingGoodsChosen;

    public BoardAction(BasicAction basicAction) {
        this.basicAction = basicAction;
        pawnColor = PawnColor.UNCOLORED;
        numberOfServants = 0;
        positionMultipleCostChosen = 0;
        positionExchangingGoodsChosen = new ArrayList<>();
    }

    public BoardAction(BasicAction basicAction, PawnColor pawnColor) {
        this.basicAction = basicAction;
        this.pawnColor = pawnColor;
        numberOfServants = 0;
        positionMultipleCostChosen = 0;
        positionExchangingGoodsChosen = new ArrayList<>();
    }

    public BoardAction(BasicAction basicAction, int numberOfServants) {
        this.basicAction = basicAction;
        this.numberOfServants = numberOfServants;
        pawnColor = PawnColor.UNCOLORED;
        positionMultipleCostChosen = 0;
        positionExchangingGoodsChosen = new ArrayList<>();
    }

    public BoardAction(BasicAction basicAction, PawnColor pawnColor, int numberOfServants) {
        this.basicAction = basicAction;
        this.pawnColor = pawnColor;
        this.numberOfServants = numberOfServants;
        positionMultipleCostChosen = 0;
        positionExchangingGoodsChosen = new ArrayList<>();
    }

    public BoardAction(BasicAction basicAction, PawnColor pawnColor,
                       int numberOfServants, int positionMultipleCostChosen) {
        this.basicAction = basicAction;
        this.pawnColor = pawnColor;
        this.numberOfServants = numberOfServants;
        this.positionMultipleCostChosen = positionMultipleCostChosen;
        positionExchangingGoodsChosen = new ArrayList<>();
    }

    public BoardAction(BasicAction basicAction, PawnColor pawnColor, int numberOfServants,
                       int positionMultipleCostChosen, List<Integer> positionExchangingGoodsChosen) {
        this.basicAction = basicAction;
        this.pawnColor = pawnColor;
        this.numberOfServants = numberOfServants;
        this.positionMultipleCostChosen = positionMultipleCostChosen;
        this.positionExchangingGoodsChosen = positionExchangingGoodsChosen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BoardAction that = (BoardAction) o;
        return getNumberOfServants() == that.getNumberOfServants() &&
                Objects.equals(getBasicAction(), that.getBasicAction()) &&
                getPawnColor() == that.getPawnColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBasicAction(), getPawnColor(), getNumberOfServants());
    }

    @Override
    public void acceptActionVisitor(ActionVisitor actionVisitor) {
        actionVisitor.visitActionDescription(this);
    }

    public BasicAction getBasicAction() {
        return basicAction;
    }

    public void setBasicAction(BasicAction basicAction) {
        this.basicAction = basicAction;
    }

    public PawnColor getPawnColor() {
        return pawnColor;
    }

    public void setPawnColor(PawnColor pawnColor) {
        this.pawnColor = pawnColor;
    }

    public int getNumberOfServants() {
        return numberOfServants;
    }

    public void setNumberOfServants(int numberOfServants) {
        this.numberOfServants = numberOfServants;
    }

    public int getPositionMultipleCostChosen() {
        return positionMultipleCostChosen;
    }

    public void setPositionMultipleCostChosen(int positionMultipleCostChosen) {
        this.positionMultipleCostChosen = positionMultipleCostChosen;
    }

    public List<Integer> getPositionExchangingGoodsChosen() {
        return positionExchangingGoodsChosen;
    }

    public void setPositionExchangingGoodsChosen(List<Integer> positionExchangingGoodsChosen) {
        this.positionExchangingGoodsChosen = positionExchangingGoodsChosen;
    }
}
