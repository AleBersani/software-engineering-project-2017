package it.polimi.ingsw.client.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.client.model.enums.PointsLight;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserLightModel {
    private ParserSettingsClient parserSettingsClient;

    public ParserLightModel() {
        parserSettingsClient = new ParserSettingsClient();
    }
//
//    /**
//     * This method reads from json file and invokes a private method to parse every DevelopmentCardLight
//     * @return A List of DevelopmentCardLight
//     * @throws IOException Can be thrown by parserSettingsClient.extractJsonArray method.
//     */
//    public List<DevelopmentCardLight> parseDevelopmentCardsClient() throws IOException {
//        JsonArray json;
//        json = parserSettingsClient.extractJsonArray("DevelopmentCardsLight.json");
//        return parseListDevelopmentCardLight(json);
//    }
//
//    /**
//     * Private method used for parsing a JsonArray in a List of DevelopmentCardLight
//     * @param cards JsonArray with all the information about DevelopmentCardLight objects.
//     * @return A List of DevelopmentCardLight
//     */
//    private List<DevelopmentCardLight> parseListDevelopmentCardLight(JsonArray cards) {
//        Gson gson = new Gson();
//        JsonObject card;
//        List<String> parsedCosts, parsedPermanentEffectDescription;
//        List<DevelopmentCardLight> parsedCards = new ArrayList<>();
//        for (int i=0; i < cards.size(); i++) {
//            card = cards.get(i)
//                        .getAsJsonObject();
//            parsedCosts = gson.fromJson(card.get("cost").getAsJsonArray(),
//                    new TypeToken<ArrayList<String>>(){}.getType());
//            parsedPermanentEffectDescription = gson.fromJson(card
//                                                                .get("permanentEffectDescription")
//                                                                .getAsJsonArray(),
//                                                            new TypeToken<ArrayList<String>>(){}.getType());
//            parsedCards.add(new DevelopmentCardLight(card.get("number").getAsInt(),  card.get("name").getAsString(),
//                    parsedCosts,
//                    card.get("instantEffectDescription").getAsString(),
//                    parsedPermanentEffectDescription));
//        }
//        return parsedCards;
//    }
//
//    /**
//     * This method reads from json file and invokes a private method to parse every LeaderCardLight
//     * @return A List of LeaderCardLight
//     * @throws IOException Can be thrown by parserSettingsClient.extractJsonArray method.
//     */
//    public List<LeaderCardLight> parseLeaderCardClient() throws IOException {
//        JsonArray json;
//        json = parserSettingsClient.extractJsonArray("LeaderCardLight.json");
//        return parseListLeaderCardLight(json);
//    }
//
//    /**
//     * Private method used for parsing a JsonArray in a List of LeaderCardLight
//     * @param cards JsonArray with all the information about LeaderCardLight objects.
//     * @return A List of LeaderCardLight
//     */
//    private List<LeaderCardLight> parseListLeaderCardLight(JsonArray cards) {
//        Gson gson = new Gson();
//        JsonObject card;
//        List<String> parsedRequirements;
//        List<LeaderCardLight> parsedCards = new ArrayList<>();
//        for (int i = 0; i < cards.size(); i++) {
//            card = cards.get(i)
//                        .getAsJsonObject();
//            parsedRequirements = gson.fromJson(card
//                                                    .get("requirements")
//                                                    .getAsJsonArray(),
//                                                new TypeToken<ArrayList<String>>() {}.getType());
//            parsedCards.add(new LeaderCardLight(card.get("name").getAsString(),
//                                                card.get("effectDescription").getAsString(),
//                                                parsedRequirements));
//        }
//        return parsedCards;
//    }
//
//    /**
//     * This method reads from json file and invokes a private method to parse every ExcommunicationTileLight
//     * @return A List of ExcommunicationTileLight
//     * @throws IOException Can be thrown by parserSettingsClient.extractJsonArray method.
//     */
//    public List<ExcommunicationTileLight> parseExcommunicationTileClient() throws IOException {
//        JsonArray json;
//        json = parserSettingsClient.extractJsonArray("ExcommunicationTileLight.json");
//        return parseListExcommunicationTileLight(json);
//    }
//
//    /**
//     * Private method used for parsing a JsonArray in a List of ExcommunicationTileLight
//     * @param cards JsonArray with all the information about ExcommunicationTileLight objects.
//     * @return A List of ExcommunicationTileLight
//     */
//    private List<ExcommunicationTileLight> parseListExcommunicationTileLight(JsonArray cards) {
//        JsonObject card;
//        List<ExcommunicationTileLight> parsedCards = new ArrayList<>();
//        for (int i = 0; i < cards.size(); i++) {
//            card = cards.get(i)
//                    .getAsJsonObject();
//            parsedCards.add(new ExcommunicationTileLight(
//                    card.get("name").getAsString(),
//                    card.get("effectDescription").getAsString()));
//        }
//        return parsedCards;
//    }
//
//    /**
//     * Public method that parses a BoardLight object from Json.
//     * @return A BoardLight object.
//     * @throws IOException Can be thrown by parserSettingsClient.extractJsonObject.
//     */
//    public BoardLight parseBoardLight() throws IOException {
//        JsonObject board;
//        board = parserSettingsClient.extractJsonObject("BoardLight.json");
//        return realParseBoardLight(board);
//    }
//
//    /**
//     * Private auxiliary method that is invoked by the previous (parseBoardLight()), that receives a JsonObject
//     * representing the board Json and parses a BoardLight object from it.
//     * @param board A JsonObject representing board Json.
//     * @return A BoardLight object.
//     */
//    private BoardLight realParseBoardLight(JsonObject board) {
//        List<SlotLight> greenTower;
//        List<SlotLight> yellowTower;
//        List<SlotLight> blueTower;
//        List<SlotLight> purpleTower;
//        List<SlotLight> market;
//        List<SlotLight> harvest;
//        List<SlotLight> production;
//        List<SlotLight> councilPalace;
//        greenTower = parseListSlotLight(board.get("greenTower").getAsJsonArray());
//        yellowTower = parseListSlotLight(board.get("yellowTower").getAsJsonArray());
//        blueTower = parseListSlotLight(board.get("blueTower").getAsJsonArray());
//        purpleTower = parseListSlotLight(board.get("purpleTower").getAsJsonArray());
//        market = parseListSlotLight(board.get("market").getAsJsonArray());
//        harvest = parseListSlotLight(board.get("harvest").getAsJsonArray());
//        production = parseListSlotLight(board.get("production").getAsJsonArray());
//        councilPalace = parseListSlotLight(board.get("councilPalace").getAsJsonArray());
//        return new BoardLight(greenTower, yellowTower, blueTower, purpleTower,
//                                market, harvest, production, councilPalace);
//    }
//
//    /**
//     * Private auxiliary method that parses from a JsonArray and returns a List of SlotLight.
//     * @param spacesLight JsonArray representing the elements to be parsed and added to the List.
//     * @return A List of SlotLight.
//     */
//    private List<SlotLight> parseListSlotLight(JsonArray spacesLight) {
//        JsonObject singleSpace;
//        List<SlotLight> parsedSlotLightList = new ArrayList<>();
//        for (int i=0; i < spacesLight.size(); i++) {
//            singleSpace = spacesLight.get(i).getAsJsonObject();
//            parsedSlotLightList.add(parseSingleSlotLight(singleSpace));
//        }
//        return parsedSlotLightList;
//    }
//
//    /**
//     * Private auxiliary method that parses from a JsonObject and returns a SlotLight object.
//     * @param singleSpace JsonObject representing the information of the SlotLight object to be instantiated.
//     * @return A SlotLight object.
//     */
//    private SlotLight parseSingleSlotLight(JsonObject singleSpace) {
//        Map<PointsLight, Integer> bonus = parseBonusMap(singleSpace.get("bonus").getAsJsonObject());
//        return new SlotLight(BoardIdentifier.valueOf(singleSpace.get("boardIdentifier").getAsString()), bonus,
//                            singleSpace.get("cost").getAsInt(), singleSpace.get("malus").getAsInt());
//    }
//
//    /**
//     * Private auxiliary method that parses from a JsonObject and returns a Map of GoodsLight and Integer representing
//     * bonuses to be contained in the Map.
//     * @param bonus JsonObject representing the information to be added to the Map of GoodsLight and Integer.
//     * @return A Map of GoodsLight and Integer.
//     */
//    private Map<PointsLight, Integer> parseBonusMap(JsonObject bonus) {
//        Map<PointsLight, Integer> bonusMap = new HashMap<>();
//        bonusMap.put(PointsLight.WOODS, bonus.get("woods").getAsInt());
//        bonusMap.put(PointsLight.STONES, bonus.get("stones").getAsInt());
//        bonusMap.put(PointsLight.MILITARY_POINTS, bonus.get("military").getAsInt());
//        bonusMap.put(PointsLight.COINS, bonus.get("coins").getAsInt());
//        bonusMap.put(PointsLight.SERVANTS, bonus.get("servants").getAsInt());
//        bonusMap.put(PointsLight.COUNCIL_PRIVILEGE, bonus.get("councilPrivilege").getAsInt());
//        return bonusMap;
//    }
}
