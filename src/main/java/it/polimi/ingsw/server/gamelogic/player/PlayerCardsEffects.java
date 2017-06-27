package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.CardFlashAction;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: JavaDoc
 */
public class PlayerCardsEffects {
    private CardFlashAction cardFlashAction;
    private int playerOrderWeight;
    private List<RequirementsModifier> requirementsModifiers;
    private List<RewardsModifier> rewardsModifiers;

    public PlayerCardsEffects() {
        cardFlashAction = null;
        playerOrderWeight = 0;
        requirementsModifiers = new ArrayList<>();
        rewardsModifiers = new ArrayList<>();
    }

    public void addRequirementsModifier(RequirementsModifier requirementsModifier) {
        requirementsModifiers.add(requirementsModifier);
    }

    public void addRewardsModifier(RewardsModifier rewardsModifier) {
        rewardsModifiers.add(rewardsModifier);
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    public Optional<CardFlashAction> getCardFlashAction() {
        if (cardFlashAction == null) {
            return Optional.empty();
        }
        return Optional.of(cardFlashAction);
    }

    public void setCardFlashAction(CardFlashAction cardFlashAction) {
        this.cardFlashAction = cardFlashAction;
    }

    public int getPlayerOrderWeight() {
        return playerOrderWeight;
    }

    public void setPlayerOrderWeight(int playerOrderWeight) {
        this.playerOrderWeight = playerOrderWeight;
    }

    public List<RequirementsModifier> getRequirementsModifiers() {
        return requirementsModifiers;
    }

    public void setRequirementsModifiers(List<RequirementsModifier> requirementsModifiers) {
        this.requirementsModifiers = requirementsModifiers;
    }

    public List<RewardsModifier> getRewardsModifiers() {
        return rewardsModifiers;
    }

    public void setRewardsModifiers(List<RewardsModifier> rewardsModifiers) {
        this.rewardsModifiers = rewardsModifiers;
    }
}
