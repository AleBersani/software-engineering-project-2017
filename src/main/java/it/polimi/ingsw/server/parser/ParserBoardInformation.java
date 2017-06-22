package it.polimi.ingsw.server.parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.server.gamelogic.basics.ExchangingGoods;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.board.*;
import it.polimi.ingsw.server.gamecontroller.gameelements.BoardInformation;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserBoardInformation {
    private ParserSettings parserSettings;

    public ParserBoardInformation() {
        this.parserSettings = new ParserSettings();
    }

    public void parseTowerMaps() throws IOException {
        JsonObject board = parserSettings.extractJsonObject("Board.json");
        BoardInformation.greenTower = parseSingleTowerMap(board.get("towers")
                                       .getAsJsonObject()
                                        .get("greenTower")
                                        .getAsJsonArray());
        BoardInformation.yellowTower = parseSingleTowerMap(board.get("towers")
                                        .getAsJsonObject()
                                        .get("yellowTower")
                                        .getAsJsonArray());
        BoardInformation.blueTower = parseSingleTowerMap(board.get("towers")
                                      .getAsJsonObject()
                                      .get("blueTower")
                                      .getAsJsonArray());
        BoardInformation.purpleTower = parseSingleTowerMap(board.get("towers")
                                        .getAsJsonObject()
                                        .get("purpleTower")
                                        .getAsJsonArray());
    }

    private Map<Space, Goods> parseSingleTowerMap(JsonArray towerJson) {
        JsonObject object;
        Space space;
        Gson gson = new Gson();
        Goods bonus;
        Map<Space, Goods> towerInformation = new HashMap<>();
        for(int index=0; index < towerJson.size(); index++) {
            object = towerJson.get(index).getAsJsonObject();
            space = parseSpace(object.get("space").getAsJsonObject());
            bonus = gson.fromJson(object.get("instantGoods").getAsJsonObject(), Goods.class);
            towerInformation.put(space, bonus);
        }
        return towerInformation;
    }

    private Space parseSpace(JsonObject space) {
        int requestedValue;
        requestedValue = space.get("requestedValue").getAsInt();
        return new Space(decideBoardId(space), requestedValue);
    }

    private BoardIdentifier decideBoardId(JsonObject space) {
        return BoardIdentifier.valueOf(space.get("boardIdentifier").getAsString());
    }

    public CouncilPalace parseCouncilPalace() throws IOException {
        JsonObject board, councilPalace;
        board = parserSettings.extractJsonObject("Board.json");
        councilPalace = board.get("councilPalace").getAsJsonObject();
        return getParsedCouncilPalace(councilPalace);
    }

    private CouncilPalace getParsedCouncilPalace(JsonObject councilPalace) {
        Gson gson = new Gson();
        ExchangingGoods bonusCouncil;
        int requestedValue;
        bonusCouncil = gson.fromJson(councilPalace.get("instantGoods").getAsJsonObject(), ExchangingGoods.class);
        requestedValue = councilPalace.get("requestedValue").getAsInt();
        return new CouncilPalace(bonusCouncil, requestedValue);
    }

    public void parseBoardActionAreas() throws IOException {
        JsonObject board, bonusActionSpaces;

        board = parserSettings.extractJsonObject("Board.json");
        bonusActionSpaces = board.get("boardActionSpaces").getAsJsonObject();
        parseProductionHarvestLists(BoardInformation.harvestArea, bonusActionSpaces.get("harvestArea").getAsJsonArray());
        parseProductionHarvestLists(BoardInformation.productionArea, bonusActionSpaces.get("productionArea").getAsJsonArray());
        parseMarket(BoardInformation.marketArea, bonusActionSpaces.get("marketArea").getAsJsonArray());
    }

    private void parseProductionHarvestLists(List<ProductionHarvestSpace> productionHarvestArea, JsonArray area) {
        JsonObject object;
        Space space;
        int malusValue;
        for (int index=0; index < area.size(); index++){
            object = area.get(index).getAsJsonObject();
            space = parseSpace(object.get("space").getAsJsonObject());
            malusValue = object.get("malusValue").getAsInt();
            productionHarvestArea.add(new ProductionHarvestSpace(space, malusValue));
        }
    }

    public void parseMarket(List<MarketSpace> marketArea, JsonArray market) {
        Gson gson = new Gson();
        JsonObject object;
        Space space;
        ExchangingGoods exchangingGoods;
        for (int index=0; index < market.size(); index++) {
            object = market.get(index).getAsJsonObject();
            space = parseSpace(object.get("space").getAsJsonObject());
            exchangingGoods = gson.fromJson(object.get("instantGoods").getAsJsonObject(), ExchangingGoods.class);
            marketArea.add(new MarketSpace(space, exchangingGoods));
        }
    }
}
