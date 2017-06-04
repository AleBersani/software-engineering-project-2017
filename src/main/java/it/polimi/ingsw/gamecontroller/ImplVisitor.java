package it.polimi.ingsw.gamecontroller;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.*;

import java.util.ArrayList;
import java.util.List;

public class ImplVisitor implements CardVisitor {
    public static void main(String argv[]) {
        List<AdditionalCardInfo> additionalCardInfoList;
        ImplVisitor implVisitor;

        AdditionalCardInfo one = new CardFlashExchangingGoods("Card 1", new ExchangingGoods(
                new Goods(new Points(1,2,3))));
        AdditionalCardInfo two = new MinRequiredOnCost("Card 2",
                new Goods(new Points(1,2,3)));

        additionalCardInfoList = new ArrayList<>();
        additionalCardInfoList.add(one);
        additionalCardInfoList.add(two);

        implVisitor = new ImplVisitor();

        additionalCardInfoList.get(0).acceptCardVisitor(implVisitor);
        additionalCardInfoList.get(1).acceptCardVisitor(implVisitor);
    }

    @Override
    public void visitAdditionalCardInfo(CardFlashExchangingGoods cardFlashExchangingGoods) {
        System.out.println("Visitor CardFlashExchangingGoods");
        System.out.println(cardFlashExchangingGoods.getName() + " " + cardFlashExchangingGoods
                .getExchangingGoods().getGoods().toString());
    }

    @Override
    public void visitAdditionalCardInfo(MinRequiredOnCost minRequiredOnCost) {
        System.out.println("Visitor MinRequiredOnCost");
        System.out.println(minRequiredOnCost.getName() + " " + minRequiredOnCost.getMin().toString());
    }

    @Override
    public void visitAdditionalCardInfo(CardFlashAction cardFlashAction) {
        System.out.println("Visitor CardFlashAction");
    }

    @Override
    public void visitAdditionalCardInfo(ConditionalProduction conditionalProduction) {
        System.out.println("Visitor ConditionalProduction");
    }

    @Override
    public void visitAdditionalCardInfo(GoodsBasedOnPossessions goodsBasedOnPossessions) {
        System.out.println("Visitor GoodsBasedOnPossessions");
    }

    @Override
    public void visitAdditionalCardInfo(MultipleProduction multipleProduction) {
        System.out.println("Visitor MultipleProduction");
    }

    @Override
    public void visitAdditionalCardInfo(RequirementsOnCard requirementsOnCard) {
        System.out.println("Visitor RequirementsOnCard");
    }

    @Override
    public void visitAdditionalCardInfo(RewardsOnCard rewardsOnCard) {
        System.out.println("Visitor RewardsOnCard");
    }
}
