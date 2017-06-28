package it.polimi.ingsw.server.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.basics.Points;
import it.polimi.ingsw.server.gamelogic.basics.Resources;
import it.polimi.ingsw.server.gamelogic.cards.additionalinfo.*;
import it.polimi.ingsw.server.gamelogic.modifiers.AvailableActions;
import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers.EndGameRewardsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.endgamerewards.modifiers.LessVictoryBasedOnBuildingsCosts;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.CanPlaceOnOccupiedSpace;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.DoubleServants;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.modifiers.RequirementsModifier;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.BonusRewards;
import it.polimi.ingsw.shared.model.ActionType;
import it.polimi.ingsw.shared.model.GeneralColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserAdditionalInfoTest {
    private ParserAdditionalInfo parserAdditionalInfo;

    @BeforeEach
    void setUp() {
        parserAdditionalInfo = new ParserAdditionalInfo();
    }

    @Test
    void testParseEndGameRewardsModifier()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseEndGameRewardsModifier";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"modifiers\": [\"lessVictoryBasedOnBuildingsCosts\"]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        EndGameRewardsModifier resultExpected = new LessVictoryBasedOnBuildingsCosts();
        EndGameRewardsModifier result = (EndGameRewardsModifier) method.invoke(parserAdditionalInfo, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseCardFlashAction() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseCardFlashAction";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class, int.class);
        method.setAccessible(true);
        String json = "{\"actionType\": [\"GREEN_TOWER\", \"PRODUCTION\", \"HARVEST\", \"MARKET\"]," +
                        "\"actionValue\": \"3\"," +
                        "\"discount\":[{\"resources\":{\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"3\"},"+
                                        "\"points\": { \"victory\": \"0\", \"military\": \"0\", \"faith\": \"0\"}}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        AdditionalCardInfo resultExpected = new CardFlashAction("Test6", ActionType.PRODUCTION, 3,
                new Goods(new Resources(0,0,0,3)));
        AdditionalCardInfo result = (AdditionalCardInfo) method.invoke(parserAdditionalInfo, obj, "Test6", 1);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseConditionalProduction()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseConditionalProduction";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{\"cardColor\":\"yellow\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        AdditionalCardInfo resultExpected = new ConditionalProduction("Test4", GeneralColor.YELLOW);
        AdditionalCardInfo result = (AdditionalCardInfo) method.invoke(parserAdditionalInfo, obj, "Test4");
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseGoodsBasedOnPossessions()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseGoodsBasedOnPossessions";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{\"rewardsForPossessions\":[" +
                            "{\"resources\":{\"woods\":\"2\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"0\"},"+
                            "\"points\": { \"victory\": \"0\", \"military\": \"1\", \"faith\": \"0\"}}]," +
                        "\"typeOfObjectRequired\": \"miliTary\"," +
                        "\"numberOfObjectRequired\": \"2\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        AdditionalCardInfo resultExpected = new GoodsBasedOnPossessions("Test3",
                                                            new Goods(
                                                                    new Resources(2,0,0,0),
                                                                    new Points(0,1,0)),
                                                            "MILITARY", 2);
        AdditionalCardInfo result = (AdditionalCardInfo) method.invoke(parserAdditionalInfo, obj, "Test3");
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseMultipleProduction() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseMultipleProduction";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{" +
                "\"result\":[{\"resources\":{\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"0\"},"+
                            "\"points\": { \"victory\": \"3\", \"military\": \"0\", \"faith\": \"0\"}," +
                            "\"councilePrivilege\": \"0\"}," +
                            "{\"resources\":{\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"0\"},"+
                            "\"points\": { \"victory\": \"6\", \"military\": \"0\", \"faith\": \"0\"}," +
                            "\"councilePrivilege\": \"0\"}],"+
                "\"costs\":[{\"resources\":{\"woods\":\"2\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"0\"},"+
                            "\"points\": { \"victory\": \"0\", \"military\": \"1\", \"faith\": \"0\"}}," +
                            "{\"resources\":{\"woods\":\"5\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"0\"},"+
                            "\"points\": { \"victory\": \"0\", \"military\": \"2\", \"faith\": \"0\"}}] }";
        List<Goods> costProduction = new ArrayList<Goods>(){{
                                                    add(new Goods(new Resources(2, 0, 0, 0),
                                                                    new Points(0, 1, 0)));
                                                    add(new Goods(new Resources(5, 0, 0, 0),
                                                                    new Points(0, 2, 0)));}};
        List<ExchangingGoods> resultProduction=new ArrayList<ExchangingGoods>(){{
                                                    add(new ExchangingGoods(new Resources(),
                                                                            new Points(3, 0, 0),
                                                                            0));
                                                    add(new ExchangingGoods(new Resources(),
                                                                            new Points(6, 0, 0),
                                                                            0));}};
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        AdditionalCardInfo resultExpected = new MultipleProduction("Test2",costProduction, resultProduction);
        AdditionalCardInfo result = (AdditionalCardInfo) method.invoke(parserAdditionalInfo, obj, "Test2");
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseRequirementsOnCard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseRequirementsOnCard";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{\"actionType\": [\"GREEN_TOWER\", \"PRODUCTION\", \"HARVEST\", \"MARKET\"]," +
                        "\"modifiers\": [\"doubleServants\", \"canPlaceOnOccupiedSpace\"]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableAction = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
                                                                        add(ActionType.PRODUCTION);
                                                                        add(ActionType.HARVEST);
                                                                        add(ActionType.MARKET);}};
        List<RequirementsModifier> requirementsModifiers = new ArrayList<RequirementsModifier>(){{
                                            add(new DoubleServants(new AvailableActions(availableAction)));
                                            add(new CanPlaceOnOccupiedSpace(new AvailableActions(availableAction)));}};
        AdditionalCardInfo resultExpected = new RequirementsOnCard("Test5",requirementsModifiers);
        AdditionalCardInfo result = (AdditionalCardInfo)method.invoke(parserAdditionalInfo, obj, "Test5");
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseRewardsOnCard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseRewardsOnCard";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{ \"actionType\": [\"GREEN_TOWER\", \"PURPLE_TOWER\", \"YELLOW_TOWER\"], " +
                        "\"bonus\":[{\"resources\":{\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"1\"},"+
                        "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}}]," +
                        "\"modifiers\":[\"bonusRewards\"] }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        List<ActionType> availableActions = new ArrayList<ActionType>(){{add(ActionType.GREEN_TOWER);
            add(ActionType.PURPLE_TOWER);
            add(ActionType.YELLOW_TOWER);}};
        AdditionalCardInfo resultExpected = new RewardsOnCard("Test1", new BonusRewards(
                                                            new AvailableActions(availableActions),
                                                            new Goods(new Resources(0,0,0,1),
                                                                        new Points(2,0,0))));
        AdditionalCardInfo result = (AdditionalCardInfo) method.invoke(parserAdditionalInfo, obj, "Test1");
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseCardFlashExchangingGoods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseCardFlashExchangingGoods";
        Class targetClass = parserAdditionalInfo.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{\"instantEffect\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"3\",\"coins\":\"1\"}," +
                        "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"3\"}," +
                "       \"councilePrivilege\": \"1\"}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        ExchangingGoods exchangingGoods = new ExchangingGoods(
                new Goods(new Resources(0,0,3,1),
                        new Points(2,0,3)), 1);
        AdditionalCardInfo resultExpected = new CardFlashExchangingGoods("Test1", exchangingGoods);
        AdditionalCardInfo result = (AdditionalCardInfo) method.invoke(parserAdditionalInfo, obj, "Test1");
        assertTrue(resultExpected.equals(result));
    }
}