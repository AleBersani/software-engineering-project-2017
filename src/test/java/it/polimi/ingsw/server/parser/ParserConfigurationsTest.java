package it.polimi.ingsw.server.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
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

class ParserConfigurationsTest {
    private ParserConfigurations parserConfigurations;

    @BeforeEach
    void setUp() {
        parserConfigurations = new ParserConfigurations();
    }

    @Test
    void testParseStartingGoods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseStartingGoods";
        Class targetClass = parserConfigurations.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{" +
                "\"resources\": {" +
                    "\"woods\": \"2\"," +
                    "\"stones\": \"2\"," +
                    "\"servants\": \"2\"," +
                    "\"coins\": \"5\"}," +
                "\"points\": {" +
                    "\"victory\": \"0\"," +
                    "\"military\": \"0\"," +
                    "\"faith\": \"0\"}}," +
                "{\"resources\": {" +
                    "\"woods\": \"2\"," +
                    "\"stones\": \"2\"," +
                    "\"servants\": \"2\"," +
                    "\"coins\": \"6\"}," +
                "\"points\": {" +
                    "\"victory\": \"0\"," +
                    "\"military\": \"0\"," +
                    "\"faith\": \"0\"}" +
                "}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<Goods> resultExpected = new ArrayList<>();
        resultExpected.add(new Goods(new Resources(2,2,2,5)));
        resultExpected.add(new Goods(new Resources(2,2,2,6)));
        List<Goods> result = (List<Goods>) method.invoke(parserConfigurations, obj);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseRequiredPointsTerritoriesMap() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseRequiredPointsTerritoriesMap";
        Class targetClass = parserConfigurations.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{" +
                "\"numberOfTerritories\": \"3\"," +
                "\"points\": {" +
                    "\"victory\": \"0\"," +
                    "\"military\": \"3\"," +
                    "\"faith\": \"0\"}" +
                "},{" +
                "\"numberOfTerritories\": \"4\"," +
                "\"points\": {" +
                    "\"victory\": \"0\"," +
                    "\"military\": \"7\"," +
                    "\"faith\": \"0\"" +
                "}}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        Map<Integer, Points> resultExpected = new HashMap<>();
        resultExpected.put(3, new Points(0,3,0));
        resultExpected.put(4, new Points(0,7,0));
        Map<Integer, Points> result = (Map<Integer, Points>) method.invoke(parserConfigurations, obj);
        assertEquals(resultExpected, result);
    }
}