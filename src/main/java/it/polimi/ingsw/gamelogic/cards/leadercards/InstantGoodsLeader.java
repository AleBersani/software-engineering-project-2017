package it.polimi.ingsw.gamelogic.cards.leadercards;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.leadercards.common.LeaderCard;

/**
 * Leader Cards having an instant Goods
 */
public class InstantGoodsLeader {
    private LeaderCard leaderCard; // Composite
    private ExchangingGoods instantGoods;

    public InstantGoodsLeader(LeaderCard leaderCard, ExchangingGoods instantGoods) {
        this.leaderCard = leaderCard;
        this.instantGoods = instantGoods;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public void setLeaderCard(LeaderCard leaderCard) {
        this.leaderCard = leaderCard;
    }

    public ExchangingGoods getInstantGoods() {
        return instantGoods;
    }

    public void setInstantGoods(ExchangingGoods instantGoods) {
        this.instantGoods = instantGoods;
    }
}
