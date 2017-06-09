package it.polimi.ingsw.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.gamelogic.basics.CardsRequired;
import it.polimi.ingsw.gamelogic.basics.Goods;
import it.polimi.ingsw.gamelogic.basics.Points;
import it.polimi.ingsw.gamelogic.basics.Resources;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testParseCardsRequiredList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseCardsRequiredList";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"territories\": \"3\",\"buildings\": \"4\",\"characters\": \"0\",\"ventures\": \"5\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<CardsRequired> resultExpected = new ArrayList<>();
        resultExpected.add(new CardsRequired(3, GeneralColor.GREEN));
        resultExpected.add(new CardsRequired(4, GeneralColor.YELLOW));
        resultExpected.add(new CardsRequired(5, GeneralColor.PURPLE));
        List<CardsRequired> result = (List<CardsRequired>) method.invoke(parserCards, obj);
        for (int index = 0; index<resultExpected.size(); index++) {
            assertTrue((resultExpected.get(index).getNumberOfCardsRequired()==result.get(index).
                                    getNumberOfCardsRequired())
            && (resultExpected.get(index).getCardColorRequired().equals(result.get(index).getCardColorRequired())));
        }
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