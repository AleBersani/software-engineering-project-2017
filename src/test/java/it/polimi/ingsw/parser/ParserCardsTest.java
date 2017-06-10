package it.polimi.ingsw.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.gamelogic.basics.*;
import it.polimi.ingsw.gamelogic.cards.development.BasicDevelopmentCard;
import it.polimi.ingsw.gamelogic.cards.development.CardInformation;
import it.polimi.ingsw.gamelogic.cards.development.Venture;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.smartcardio.Card;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParserCardsTest {
    private ParserCards parserCards;

    @BeforeEach
    void setUp() {
        parserCards = new ParserCards();
    }



    @Test
    void testParseGoods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseGoods";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"resources\": {\"woods\": \"1\",\"stones\": \"3\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"1\",\"faith\": \"5\"}}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Goods resultExpected = new Goods(new Resources(1, 3, 0, 0),
                new Points(0, 1, 5));
        Goods result = (Goods) method.invoke(parserCards, obj);
        assertEquals(resultExpected, result);
    }
}