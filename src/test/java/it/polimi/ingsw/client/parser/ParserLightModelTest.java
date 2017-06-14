package it.polimi.ingsw.client.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import it.polimi.ingsw.client.lightmodel.DevelopmentCardLight;
import it.polimi.ingsw.client.lightmodel.ExcommunicationTileLight;
import it.polimi.ingsw.client.lightmodel.LeaderCardLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserLightModelTest {
    private ParserLightModel parserLightModel;

    @BeforeEach
    void setUp() {
        parserLightModel = new ParserLightModel();
    }

    @Test
    void testParseListDevelopmentCardLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseListDevelopmentCardLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{"+
                        "\"name\": \"Commercial Hub\","+
                        "\"instantEffectDescription\": \"None\","+
                        "\"permanentEffectDescription\": [\"You get 1 Coin as harvest result with cost of 1\"],"+
                        "\"cost\": [\"None\"]},"+
                        "{"+
                        "\"name\": \"Woods\","+
                        "\"instantEffectDescription\": \"None\","+
                        "\"permanentEffectDescription\": [\"You get 1 Wood as harvest result with cost of 2\"],"+
                        "\"cost\": [\"None\"]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<String> cost1 = new ArrayList<String>(){{add("None");}};
        List<String> permanentEffect1 = new ArrayList<String>(){{add("You get 1 Coin as harvest result with cost of 1");}};
        List<String> permanentEffect2 = new ArrayList<String>(){{add("You get 1 Wood as harvest result with cost of 2");}};
        List<DevelopmentCardLight> resultExpected = new ArrayList<DevelopmentCardLight>(){{
                        add(new DevelopmentCardLight("Commercial Hub",
                                                            cost1,
                                        "None",
                                                            permanentEffect1));
                        add(new DevelopmentCardLight("Woods",
                                                            cost1,
                                        "None",
                                                            permanentEffect2));}};
        List<DevelopmentCardLight> result = (List<DevelopmentCardLight>) method.invoke(parserLightModel, obj);
        for (int i=0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i=0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseListLeaderCardLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseListLeaderCardLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{"+
                "\"name\": \"Commercial Hub\","+
                "\"effectDescription\": \"None\","+
                "\"requirements\": [\"You get 1 Coin as harvest result with cost of 1\"]},"+
                "{"+
                "\"name\": \"Woods\","+
                "\"effectDescription\": \"None\","+
                "\"requirements\": [\"You get 1 Wood as harvest result with cost of 2\"]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<String> requirements1 = new ArrayList<String>(){{add("You get 1 Coin as harvest result with cost of 1");}};
        List<String> requirements2 = new ArrayList<String>(){{add("You get 1 Wood as harvest result with cost of 2");}};
        List<LeaderCardLight> resultExpected = new ArrayList<LeaderCardLight>(){{
            add(new LeaderCardLight("Commercial Hub",
                    "None",
                    requirements1));
            add(new LeaderCardLight("Woods",
                    "None",
                    requirements2));}};
        List<LeaderCardLight> result = (List<LeaderCardLight>) method.invoke(parserLightModel, obj);
        for (int i=0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i=0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseListExcommunicationTileLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseListExcommunicationTileLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{"+
                "\"name\": \"1.1\","+
                "\"effectDescription\": \"Each time you gain Military points (from action spaces or from your cards), " +
                                        "gain 1 fewer Military point. (If you have more cards that give you Military " +
                                        "points, consider each card a single source, so you gain -1 Military point " +
                                        "for each card.)\""+
                "},"+
                "{"+
                "\"name\": \"1.2\","+
                "\"effectDescription\": \"Each time you receive coins (from action spaces or from your Cards), " +
                                        "you receive 1 fewer coin. (If you have more Cards that give you coins, " +
                                        "consider each Card a single source, so you receive -1 coin for each card.)\""+
                "}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<ExcommunicationTileLight> resultExpected = new ArrayList<ExcommunicationTileLight>(){{
                    add(new ExcommunicationTileLight("1.1",
                                    "Each time you gain Military points (from action spaces or from your" +
                                            " cards), gain 1 fewer Military point. (If you have more cards that give you" +
                                            " Military points, consider each card a single source, so you gain -1 " +
                                            "Military point for each card.)"));
                    add(new ExcommunicationTileLight("1.2",
                                    "Each time you receive coins (from action spaces or from your Cards)," +
                                            " you receive 1 fewer coin. (If you have more Cards that give you coins," +
                                            " consider each Card a single source, so you receive -1 coin " +
                                            "for each card.)"));}};
        List<ExcommunicationTileLight> result = (List<ExcommunicationTileLight>)method.invoke(parserLightModel, obj);
        for (int i=0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i=0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

}