package it.polimi.ingsw.gamelogic.modifiers.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.ActionType;

/**
 * Class that describes the basic type of rewards
 */
public class BasicRewards {
    private ActionType actionType;
    private Goods rewards;

    private Goods additionalRewards;
    private Goods rewardsCopy;
    private Goods bonusAndMalus;

    public BasicRewards(ActionType actionType, Goods rewards) {
        this.actionType = actionType;
        this.rewards = rewards;
        additionalRewards = new Goods();
        rewardsCopy = rewards;
        bonusAndMalus = new Goods();
    }

    public BasicRewards(ActionType actionType, Goods rewards, Goods additionalRewards) {
        this.actionType = actionType;
        this.rewards = rewards;
        this.additionalRewards = additionalRewards;
        rewardsCopy = rewards;
        bonusAndMalus = new Goods();
    }

    /**
     * Calculates the rewards at the end of an action
     * @return Goods as rewards
     */
    public Goods calculateFinalRewards() {
        Goods goods = rewards;
        goods.addAll(bonusAndMalus);
        goods.addAll(additionalRewards);
        return goods;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Goods getRewards() {
        return rewards;
    }

    public void setRewards(Goods rewards) {
        this.rewards = rewards;
    }

    public Goods getAdditionalRewards() {
        return additionalRewards;
    }

    public void setAdditionalRewards(Goods additionalRewards) {
        this.additionalRewards = additionalRewards;
    }

    public Goods getRewardsCopy() {
        return rewardsCopy;
    }

    public void setRewardsCopy(Goods rewardsCopy) {
        this.rewardsCopy = rewardsCopy;
    }

    public Goods getBonusAndMalus() {
        return bonusAndMalus;
    }

    public void setBonusAndMalus(Goods bonusAndMalus) {
        this.bonusAndMalus = bonusAndMalus;
    }
}
