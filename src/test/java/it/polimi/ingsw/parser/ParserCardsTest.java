package it.polimi.ingsw.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.gamelogic.basics.*;
import it.polimi.ingsw.gamelogic.cards.development.*;
import it.polimi.ingsw.gamelogic.cards.development.Character;
import it.polimi.ingsw.gamelogic.cards.excommunicationtiles.ExcommunicationTile;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCard;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderCost;
import it.polimi.ingsw.gamelogic.cards.leader.LeaderInformation;
import it.polimi.ingsw.gamelogic.enums.GeneralColor;
import it.polimi.ingsw.gamelogic.enums.LeaderCategory;
import it.polimi.ingsw.gamelogic.enums.PeriodNumber;
import org.apache.maven.model.Build;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.awt.image.BufferedImageDevice;

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
    void testParseTerritories() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseTerritories";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json="[{\"cardInformations\": [{\"name\": \"Zecca\",\"number\": \"25\"," +
                "                               \"period\": \"1\",\"cardColor\": \"green\"}]," +
                "\"cost\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"4\"},"+
                "\"points\": {\"victory\": \"2\",\"military\": \"0\",\"faith\": \"0\"}}]," +
                "\"harvestActionValueRequired\": \"5\","+
                "\"harvestResult\": [{" +
                "\"resources\": { \"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"}," +
                "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}," +
                "\"councilePrivilege\": \"3\"}]},"+
                "{\"cardInformations\": [{\"name\": \"Teatro\",\"number\": \"28\"," +
                "\"period\": \"1\",\"cardColor\": \"green\"}]," +
                "\"cost\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"4\"},"+
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"1\"}}]," +
                "\"harvestActionValueRequired\": \"3\","+
                "\"harvestResult\": [{" +
                "\"resources\": { \"woods\": \"0\", \"stones\": \"2\", \"servants\": \"3\", \"coins\": \"0\"}," +
                "\"points\": { \"victory\": \"4\", \"military\": \"0\", \"faith\": \"1\"}," +
                "\"councilePrivilege\": \"0\"}]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<Territory> resultExpected = new ArrayList<>();
        List<Goods> costCard1 = new ArrayList<Goods>(){{add(new Goods(new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card1 = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard1);
        resultExpected.add(new Territory(card1,5
                ,new ExchangingGoods(new Goods(new Resources(0,0,0,1),
                new Points(2,0,0)),3)));
        List<Goods> costCard2 = new ArrayList<Goods>(){{add(new Goods(new Resources(0, 0, 0, 4),
                new Points(0, 0, 1)));}};
        BasicDevelopmentCard card2 = new BasicDevelopmentCard(new CardInformation(28, "Teatro",
                PeriodNumber.FIRST, GeneralColor.GREEN), costCard2);
        resultExpected.add(new Territory(card2,3
                ,new ExchangingGoods(new Goods(new Resources(0,2,3,0),
                new Points(4,0,1)),0)));
        List<Territory> result = (List<Territory>) method.invoke(parserCards, obj);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseBuildings() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseBuildings";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json="[{\"cardInformations\": [{\"name\": \"Zecca\",\"number\": \"25\"," +
                "                               \"period\": \"1\",\"cardColor\": \"yellow\"}]," +
                "\"cost\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"4\"},"+
                "\"points\": {\"victory\": \"2\",\"military\": \"0\",\"faith\": \"0\"}}]," +
                "\"productionActionValueRequired\": \"5\","+
                "\"productionResult\": [{" +
                "\"resources\": { \"woods\": \"0\", \"stones\": \"0\", \"servants\": \"0\", \"coins\": \"1\"}," +
                "\"points\": { \"victory\": \"2\", \"military\": \"0\", \"faith\": \"0\"}," +
                "\"councilePrivilege\": \"3\"}]},"+
                "{\"cardInformations\": [{\"name\": \"Teatro\",\"number\": \"28\"," +
                "\"period\": \"1\",\"cardColor\": \"yellow\"}]," +
                "\"cost\": [{\"resources\": {\"woods\":\"0\",\"stones\":\"0\",\"servants\":\"0\",\"coins\":\"4\"},"+
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"1\"}}]," +
                "\"productionActionValueRequired\": \"3\","+
                "\"productionResult\": [{" +
                "\"resources\": { \"woods\": \"0\", \"stones\": \"2\", \"servants\": \"3\", \"coins\": \"0\"}," +
                "\"points\": { \"victory\": \"4\", \"military\": \"0\", \"faith\": \"1\"}," +
                "\"councilePrivilege\": \"0\"}]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<Building> resultExpected = new ArrayList<>();
        List<Goods> costCard1 = new ArrayList<Goods>(){{add(new Goods(new Resources(0, 0, 0, 4),
                new Points(2, 0, 0)));}};
        BasicDevelopmentCard card1 = new BasicDevelopmentCard(new CardInformation(25, "Zecca",
                PeriodNumber.FIRST, GeneralColor.YELLOW), costCard1);
        resultExpected.add(new Building(card1,5
                ,new ExchangingGoods(new Goods(new Resources(0,0,0,1),
                new Points(2,0,0)),3)));
        List<Goods> costCard2 = new ArrayList<Goods>(){{add(new Goods(new Resources(0, 0, 0, 4),
                new Points(0, 0, 1)));}};
        BasicDevelopmentCard card2 = new BasicDevelopmentCard(new CardInformation(28, "Teatro",
                PeriodNumber.FIRST, GeneralColor.YELLOW), costCard2);
        resultExpected.add(new Building(card2,3
                ,new ExchangingGoods(new Goods(new Resources(0,2,3,0),
                new Points(4,0,1)),0)));
        List<Building> result = (List<Building>) method.invoke(parserCards, obj);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseCharacter() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseCharacters";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{\"cardInformations\": [{\"name\": \"Condottiero\",\"number\": \"49\",\"period\": \"1\",\"cardColor\": \"blue\"}]," +
                "\"cost\": [{\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"4\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}]}," +
                "" +
                "{\"cardInformations\": [{\"name\": \"Costruttore\",\"number\": \"50\",\"period\": \"1\",\"cardColor\": \"blue\"}]," +
                "\"cost\": [{\"resources\": {\"woods\": \"3\",\"stones\": \"0\",\"servants\": \"1\",\"coins\": \"4\"}," +
                "\"points\": {\"victory\": \"1\",\"military\": \"1\",\"faith\": \"0\"}}]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<Character> resultExpected = new ArrayList<>();
        List<Goods> costCard1 = new ArrayList<Goods>(){{add(new Goods(new Resources(0, 0, 0, 4),
                new Points(0, 0, 0)));}};
        BasicDevelopmentCard card1 = new BasicDevelopmentCard(new CardInformation(49, "Condottiero",
                PeriodNumber.FIRST, GeneralColor.BLUE), costCard1);
        resultExpected.add(new Character(card1));
        List<Goods> costCard2 = new ArrayList<Goods>(){{add(new Goods(new Resources(3,0,1,4),
                new Points(1,1,0)));}};
        BasicDevelopmentCard card2 = new BasicDevelopmentCard(new CardInformation(50, "Costruttore",
                PeriodNumber.FIRST, GeneralColor.BLUE), costCard2);
        resultExpected.add(new Character(card2));
        List<Character> result = (List<Character>) method.invoke(parserCards, obj);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseVentures() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseVentures";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{\"cardInformations\": [{\"name\": \"Ingaggiare reclute\",\"number\": \"73\",\"period\": \"1\",\"cardColor\": \"purple\"}]," +
                "\"cost\": [{\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"4\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}]," +
                "\"requirementsOnCost\": [{" +
                "\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\":{\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}]," +
                "\"endGamePoints\": [{\"victory\": \"4\",\"military\": \"0\",\"faith\": \"0\"}]}," +
                "" +
                "{\"cardInformations\": [{\"name\": \"Riparare la Chiesa\",\"number\": \"76\",\"period\": \"3\",\"cardColor\": \"purple\"}]," +
                "\"cost\": [{\"resources\": {\"woods\": \"3\",\"stones\": \"0\",\"servants\": \"1\",\"coins\": \"4\"}," +
                "\"points\": {\"victory\": \"1\",\"military\": \"1\",\"faith\": \"0\"}}]," +
                "\"requirementsOnCost\": [{" +
                "\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\":{\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}]," +
                "\"endGamePoints\": [{\"victory\": \"25\",\"military\": \"0\",\"faith\": \"0\"}]}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<Venture> resultExpected = new ArrayList<>();
        List<Goods> costCard1 = new ArrayList<Goods>(){{add(new Goods(new Resources(0, 0, 0, 4),
                new Points(0, 0, 0)));}};
        BasicDevelopmentCard card1 = new BasicDevelopmentCard(new CardInformation(73, "Ingaggiare reclute",
                PeriodNumber.FIRST, GeneralColor.PURPLE), costCard1);
        Goods endGameCard1 = new Goods(new Resources(), new Points(4,0,0));
        List<Goods> reqCard1 = new ArrayList<Goods>(){{add(new Goods(new Resources(), new Points()));}};
        resultExpected.add(new Venture(card1, endGameCard1, reqCard1));
        List<Goods> costCard2 = new ArrayList<Goods>(){{add(new Goods(new Resources(3,0,1,4),
                new Points(1,1,0)));}};
        BasicDevelopmentCard card2 = new BasicDevelopmentCard(new CardInformation(76, "Riparare la Chiesa",
                PeriodNumber.THIRD, GeneralColor.PURPLE), costCard2);
        Goods endGameCard2 = new Goods(new Resources(), new Points(25,0,0));
        List<Goods> reqCard2 = new ArrayList<Goods>(){{add(new Goods(new Resources(), new Points()));}};
        resultExpected.add(new Venture(card2, endGameCard2, reqCard2));
        List<Venture> result = (List<Venture>) method.invoke(parserCards, obj);
        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseDevelopmentCard() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseDevelopmentCard";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"cardInformations\": [{\"name\": \"Zecca\",\"number\": \"25\",\"period\": \"1\",\"cardColor\": \"yellow\"}]," +
                "\"cost\": [{\"resources\": {\"woods\": \"1\",\"stones\": \"3\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        CardInformation cardInformation = new CardInformation(25, "Zecca",
                                                PeriodNumber.FIRST, GeneralColor.YELLOW);
        List<Goods> cost = new ArrayList<Goods>(){{add(new Goods(new Resources(1, 3, 0, 0),
                new Points(0, 0, 0)));}};
        BasicDevelopmentCard resultExpected = new BasicDevelopmentCard(cardInformation, cost);
        BasicDevelopmentCard result = (BasicDevelopmentCard) method.invoke(parserCards, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseExchangingGoods() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseExchangingGoods", fieldName = "productionResult";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class, String.class);
        method.setAccessible(true);
        String json = "{\"productionResult\": [" +
                "{\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"1\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"3\",\"faith\": \"0\"}," +
                "\"councilePrivilege\": \"3\"}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        ExchangingGoods resultExpected = new ExchangingGoods(new Goods(new Resources(0,0,0,1),
                new Points(0, 3, 0)), 3);
        ExchangingGoods result = (ExchangingGoods) method.invoke(parserCards, obj, fieldName);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseCardInformation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseCardInformation";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"cardInformations\": [{\"name\": \"Ingaggiare reclute\",\"number\": \"73\"," +
                "\"period\": \"1\",\"cardColor\": \"purple\"}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        CardInformation resultExpected = new CardInformation(73, "Ingaggiare reclute",
                PeriodNumber.FIRST, GeneralColor.PURPLE);
        CardInformation result = (CardInformation) method.invoke(parserCards, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseEndGameRewards() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseEndGameRewards";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"endGamePoints\": [{\"victory\": \"4\",\"military\": \"0\",\"faith\": \"0\"}]}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        Goods resultExpected = new Goods(new Resources(), new Points(4, 0, 0));
        Goods result = (Goods) method.invoke(parserCards, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseExcommunicationTiles() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseExcommunicationTiles";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{\"excommunicationTileDetails\":{\"ExcommunicationTileName\": \"1.1\",\"period\": \"1\"}}," +
                "{\"excommunicationTileDetails\":{\"ExcommunicationTileName\": \"2.1\",\"period\": \"2\"}}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        List<ExcommunicationTile> resultExpected = new ArrayList<ExcommunicationTile>(){{
            add(new ExcommunicationTile("1.1", PeriodNumber.FIRST));
            add(new ExcommunicationTile("2.1", PeriodNumber.SECOND));}};
        List<ExcommunicationTile> result = (List<ExcommunicationTile>) method.invoke(parserCards, obj);
        for(int i=0; i<resultExpected.size(); i++){
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++){
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseSingleExcommunicationTile() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseSingleExcommunicationTile";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{\"ExcommunicationTileName\": \"1.1\",\"period\": \"1\"}";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        ExcommunicationTile resultExpected = new ExcommunicationTile("1.1", PeriodNumber.FIRST);
        ExcommunicationTile result = (ExcommunicationTile) method.invoke(parserCards, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseLeaderCards() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String json = "[{\"name\": \"Francesco Sforza\"," +
                "\"requirements\": " +
                "[{\"developmentCards\": {\"territories\": \"0\",\"buildings\": \"0\",\"characters\": \"0\",\"ventures\": \"5\"}," +
                "\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}], \"abilityType\": \"consumable\"},"+
                "{\"name\": \"Ludovico Ariosto\"," +
                "\"requirements\": " +
                "[{\"developmentCards\": {\"territories\": \"5\",\"buildings\": \"0\",\"characters\": \"0\",\"ventures\": \"0\"}," +
                "\"resources\": {\"woods\": \"0\",\"stones\": \"0\",\"servants\": \"5\",\"coins\": \"0\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"1\"}}], \"abilityType\": \"permanent\"}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        String methodName = "parseLeaderCards";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        List<LeaderCard> resultExpected = new ArrayList<>();
        List<CardsRequired> cardsRequired1 = new ArrayList<CardsRequired>(){{add(
                new CardsRequired(5, GeneralColor.PURPLE));}};
        List<LeaderCost> cost1 = new ArrayList<LeaderCost>(){{add(new LeaderCost(new Goods(new Resources(0, 0, 0, 0),
                new Points(0,0,0)), cardsRequired1));}};
        resultExpected.add(new LeaderCard(new LeaderInformation("Francesco Sforza", LeaderCategory.CONSUMABLE), cost1));
        List<CardsRequired> cardsRequired2 = new ArrayList<CardsRequired>(){{add(
                new CardsRequired(5, GeneralColor.GREEN));}};
        List<LeaderCost> cost2 = new ArrayList<LeaderCost>(){{add(new LeaderCost(new Goods(new Resources(0, 0, 5, 0),
                new Points(0,0,1)), cardsRequired2));}};
        resultExpected.add(new LeaderCard(new LeaderInformation("Ludovico Ariosto", LeaderCategory.PERMANENT), cost2));
        List<LeaderCard> result = (List<LeaderCard>) method.invoke(parserCards, obj);
        for(int i=0; i<resultExpected.size(); i++){
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
    }

    @Test
    void testParseLeaderInformation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseLeaderInformation";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonObject.class);
        method.setAccessible(true);
        String json = "{ \"name\": \"Carta di prova\", \"abilityType\": \"permanent\" }";
        JsonObject obj = (JsonObject) new JsonParser().parse(json);
        LeaderInformation resultExpected = new LeaderInformation("Carta di prova", LeaderCategory.PERMANENT);
        LeaderInformation result = (LeaderInformation) method.invoke(parserCards, obj);
        assertTrue(resultExpected.equals(result));
    }

    @Test
    void testParseLeaderCategory() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseLeaderCategory";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, String.class);
        method.setAccessible(true);
        String[] leaderCategoryId = new String[]{"permanent", "consumable"};
        LeaderCategory[] resultExpected = new LeaderCategory[]{LeaderCategory.PERMANENT, LeaderCategory.CONSUMABLE};
        for(int i=0; i<leaderCategoryId.length; i++) {
            assertEquals(resultExpected[i], method.invoke(parserCards, leaderCategoryId[i]));
        }
    }

    @Test
    void testParseLeaderCosts() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String methodName = "parseLeaderCosts";
        Class targetClass = parserCards.getClass();
        Method method = targetClass.getDeclaredMethod(methodName, JsonArray.class);
        method.setAccessible(true);
        String json = "[{\"developmentCards\": {\"territories\": \"2\",\"buildings\": \"5\",\"characters\": \"0\",\"ventures\": \"0\"}," +
                "\"resources\": {\"woods\": \"1\",\"stones\": \"0\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}," +
                "{\"developmentCards\": {\"territories\": \"0\",\"buildings\": \"6\",\"characters\": \"0\",\"ventures\": \"0\"}," +
                "\"resources\": {\"woods\": \"0\",\"stones\": \"2\",\"servants\": \"0\",\"coins\": \"0\"}," +
                "\"points\": {\"victory\": \"0\",\"military\": \"0\",\"faith\": \"0\"}}]";
        JsonArray obj = (JsonArray) new JsonParser().parse(json);
        LeaderCost cost1 = new LeaderCost(new Goods(new Resources(1, 0, 0, 0), new Points(0,0,0)),
                new ArrayList<CardsRequired>(){{add(new CardsRequired(2, GeneralColor.GREEN));
                    add(new CardsRequired(5, GeneralColor.YELLOW));}});
        LeaderCost cost2 = new LeaderCost(new Goods(new Resources(0, 2, 0, 0), new Points(0,0,0)),
                new ArrayList<CardsRequired>(){{add(new CardsRequired(6, GeneralColor.YELLOW));}});
        List<LeaderCost> resultExpected = new ArrayList<LeaderCost>(){{add(cost1); add(cost2);}};
        CardsRequired card1, card2;
        List<LeaderCost> result = (List<LeaderCost>) method.invoke(parserCards, obj);

        for(int i=0; i<resultExpected.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
        for(int i=0; i<result.size(); i++) {
            assertTrue(resultExpected.get(i).equals(result.get(i)));
        }
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
            assertTrue(resultExpected.get(index).equals(result.get(index)));
        }
        for (int index = 0; index<result.size(); index++) {
            assertTrue(resultExpected.get(index).equals(result.get(index)));
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