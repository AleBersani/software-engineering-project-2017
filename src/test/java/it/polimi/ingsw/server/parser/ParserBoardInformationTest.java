package it.polimi.ingsw.server.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamelogic.enums.PeriodNumber;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserBoardInformationTest {
    private ParserBoardInformation parserBoardInformation;

    @BeforeEach
    void setUp() {
        parserBoardInformation = new ParserBoardInformation();
    }

    @Test
    void testParseSingleTowerMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleTowerMap";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{"+
                "\"space\": {"+
                            "\"requestedValue\": \"3\","+
                            "\"boardIdentifier\": \"T_G_3\"},"+
                "\"instantGoods\": {"+
                            "\"resources\": {"+
                                        "\"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"},"+
                            "\"points\": {"+
                                        "\"victory\": \"0\", \"military\": \"0\", \"faith\": \"0\"}}" +
                "}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        Space space;
        Goods bonus;
        space = new Space(BoardIdentifier.T_G_3, 3);
        bonus = new Goods(new Resources(0,0,0,1));
        Map<Space, Goods> resultExpected = new HashMap<Space, Goods>(){{put(space, bonus);}};
        Map<Space, Goods> result = (Map<Space, Goods>) method.invoke(parserBoardInformation, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testParseSpace() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSpace";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{"+
                "\"requestedValue\": \"3\","+
                "\"boardIdentifier\": \"T_G_3\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Space resultExpected = new Space(BoardIdentifier.T_G_3, 3);
        Space result = (Space) method.invoke(parserBoardInformation, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testGetParsedCouncilPalace() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "getParsedCouncilPalace";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{"+
                "\"requestedValue\": \"3\","+
                "\"instantGoods\": {"+
                    "\"resources\": {"+
                                    "\"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"},"+
                    "\"points\": {"+
                                    "\"victory\": \"0\", \"military\": \"0\", \"faith\": \"0\"}," +
                    "\"councilPrivilege\": \"1\"}" +
                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        CouncilPalace resultExpected = new CouncilPalace(new ExchangingGoods(
                                                                    new Resources(0,0,0,1),
                                                                    1),
                                                        3);
        CouncilPalace result = (CouncilPalace) method.invoke(parserBoardInformation, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testParseProductionHarvestLists() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseProductionHarvestLists";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, List.class, JsonArray.class);
        method.setAccessible(true);
        String json = "[" +
                        "{\"space\": {"+
                            "\"requestedValue\": \"1\","+
                            "\"boardIdentifier\": \"PRODUCTION_2\"}," +
                        "\"malusValue\": 3 }" +
                        "]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<ProductionHarvestSpace> resultExpected = new ArrayList<ProductionHarvestSpace>(){{
                                                        add(new ProductionHarvestSpace(
                                                            new Space(BoardIdentifier.PRODUCTION_2, 1),
                                                            3));}};
        List<ProductionHarvestSpace> result = new ArrayList<>();
        method.invoke(parserBoardInformation, result, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testParseMarket() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseMarket";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, List.class, JsonArray.class);
        method.setAccessible(true);
        String json = "[{\"space\": {"+
                            "\"requestedValue\": \"3\","+
                            "\"boardIdentifier\": \"M_2\"},"+
                        "\"instantGoods\": {"+
                            "\"resources\": {"+
                                    "\"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"},"+
                            "\"points\": {"+
                                    "\"victory\": \"0\", \"military\": \"0\", \"faith\": \"0\"}," +
                            "\"councilPrivilege\": \"0\"}" +
                        "}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<MarketSpace> resultExpected = new ArrayList<MarketSpace>(){{
                                            add(new MarketSpace(
                                                            new Space(BoardIdentifier.M_2, 3),
                                                            new ExchangingGoods(new Resources(0,0,
                                                                                            0,1),
                                                                                    0)));}};
        List<MarketSpace> result = new ArrayList<>();
        method.invoke(parserBoardInformation, result, obj);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testGetParsedBonusTiles() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "getParsedBonusTiles";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, Map.class, JsonArray.class);
        method.setAccessible(true);
        String json = "[{\"name\": \"B_TILE_1\"," +
                        "\"bonusProduction\": {" +
                            "\"resources\": {" +
                                "\"woods\": \"0\"," +
                                "\"stones\": \"0\"," +
                                "\"servants\": \"2\"," +
                                "\"coins\": \"1\"}," +
                            "\"points\": {" +
                                "\"victory\": \"0\"," +
                                "\"military\": \"0\"," +
                                "\"faith\": \"0\"}," +
                            "\"councilPrivilege\": \"0\"}," +
                        "\"bonusHarvest\": {" +
                            "\"resources\": {" +
                                "\"woods\": \"1\"," +
                                "\"stones\": \"1\"," +
                                "\"servants\": \"0\"," +
                                "\"coins\": \"0\"}," +
                            "\"points\": {" +
                                "\"victory\": \"0\"," +
                                "\"military\": \"1\"," +
                                "\"faith\": \"0\"}," +
                            "\"councilPrivilege\": \"0\"}" +
                        "}," +
                        "{\"name\": \"B_TILE_2\"," +
                        "\"bonusProduction\": {" +
                            "\"resources\": {" +
                                "\"woods\": \"0\"," +
                                "\"stones\": \"0\"," +
                                "\"servants\": \"0\"," +
                                "\"coins\": \"1\"}," +
                            "\"points\": {" +
                                "\"victory\": \"0\"," +
                                "\"military\": \"2\"," +
                                "\"faith\": \"0\"}," +
                            "\"councilPrivilege\": \"0\"}," +
                        "\"bonusHarvest\": {" +
                            "\"resources\": {" +
                                "\"woods\": \"1\"," +
                                "\"stones\": \"1\"," +
                                "\"servants\": \"1\"," +
                                "\"coins\": \"0\"}," +
                            "\"points\": {" +
                                "\"victory\": \"0\"," +
                                "\"military\": \"0\"," +
                                "\"faith\": \"0\"}," +
                            "\"councilPrivilege\": \"0\"}}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        Map<String, List<ExchangingGoods>> resultExpected = new HashMap<>();
        List<ExchangingGoods> valuesToAddBT1 = new ArrayList<ExchangingGoods>(){{
            add(new ExchangingGoods(new Resources(0,0,2,1), 0));
            add(new ExchangingGoods(new Resources(1, 1, 0, 0),
                                    new Points(0, 1, 0), 0));
        }};
        resultExpected.put("B_TILE_1", valuesToAddBT1);
        List<ExchangingGoods> valuesToAddBT2 = new ArrayList<ExchangingGoods>(){{
            add(new ExchangingGoods(new Resources(0, 0, 0, 1),
                                    new Points(0, 2, 0), 0));
            add(new ExchangingGoods(new Resources(1, 1, 1, 0), 0));
        }};
        resultExpected.put("B_TILE_2", valuesToAddBT2);
        Map<String, List<ExchangingGoods>> result = new HashMap<>();
        method.invoke(parserBoardInformation, result, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testParseExchangingGoods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseExchangingGoods";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                        "\"resources\": {" +
                            "\"woods\": \"1\"," +
                            "\"stones\": \"1\"," +
                            "\"servants\": \"1\"," +
                            "\"coins\": \"0\"}," +
                        "\"points\": {" +
                            "\"victory\": \"0\"," +
                            "\"military\": \"0\"," +
                            "\"faith\": \"0\"}," +
                        "\"councilPrivilege\": \"0\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        ExchangingGoods resultExpected = new ExchangingGoods(new Resources(1,1,1,0),
                                                             0);
        ExchangingGoods result = (ExchangingGoods) method.invoke(parserBoardInformation, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testGetFaithToVictoryPointsMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "getFaithToVictoryPointsMap";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, Map.class, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"0\": \"0\"," +
                "\"1\": \"1\"," +
                "\"2\": \"2\"" +

                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Map<Integer, Integer> resultExpected = new HashMap<Integer, Integer>(){{put(0, 0);
            put(1, 1);
            put(2, 2);}};
        Map<Integer, Integer> result = new HashMap<>();
        method.invoke(parserBoardInformation, result, obj);
        assertEquals(resultExpected, result);
    }

    @Test
    void testGetFaithPointsForExcommunication() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "getFaithPointsForExcommunication";
        Class targetClass = parserBoardInformation.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, Map.class, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"FIRST\": \"4\"," +
                "\"SECOND\": \"5\"," +
                "\"THIRD\": \"6\"" +
                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Map<PeriodNumber, Integer> resultExpected = new HashMap<PeriodNumber, Integer>(){{put(PeriodNumber.FIRST, 4);
            put(PeriodNumber.SECOND,5);
            put(PeriodNumber.THIRD, 6);}};
        Map<PeriodNumber, Integer> result = new HashMap<>();
        method.invoke(parserBoardInformation, result, obj);
        assertEquals(resultExpected, result);
    }
}