package it.polimi.ingsw.gamelogic.cards.development;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TODO: JavaDoc
 */
public class Building implements Collectible {
    private DevelopmentCard developmentCard;
    private ExchangingGoods productionResult;
    private AdditionalCardInfo additionalCardInfo;

    /**
     * Optional example
     * @param argv default list of argument for main methods
     */
    public static void main(String argv[]) {
        Building building = new Building(
                new DevelopmentCard(new CardInformation(1,
                        "Name",
                        PeriodNumber.FIRST,
                        GeneralColor.YELLOW), new ArrayList<>(), new ExchangingGoods()));

        AdditionalCardInfo additionalCardInfo;
        building.getAdditionalCardInfo()
                .ifPresent(consumer -> System.out.println(consumer.getName()));
    }

    public Building(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
        productionResult = new ExchangingGoods();
        additionalCardInfo = null;
    }

    public Building(DevelopmentCard developmentCard, ExchangingGoods productionResult) {
        this.developmentCard = developmentCard;
        this.productionResult = productionResult;
        additionalCardInfo = null;
    }

    public Building(DevelopmentCard developmentCard, ExchangingGoods productionResult, AdditionalCardInfo additionalCardInfo) {
        this.developmentCard = developmentCard;
        this.productionResult = productionResult;
        this.additionalCardInfo = additionalCardInfo;
    }

    @Override
    public CardInformation getCardInformation() {
        return developmentCard.getCardInformation();
    }

    @Override
    public List<Goods> getCosts() {
        return developmentCard.getCosts();
    }

    @Override
    public ExchangingGoods getInstantEffect() {
        return developmentCard.getInstantExchangingGoods();
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    public ExchangingGoods getProductionResult() {
        return productionResult;
    }

    public void setProductionResult(ExchangingGoods productionResult) {
        this.productionResult = productionResult;
    }

    /**
     * TODO: JavaDoc
     * @return
     */
    public Optional<AdditionalCardInfo> getAdditionalCardInfo() {
        if (additionalCardInfo == null)
            return Optional.empty();
        return Optional.of(additionalCardInfo);
    }

    public void setAdditionalCardInfo(AdditionalCardInfo additionalCardInfo) {
        this.additionalCardInfo = additionalCardInfo;
    }
}
