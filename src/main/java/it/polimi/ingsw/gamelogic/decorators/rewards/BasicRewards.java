package it.polimi.ingsw.gamelogic.decorators.rewards;

import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.enums.ActionType;

/**
 * TODO: JavaDoc
 */
public class BasicRewards {
    private ActionType actionType;
    private Goods rewards;
    private Goods additionalRewards;

    private Goods rewardsCopy;
    private Goods bonusAndMalus;

    public BasicRewards(ActionType actionType, Goods rewards, Goods additionalRewards) {
        this.actionType = actionType;
        this.rewards = rewards;
        this.additionalRewards = additionalRewards;
        rewardsCopy = rewards;
        bonusAndMalus = new Goods();
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    public Goods calculateFinalRewards() {
        Goods goods = getRewards();
        goods.addAll(bonusAndMalus);
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
