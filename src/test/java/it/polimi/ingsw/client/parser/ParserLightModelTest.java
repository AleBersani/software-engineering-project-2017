package it.polimi.ingsw.client.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.client.lightmodel.*;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String json = "[{" +
                        "\"number\": \"32\""+
                        "\"name\": \"Commercial Hub\","+
                        "\"instantEffectDescription\": \"None\","+
                        "\"permanentEffectDescription\": [\"You get 1 Coin as harvest result with cost of 1\"],"+
                        "\"cost\": [\"None\"]},"+
                        "{" +
                        "\"number\": \"35\""+
                        "\"name\": \"Woods\","+
                        "\"instantEffectDescription\": \"None\","+
                        "\"permanentEffectDescription\": [\"You get 1 Wood as harvest result with cost of 2\"],"+
                        "\"cost\": [\"None\"]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<String> cost1 = new ArrayList<String>(){{add("None");}};
        List<String> permanentEffect1 = new ArrayList<String>(){{add("You get 1 Coin as harvest result with cost of 1");}};
        List<String> permanentEffect2 = new ArrayList<String>(){{add("You get 1 Wood as harvest result with cost of 2");}};
        List<DevelopmentCardLight> resultExpected = new ArrayList<DevelopmentCardLight>(){{
                        add(new DevelopmentCardLight(32, "Commercial Hub",
                                                            cost1,
                                        "None",
                                                            permanentEffect1));
                        add(new DevelopmentCardLight(35,"Woods",
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

    @Test
    void testRealParseBoardLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "realParseBoardLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"greenTower\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"yellowTower\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"blueTower\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"purpleTower\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"market\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"harvest\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"production\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}],"+
                        "\"councilPalace\": [" +
                            "{\"bonus\": {\"woods\": \"2\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"0\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"7\",\"malus\": \"0\", \"boardIdentifier\":\"T_G_2\"}]" +
                        "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Map<GoodsLight, Integer> bonusMap = new HashMap<>();
        bonusMap.put(GoodsLight.WOODS, 2);
        bonusMap.put(GoodsLight.STONES, 0);
        bonusMap.put(GoodsLight.MILITARY_POINTS, 0);
        bonusMap.put(GoodsLight.COINS, 0);
        bonusMap.put(GoodsLight.SERVANTS, 0);
        bonusMap.put(GoodsLight.COUNCIL_PRIVILEGE, 0);
        List<SlotLight> place = new ArrayList<SlotLight>(){{add(new SlotLight(BoardIdentifier.T_G_2, bonusMap,
                                                                                7, 0));}};
        BoardLight resultExpected = new BoardLight(place, place, place, place, place, place, place, place);
        BoardLight result = (BoardLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseListSlotLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseListSlotLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json =  "[" +
                        "{\"bonus\": {\"woods\": \"5\",\"stones\": \"0\",\"military\": \"0\"," +
                                     "\"coins\": \"0\",\"servants\": \"6\",\"councilPrivilege\": \"0\"}," +
                        "\"cost\": \"0\",\"malus\": \"1\", \"boardIdentifier\":\"T_G_2\"}" +
                        "]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        Map<GoodsLight, Integer> bonusMap = new HashMap<>();
        bonusMap.put(GoodsLight.WOODS, 5);
        bonusMap.put(GoodsLight.STONES, 0);
        bonusMap.put(GoodsLight.MILITARY_POINTS, 0);
        bonusMap.put(GoodsLight.COINS, 0);
        bonusMap.put(GoodsLight.SERVANTS, 6);
        bonusMap.put(GoodsLight.COUNCIL_PRIVILEGE, 0);
        List<SlotLight> resultExpected = new ArrayList<SlotLight>(){{add(new SlotLight(BoardIdentifier.T_G_2, bonusMap,
                                                                                        0, 1));}};
        List<SlotLight> result = (List<SlotLight>) method.invoke(parserLightModel, obj);
        for (int i=0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i=0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }
    @Test
    void testParseSingleSlotLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleSlotLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json =  "{" +
                            "\"bonus\": {\"woods\": \"5\",\"stones\": \"0\",\"military\": \"0\"," +
                                        "\"coins\": \"0\",\"servants\": \"6\",\"councilPrivilege\": \"0\"}," +
                            "\"cost\": \"0\",\"malus\": \"1\", \"boardIdentifier\":\"T_G_1\"" +
                        "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Map<GoodsLight, Integer> bonusMap = new HashMap<>();
        bonusMap.put(GoodsLight.WOODS, 5);
        bonusMap.put(GoodsLight.STONES, 0);
        bonusMap.put(GoodsLight.MILITARY_POINTS, 0);
        bonusMap.put(GoodsLight.COINS, 0);
        bonusMap.put(GoodsLight.SERVANTS, 6);
        bonusMap.put(GoodsLight.COUNCIL_PRIVILEGE, 0);
        SlotLight resultExpected = new SlotLight(BoardIdentifier.T_G_1, bonusMap, 0, 1);
        SlotLight result = (SlotLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseBonusMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBonusMap";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"woods\": \"5\",\"stones\": \"0\",\"military\": \"0\"," +
                       "\"coins\": \"0\",\"servants\": \"6\",\"councilPrivilege\": \"0\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Map<GoodsLight, Integer> resultExpected = new HashMap<>();
        resultExpected.put(GoodsLight.WOODS, 5);
        resultExpected.put(GoodsLight.STONES, 0);
        resultExpected.put(GoodsLight.MILITARY_POINTS, 0);
        resultExpected.put(GoodsLight.COINS, 0);
        resultExpected.put(GoodsLight.SERVANTS, 6);
        resultExpected.put(GoodsLight.COUNCIL_PRIVILEGE, 0);
        Map<GoodsLight, Integer> result = (Map<GoodsLight, Integer>) method.invoke(parserLightModel, obj);
        assertEquals(resultExpected, result);
    }
}