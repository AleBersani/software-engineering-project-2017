package it.polimi.ingsw.gamecontroller;

import it.polimi.ingsw.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.cards.CardVisitor;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.AdditionalCardInfo;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.CardFlashExchangingGoods;
import it.polimi.ingsw.gamelogic.cards.additionalinfo.MinRequiredOnCost;

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

        additionalCardInfoList.get(1).acceptCardVisitor(implVisitor);
        additionalCardInfoList.get(1).acceptCardVisitor(implVisitor);
    }

    @Override
    public void visitCardFlashExchangingGoods(CardFlashExchangingGoods cardFlashExchangingGoods) {
        System.out.println("Visitor Card Flash blalbwASGEASDG");
        System.out.println(cardFlashExchangingGoods.getName() + " " + cardFlashExchangingGoods
                .getExchangingGoods().getGoods().toString());
    }

    @Override
    public void visitMinRequiredOnCost(MinRequiredOnCost minRequiredOnCost) {
        System.out.println("Visitor Min Resadfgdsagfves");
        System.out.println(minRequiredOnCost.getName() + " " + minRequiredOnCost.getMin().toString());
    }
}
