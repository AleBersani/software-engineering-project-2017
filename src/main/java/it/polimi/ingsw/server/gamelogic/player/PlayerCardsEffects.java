package it.polimi.ingsw.server.gamelogic.player;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.CardFlashAction;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PlayerCardsEffects {
    private CardFlashAction cardFlashAction;
    private Goods churchSustainBonus;
    private int playerOrderWeight;
    private List<RequirementsModifier> requirementsModifiers;
    private List<RewardsModifier> rewardsModifiers;

    public PlayerCardsEffects() {
        cardFlashAction = null;
        churchSustainBonus = new Goods();
        playerOrderWeight = 0;
        requirementsModifiers = new ArrayList<>();
        rewardsModifiers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerCardsEffects that = (PlayerCardsEffects) o;
        return getPlayerOrderWeight() == that.getPlayerOrderWeight() &&
                Objects.equals(getCardFlashAction(), that.getCardFlashAction()) &&
                Objects.equals(getRequirementsModifiers(), that.getRequirementsModifiers()) &&
                Objects.equals(getRewardsModifiers(), that.getRewardsModifiers()) &&
                Objects.equals(getChurchSustainBonus(), that.getChurchSustainBonus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardFlashAction(), getPlayerOrderWeight(), getRequirementsModifiers(), getRewardsModifiers());
    }

    public void addRequirementsModifier(RequirementsModifier requirementsModifier) {
        requirementsModifiers.add(requirementsModifier);
    }

    public void addRewardsModifier(RewardsModifier rewardsModifier) {
        rewardsModifiers.add(rewardsModifier);
    }
    
    public Optional<CardFlashAction> getCardFlashAction() {
        if (cardFlashAction == null) {
            return Optional.empty();
        }
        return Optional.of(cardFlashAction);
    }

    public void setCardFlashAction(CardFlashAction cardFlashAction) {
        this.cardFlashAction = cardFlashAction;
    }

    public Goods getChurchSustainBonus() {
        return churchSustainBonus;
    }

    public void setChurchSustainBonus(Goods churchSustainBonus) {
        this.churchSustainBonus = churchSustainBonus;
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
