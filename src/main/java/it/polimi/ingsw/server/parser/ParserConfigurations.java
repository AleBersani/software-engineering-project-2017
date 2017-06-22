package it.polimi.ingsw.server.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.server.gamelogic.basics.BoardConfiguration;
import it.polimi.ingsw.server.gamelogic.basics.CouncilPrivilege;
import it.polimi.ingsw.server.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.server.gamelogic.basics.Goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing methods that set game configuration information, parsing from Json files.
 */
public class ParserConfigurations {
    private static final String GAME_CONFIGURATION_JSON =  "GameConfiguration.json";

    private ParserSettings settings;

    public ParserConfigurations() {
        settings = new ParserSettings();
    }

    /**
     * Method that sets GameConfiguration attributes, parsing from Json file.
     * @return A GameConfiguration object.
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
     * @return A BoardConfiguration object.
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
     * Method that sets CouncilePrivilege attributes, parsing from Json.
     * @return //
     * @throws IOException //
     */
    public void parseCouncilePrivilege() throws IOException {
        JsonObject councileConf = settings.extractJsonObject(GAME_CONFIGURATION_JSON);
        Gson gson = new Gson();
        JsonArray choices = councileConf.get("possibleChoices").getAsJsonArray();
        List<Goods> possibleChoices = gson.fromJson(choices, new TypeToken<ArrayList<Goods>>(){}.getType());
        CouncilPrivilege.setPossibleChoices(possibleChoices);
    }
}
