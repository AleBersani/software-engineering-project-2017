package it.polimi.ingsw.server.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.*;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.*;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.PawnColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserModifiersTest {
    private ParserModifiers parserModifiers;

    @BeforeEach
    void setUp() {
        parserModifiers = new ParserModifiers();
    }

    @Test
    void testParseListRequirementsModifier() throws Exception {
        String json = "{ \"actionType\": [\"GREEN_TOWER\"], " +
                "\"bonus\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"1\"}," +
                "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}}]," +
                "\"pawnColor\": [\"orange\"]," +
                "\"actionValueSurplus\": \"2\"," +
                "\"pawnColor\": [\"orange\"]," +
                "\"pawnValue\": \"2\"}";
        List<String> modifiers = new ArrayList<>();
        modifiers.add("bonusOnCardCost");
        modifiers.add("bonusPawnValue");
        modifiers.add("fixedColouredPawnValue");
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<RequirementsModifier> resultExpected = new ArrayList<>();
        Goods bonus = new Goods(new Resources(0,0,0,1),
                                new Points(2,0,0));
        resultExpected.add(new BonusOnCardCost(new AvailableActions(ActionType.GREEN_TOWER), bonus));
        resultExpected.add(new BonusPawnValue(new AvailableActions(ActionType.GREEN_TOWER),
                PawnColor.ORANGE, 2));
        resultExpected.add(new FixedColouredPawnValue(new AvailableActions(ActionType.GREEN_TOWER),
                PawnColor.ORANGE, 2));
        List<RequirementsModifier> result = parserModifiers.parseListRequirementsModifier(modifiers, obj);
        for (int i = 0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i = 0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseBonusActionValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBonusActionValue";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"]," +
                        "\"bonusValue\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new BonusActionValue(new AvailableActions(availableActions),2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseBonusOnCardCost() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBonusOnCardCost";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, int.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"], " +
                        "\"bonus\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"1\"}," +
                        "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        Goods bonus = new Goods(new Resources(0,0,0,1),
                                        new Points(2,0,0));
        RequirementsModifier resultExpected = new BonusOnCardCost(new AvailableActions(availableActions), bonus);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj, 0);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseBonusPawnsValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBonusPawnsValue";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"]," +
                        "\"pawnColor\": [\"orange\", \"black\", \"neutral\"]," +
                        "\"actionValueSurplus\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        List<PawnColor> pawnColors = new ArrayList<PawnColor>(){{add(PawnColor.ORANGE);
                                                                add(PawnColor.BLACK);
                                                                add(PawnColor.NEUTRAL);}};
        RequirementsModifier resultExpected = new BonusPawnsValue(new AvailableActions(availableActions),
                pawnColors, 2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseBonusPawnValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBonusPawnValue";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, int.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"]," +
                        "\"pawnColor\": [\"orange\", \"black\"]," +
                        "\"actionValueSurplus\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new BonusPawnValue(new AvailableActions(availableActions),
                                                                        PawnColor.BLACK, 2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj, 1);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseCanPlaceOnOccupiedSpace()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseCanPlaceOnOccupiedSpace";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new CanPlaceOnOccupiedSpace(new AvailableActions(availableActions));
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseDoubleServants() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseDoubleServants";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new DoubleServants(new AvailableActions(availableActions));
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseFixedColouredPawnsValue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseFixedColouredPawnsValue";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"]," +
                "\"pawnColor\": [\"orange\", \"black\", \"neutral\"]," +
                "\"pawnsValue\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        List<PawnColor> pawnColors = new ArrayList<PawnColor>(){{add(PawnColor.ORANGE); add(PawnColor.BLACK);
                                                                    add(PawnColor.NEUTRAL);}};
        RequirementsModifier resultExpected = new FixedColouredPawnsValue(new AvailableActions(availableActions),
                                                                                pawnColors, 2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseFixedColouredPawnValue()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseFixedColouredPawnValue";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, int.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"]," +
                "\"pawnColor\": [\"orange\"]," +
                "\"pawnValue\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new FixedColouredPawnValue(new AvailableActions(availableActions),
                                                                            PawnColor.ORANGE, 2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj, 0);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseMalusActionValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseMalusActionValue";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"]," +
                        "\"malusActionValue\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new MalusActionValue(new AvailableActions(availableActions), 2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseMalusColouredPawns() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseMalusColouredPawns";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"], " +
                        "\"pawnColor\": [\"orange\", \"black\", \"neutral\"]," +
                        "\"malusActionValue\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
            add(ActionType.PURPLE_TOWER);
            add(ActionType.YELLOW_TOWER);}};
        List<PawnColor> pawnColors = new ArrayList<PawnColor>(){{add(PawnColor.ORANGE); add(PawnColor.BLACK);
            add(PawnColor.NEUTRAL);}};
        RequirementsModifier resultExpected = new MalusColouredPawns(
                new AvailableActions(availableActions), pawnColors, 2);
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseNoBonusGoodsOnTower()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseNoBonusGoodsOnTower";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new NoBonusGoodsOnTower(new AvailableActions(availableActions));
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseNoMarketPlacement() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseNoMarketPlacement";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new NoMarketPlacement(new AvailableActions(availableActions));
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseNoMilitaryForTerritories()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseNoMilitaryForTerritories";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new NoMilitaryForTerritories(new AvailableActions(availableActions));
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseNoOccupiedTowerCost()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseNoOccupiedTowerCost";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RequirementsModifier resultExpected = new NoOccupiedTowerCost(new AvailableActions(availableActions));
        RequirementsModifier result = (RequirementsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseBonusRewards() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        String methodName = "parseBonusRewards";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"], " +
                "\"bonus\": [{\"resources\": { \"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"}," +
                "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        Goods bonusRewards = new Goods(new Resources(0,0,0,1),
                                        new Points(2,0,0));
        RewardsModifier resultExpected = new BonusRewards(new AvailableActions(availableActions), bonusRewards);
        RewardsModifier result = (RewardsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseDoubleRewards() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseDoubleRewards";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RewardsModifier resultExpected = new DoubleRewards(new AvailableActions(availableActions));
        RewardsModifier result = (RewardsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseMalusRewards() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseMalusRewards";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"], " +
                        "\"rewardsMalus\": [{\"resources\": { \"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"}," +
                                                "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        Goods rewardsMalus = new Goods(new Resources(0,0,0,1),
                                        new Points(2,0,0));
        RewardsModifier resultExpected = new MalusRewards(new AvailableActions(availableActions), rewardsMalus);
        RewardsModifier result = (RewardsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseNoTowerBonusRewards()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseNoTowerBonusRewards";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        RewardsModifier resultExpected = new NoTowerBonusRewards(new AvailableActions(availableActions));
        RewardsModifier result = (RewardsModifier) method.invoke(parserModifiers, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseListActionType() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseListActionType";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> resultExpected = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PURPLE_TOWER);
                                                                        add(ActionType.YELLOW_TOWER);}};
        List<ActionType> result = (List<ActionType>) method.invoke(parserModifiers, obj);
        for (int i =0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i = 0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseListPawnColor() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseListPawnColor";
        Class targetClass = parserModifiers.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"pawnColor\": [\"orange\", \"black\", \"neutral\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<PawnColor> resultExpected = new ArrayList<PawnColor>(){{add(PawnColor.ORANGE); add(PawnColor.BLACK);
                                                                        add(PawnColor.NEUTRAL);}};
        List<PawnColor> result = (List<PawnColor>) method.invoke(parserModifiers, obj);
        for (int i = 0; i < resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for (int i = 0; i < result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }
}