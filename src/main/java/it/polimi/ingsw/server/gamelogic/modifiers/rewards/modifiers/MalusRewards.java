package it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers;

import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;

import java.util.Objects;

/**
 * CLass that describes an effect that reduces the Player's Goods
 */
public class MalusRewards extends RewardsModifier {
    private Goods malus;

    public MalusRewards(AvailableActions availableActions, Goods malus) {
        super(availableActions);
        this.malus = malus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        MalusRewards that = (MalusRewards) o;
        return Objects.equals(malus, that.malus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), malus);
    }

    @Override
    public void modifyRewards(BasicRewards basicRewards) {
        if (availableActions.hasAvailableAction(basicRewards.getActionType())) {
            Goods bonusAndMalus = basicRewards.getBonusAndMalus();
            bonusAndMalus.subtractAll(malus);
            basicRewards.setBonusAndMalus(bonusAndMalus);

            Goods additionalRewards = basicRewards.getAdditionalRewards();
            if (malus.isLessThan(additionalRewards)) {
                additionalRewards.subtractAll(malus);
                basicRewards.setAdditionalRewards(additionalRewards);
            }
        }
    }

    public Goods getMalus() {
        return malus;
    }

    public void setMalus(Goods malus) {
        this.malus = malus;
    }
}
