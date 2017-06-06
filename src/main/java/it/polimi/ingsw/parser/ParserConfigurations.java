package it.polimi.ingsw.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.gamelogic.basics.BoardConfiguration;
import it.polimi.ingsw.gamelogic.basics.CouncilePrivilege;
import it.polimi.ingsw.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.gamelogic.basics.Goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserConfigurations {
    private ParserSettings settings;

    public ParserConfigurations() {
        settings = new ParserSettings();
    }
    public GameConfiguration parseGameConfiguration() throws IOException {
        JsonObject gameConf = settings.extractJsonObject("GameConfiguration.json");
        GameConfiguration game = new GameConfiguration();
        game.setMaxNumberOfPlayer(gameConf.get("maxNumberOfPlayer").getAsInt());
        game.setMoveTimeout(gameConf.get("moveTimeout").getAsInt());
        game.setStartingGameTimeout(gameConf.get("startingGameTimeout").getAsInt());
        game.setNumberOfPeriods(gameConf.get("numberOfPeriods").getAsInt());
        return game;
    }
    public BoardConfiguration parseBoardConfiguration() throws IOException {
        JsonObject boardConf = settings.extractJsonObject("GameConfiguration.json");
        BoardConfiguration boardConfiguration = new BoardConfiguration();
        boardConfiguration.setMaxFaithPoints(boardConf.get("maxFaithPoints").getAsInt());
        boardConfiguration.setMaxMilitaryPoints(boardConf.get("maxMilitaryPoints").getAsInt());
        boardConfiguration.setNumberOfDices(boardConf.get("numberOfDices").getAsInt());
        boardConfiguration.setNumberOfSlotsForTowers(boardConf.get("numberOfSlotsForTowers").getAsInt());
        boardConfiguration.setNumberOfTowers(boardConf.get("numberOfTowers").getAsInt());
        return boardConfiguration;
    }

    public CouncilePrivilege parseCouncilePrivilege() throws IOException {
        JsonObject councileConf = settings.extractJsonObject("GameConfiguration.json");
        Gson gson = new Gson();
        CouncilePrivilege councilePrivilege = new CouncilePrivilege();
        JsonArray choices = councileConf.get("possibleChoices").getAsJsonArray();
        List<Goods> possibleChoices = gson.fromJson(choices, new TypeToken<ArrayList<Goods>>(){}.getType());
        councilePrivilege.setPossibleChoices(possibleChoices);
        return councilePrivilege;
    }
}
