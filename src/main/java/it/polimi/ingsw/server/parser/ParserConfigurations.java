package it.polimi.ingsw.server.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.server.gamelogic.basics.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing methods that set game configuration information, parsing from Json files.
 */
public class ParserConfigurations {
    private static final String GAME_CONFIGURATION_JSON =  "GameConfiguration.json";
    private static final String PLAYER_CONFIGURATION_JSON = "PlayerConfiguration.json";

    private ParserSettings settings;

    public ParserConfigurations() {
        settings = new ParserSettings();
    }

    /**
     * Method that sets GameConfiguration attributes, parsing from Json file.
     * @throws IOException //
     */
    public void parseGameConfiguration() throws IOException {
        JsonObject gameConf = settings.extractJsonObject(GAME_CONFIGURATION_JSON);
        GameConfiguration.setMaxNumberOfPlayer(gameConf.get("maxNumberOfPlayer").getAsInt());
        GameConfiguration.setMoveTimeout(gameConf.get("moveTimeout").getAsInt());
        GameConfiguration.setStartingGameTimeout(gameConf.get("startingGameTimeout").getAsInt());
        GameConfiguration.setNumberOfPeriods(gameConf.get("numberOfPeriods").getAsInt());
    }

    /**
     * Method that sets BoardConfiguration attributes, parsing from Json.
     * @throws IOException //
     */
    public void parseBoardConfiguration() throws IOException {
        JsonObject boardConf = settings.extractJsonObject(GAME_CONFIGURATION_JSON);
        BoardConfiguration.setMaxFaithPoints(boardConf.get("maxFaithPoints").getAsInt());
        BoardConfiguration.setMaxMilitaryPoints(boardConf.get("maxMilitaryPoints").getAsInt());
        BoardConfiguration.setNumberOfDices(boardConf.get("numberOfDices").getAsInt());
        BoardConfiguration.setNumberOfSlotsForTowers(boardConf.get("numberOfSlotsForTowers").getAsInt());
        BoardConfiguration.setNumberOfTowers(boardConf.get("numberOfTowers").getAsInt());
    }

    /**
     * Method that sets CouncilPrivilege attributes, parsing from Json.
     * @throws IOException Possibly thrown by settings.extractJsonObject
     */
    public void parseCouncilPrivilege() throws IOException {
        JsonObject councilConf = settings.extractJsonObject(GAME_CONFIGURATION_JSON);
        Gson gson = new Gson();
        JsonArray choices = councilConf.get("possibleChoices").getAsJsonArray();
        List<Goods> possibleChoices = gson.fromJson(choices, new TypeToken<ArrayList<Goods>>(){}.getType());
        CouncilPrivilege.setPossibleChoices(possibleChoices);
    }

    /**
     * Method that sets PlayerConfiguration attributes, parsing from Json.
     * @throws IOException Possibly thrown by settings.extractJsonObject
     */
    public void parsePlayerConfiguration() throws IOException {
        JsonObject playerConf = settings.extractJsonObject(PLAYER_CONFIGURATION_JSON);
        PlayerConfiguration.setNumberOfLeaders(playerConf.get("numberOfLeaders").getAsInt());
        PlayerConfiguration.setMaxNumberOfDevCardsForCategory(
                playerConf.get("maxNumberOfDevCardsForCategory").getAsInt());
        PlayerConfiguration.setNumberOfPawns(playerConf.get("numberOfPawns").getAsInt());
        PlayerConfiguration.setStartingGoods(parseStartingGoods(playerConf.get("startingGoods").getAsJsonArray()));
        PlayerConfiguration.setRequiredPointsForTerritories(parseRequiredPointsTerritoriesMap(
                playerConf.get("requiredPointsForTerritories").getAsJsonArray()));
    }

    /**
     * Auxiliary private method that parses a JsonArray to a List of Goods, useful for previous method.
     * @param startingGoods JsonArray from which method parses information.
     * @return A List of Goods.
     */
    private List<Goods> parseStartingGoods(JsonArray startingGoods) {
        JsonObject object;
        Gson gson = new Gson();
        Resources resources;
        Points points;
        List<Goods> result = new ArrayList<>();
        for (int i = 0; i < startingGoods.size(); i++){
            object = startingGoods.get(i).getAsJsonObject();
            resources = gson.fromJson(object.get("resources").getAsJsonObject(), Resources.class);
            points = gson.fromJson(object.get("points").getAsJsonObject(), Points.class);
            result.add(new Goods(resources, points));
        }
        return result;
    }

    /**
     * Auxiliary private method that parses a JsonArray to a Map of Integer - Points, useful for previous method.
     * @param requiredPointsForTerritories JsonArray from which method parses information.
     * @return A Map of Integer - Points.
     */
    private Map<Integer, Points> parseRequiredPointsTerritoriesMap(JsonArray requiredPointsForTerritories) {
        JsonObject object;
        Gson gson = new Gson();
        Points points;
        Map<Integer, Points> result = new HashMap<>();
        for (int i = 0; i < requiredPointsForTerritories.size(); i++) {
            object = requiredPointsForTerritories.get(i).getAsJsonObject();
            points = gson.fromJson(object.get("points").getAsJsonObject(), Points.class);
            result.put(object.get("numberOfTerritories").getAsInt(), points);
        }
        return result;
    }


}
