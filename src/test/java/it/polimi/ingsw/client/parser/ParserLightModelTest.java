package it.polimi.ingsw.client.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.client.cli.model.*;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserLightModelTest {
    private ParserLightModel parserLightModel;

    @BeforeEach
    void setUp() {
        parserLightModel = new ParserLightModel();
    }

    @Test
    void testParseBoardInformation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBoardInformation";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class, List.class);
        method.setAccessible(true);
        String json = "[{" +
                "\"bonus\": \"2W\"," +
                "\"cost\": \"7\"," +
                "\"malus\": \"0\"," +
                "\"boardIdentifier\": \"T_G_4\"" +
                "}," +
                "{" +
                "\"bonus\": \"1W\"," +
                "\"cost\": \"5\"," +
                "\"malus\": \"0\"," +
                "\"boardIdentifier\": \"T_G_3\"" +
                "}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<BoardSpaceDescriptionLight> resultExpected = new ArrayList<BoardSpaceDescriptionLight>(){{
                                                            add(new BoardSpaceDescriptionLight(BoardIdentifier.T_G_4,
                                                                    "2W", 7, 0));
                                                            add(new BoardSpaceDescriptionLight(BoardIdentifier.T_G_3,
                                                                    "1W", 5, 0));}};
        List<BoardSpaceDescriptionLight> result = new ArrayList<>();
        method.invoke(parserLightModel, obj, result);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseSingleBoardSpace() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleBoardSpace";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"bonus\": \"2W\"," +
                "\"cost\": \"7\"," +
                "\"malus\": \"0\"," +
                "\"boardIdentifier\": \"T_G_4\"" +
                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        BoardSpaceDescriptionLight resultExpected = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_4,
                                                                    "2W", 7, 0);
        BoardSpaceDescriptionLight result = (BoardSpaceDescriptionLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseBonusTilesInformation()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBonusTilesInformation";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class, List.class);
        method.setAccessible(true);
        String json = "[{" +
                "\"name\": \"B_TILE_1\"," +
                "\"description\": \"Production: {2Se, 1C} Harvest: {1W, 1S, 1Mp}\"" +
                "}," +
                "{" +
                "\"name\": \"B_TILE_2\"," +
                "\"description\": \"Production: {2Mp, 1C} Harvest: {1W, 1S, 1Se}\"" +
                "}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<BonusTileDescriptionLight> resultExpected = new ArrayList<BonusTileDescriptionLight>(){{
                        add(new BonusTileDescriptionLight("B_TILE_1",
                                                          "Production: {2Se, 1C} Harvest: {1W, 1S, 1Mp}"));
                        add(new BonusTileDescriptionLight("B_TILE_2",
                                                          "Production: {2Mp, 1C} Harvest: {1W, 1S, 1Se}"));
        }};
        List<BonusTileDescriptionLight> result = new ArrayList<>();
        method.invoke(parserLightModel, obj, result);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseSingleBonusTile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleBonusTile";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"name\": \"B_TILE_1\"," +
                "\"description\": \"Production: {2Se, 1C} Harvest: {1W, 1S, 1Mp}\"" +
                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        BonusTileDescriptionLight resultExpected = new BonusTileDescriptionLight("B_TILE_1",
                                                            "Production: {2Se, 1C} Harvest: {1W, 1S, 1Mp}");
        BonusTileDescriptionLight result = (BonusTileDescriptionLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseDevelopmentCardsLight()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseDevelopmentCardsLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class, List.class);
        method.setAccessible(true);
        String json = "[{" +
                "\"name\": \"Commercial Hub\"," +
                "\"number\": \"1\"," +
                "\"instantEffectDescription\": \"None\"," +
                "\"permanentEffectDescription\": [\"You get 1 Coin as harvest result with cost of 1\"]," +
                "\"cost\": [\"None\"]" +
                "}," +
                "{\"name\": \"Woods\"," +
                "\"number\": \"2\"," +
                "\"instantEffectDescription\": \"You receive 1 Wood\"," +
                "\"permanentEffectDescription\": [\"You get 1 Wood as harvest result with cost of 2\"]," +
                "\"cost\": [\"None\"]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<DevelopmentCardsLight> resultExpected = new ArrayList<DevelopmentCardsLight>(){{
                add(new DevelopmentCardsLight("Commercial Hub",
                                            "None",
                                            "You get 1 Coin as harvest result with cost of 1",
                                            "None"));
                add(new DevelopmentCardsLight("Woods",
                                            "You receive 1 Wood",
                                            "You get 1 Wood as harvest result with cost of 2",
                                            "None"));}};
        List<DevelopmentCardsLight> result = new ArrayList<>();
        method.invoke(parserLightModel, obj, result);
        for(int i=0; i<resultExpected.size(); i++)
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        for(int i=0; i<result.size(); i++)
            assertTrue(resultExpected.get(i).equals(result.get(i)));
    }

    @Test
    void testParseSingleDevCard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleDevCard";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"name\": \"Commercial Hub\"," +
                "\"number\": \"1\"," +
                "\"instantEffectDescription\": \"None\"," +
                "\"permanentEffectDescription\": [\"You get 1 Coin as harvest result with cost of 1\"]," +
                "\"cost\": [\"None\"]" +
                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        DevelopmentCardsLight resultExpected = new DevelopmentCardsLight(
                                            "Commercial Hub",
                                            "None",
                                            "You get 1 Coin as harvest result with cost of 1",
                                            "None");
        DevelopmentCardsLight result = (DevelopmentCardsLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseExcommunicationTilesLight()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseExcommunicationTilesLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class, List.class);
        method.setAccessible(true);
        String json = "[{\"name\": \"1.1\"," +
                "\"effectDescription\": \"Each time you gain Military points (from action spaces or from your cards), " +
                "gain 1 fewer Military point. (If you have more cards that give you Military points," +
                " consider each card a single source, " +
                "so you gain -1 Military point for each card.)\"" +
                "},{" +
                "\"name\": \"1.2\"," +
                "\"effectDescription\": \"Each time you receive coins (from action spaces or from your Cards), " +
                "you receive 1 fewer coin. (If you have more Cards that give you coins, consider each Card a single" +
                " source, so you receive -1 coin for each card.)\"}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<ExcommunicationTileLight> resultExpected = new ArrayList<ExcommunicationTileLight>(){{
                    add(new ExcommunicationTileLight("1.1",
                        "Each time you gain Military points (from action spaces or from your cards), " +
                                "gain 1 fewer Military point. (If you have more cards that give you Military points," +
                                " consider each card a single source, so you gain -1 Military point for each card.)"));
                    add(new ExcommunicationTileLight("1.2",
                        "Each time you receive coins (from action spaces or from your Cards), you " +
                                "receive 1 fewer coin. (If you have more Cards that give you coins, consider each " +
                                "Card a single source, so you receive -1 coin for each card.)"));
        }};
        List<ExcommunicationTileLight> result = new ArrayList<>();
        method.invoke(parserLightModel, obj, result);
        for(int i=0; i<resultExpected.size(); i++)
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        for(int i=0; i<result.size(); i++)
            assertTrue(resultExpected.get(i).equals(result.get(i)));
    }

    @Test
    void testParseSingleExcommunicationTile()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleExcommunicationTile";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"name\": \"1.2\"," +
                "\"effectDescription\": \"Each time you receive coins (from action spaces or from your Cards), " +
                "you receive 1 fewer coin. (If you have more Cards that give you coins, consider each Card a single" +
                " source, so you receive -1 coin for each card.)\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        ExcommunicationTileLight resultExpected = new ExcommunicationTileLight("1.2",
                "Each time you receive coins (from action spaces or from your Cards), you " +
                                "receive 1 fewer coin. (If you have more Cards that give you coins, consider each " +
                                "Card a single source, so you receive -1 coin for each card.)");
        ExcommunicationTileLight result = (ExcommunicationTileLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseLeaderCardsLight() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseLeaderCardsLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class, List.class);
        method.setAccessible(true);
        String json = "[{" +
                "\"name\": \"Francesco Sforza\"," +
                "\"effectDescription\": \"Perform a harvest action at value 1\"," +
                "\"requirements\": [\"5 Venture cards\"]" +
                "},{" +
                "\"name\": \"Ludovico Ariosto\"," +
                "\"effectDescription\": \"You can place your pawn in an occupied action space\"," +
                "\"requirements\": [\"5 Character cards\"]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<LeaderCardLight> resultExpected = new ArrayList<LeaderCardLight>(){{
                        add(new LeaderCardLight("Francesco Sforza",
                                                "Perform a harvest action at value 1",
                                                "5 Venture cards"));
                        add(new LeaderCardLight("Ludovico Ariosto",
                                                "You can place your pawn in an occupied action space",
                                                "5 Character cards"));
        }};
        List<LeaderCardLight> result = new ArrayList<>();
        method.invoke(parserLightModel, obj, result);
        for(int i=0; i<resultExpected.size(); i++)
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        for(int i=0; i<result.size(); i++)
            assertTrue(resultExpected.get(i).equals(result.get(i)));
    }

    @Test
    void testParseSingleLeaderCardLight()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleLeaderCardLight";
        Class targetClass = parserLightModel.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{" +
                "\"name\": \"Francesco Sforza\"," +
                "\"effectDescription\": \"Perform a harvest action at value 1\"," +
                "\"requirements\": [\"5 Venture cards\"]" +
                "}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        LeaderCardLight resultExpected = new LeaderCardLight("Francesco Sforza",
                                                            "Perform a harvest action at value 1",
                                                            "5 Venture cards");
        LeaderCardLight result = (LeaderCardLight) method.invoke(parserLightModel, obj);
        assertTrue(resultExpected.equals(result));
    }
}