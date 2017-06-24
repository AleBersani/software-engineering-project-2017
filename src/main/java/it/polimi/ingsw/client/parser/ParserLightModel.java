package it.polimi.ingsw.client.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.polimi.ingsw.client.cli.gameinformation.BoardOwnerInformation;
import it.polimi.ingsw.client.cli.gameinformation.CardsInformation;
import it.polimi.ingsw.client.cli.model.*;
import it.polimi.ingsw.server.gamecontroller.gameelements.BoardInformation;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.io.IOException;
import java.util.List;

public class ParserLightModel implements Runnable {
    private ParserSettingsClient parserSettingsClient;

    public ParserLightModel() {
        parserSettingsClient = new ParserSettingsClient();
    }

    @Override
    public void run() {
        try {
            completeParseCLI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void completeParseCLI() throws IOException {
        parseBoardOwner();
        parseCardsInformation();
    }

    public void parseBoardOwner() throws IOException {
        JsonObject boardOwnerInformation;
        boardOwnerInformation = parserSettingsClient.extractJsonObject("BoardLight.json");
        BoardOwnerInformation.initLists();
        parseBoardInformation(boardOwnerInformation.get("boardInformation").getAsJsonArray(),
                              BoardOwnerInformation.boardSpaceDescriptionLights);
        parseBonusTilesInformation(boardOwnerInformation.get("bonusTiles").getAsJsonArray(),
                                   BoardOwnerInformation.possibleBonusTiles);
    }

    private void parseBoardInformation(JsonArray boardInformation, List<BoardSpaceDescriptionLight> boardSpaces) {
        JsonObject boardSpace;
        BoardSpaceDescriptionLight parsedBoardSpace;
        for (int i=0; i < boardInformation.size(); i++) {
            boardSpace = boardInformation.get(i).getAsJsonObject();
            parsedBoardSpace = parseSingleBoardSpace(boardSpace);
            boardSpaces.add(parsedBoardSpace);
        }
    }

    private BoardSpaceDescriptionLight parseSingleBoardSpace(JsonObject boardSpace) {
        return new BoardSpaceDescriptionLight(BoardIdentifier.valueOf(boardSpace.get("boardIdentifier").getAsString()),
                                                boardSpace.get("bonus").getAsString(),
                                                boardSpace.get("cost").getAsInt(),
                                                boardSpace.get("malus").getAsInt());
    }

    private void parseBonusTilesInformation(JsonArray bonusTiles, List<BonusTileDescriptionLight> possibleBonusTiles) {
        JsonObject bonusTile;
        BonusTileDescriptionLight parsedBonusTile;
        for (int i=0; i < bonusTiles.size(); i++) {
            bonusTile = bonusTiles.get(i).getAsJsonObject();
            parsedBonusTile = parseSingleBonusTile(bonusTile);
            possibleBonusTiles.add(parsedBonusTile);
        }
    }

    private BonusTileDescriptionLight parseSingleBonusTile(JsonObject bonusTile) {
        return new BonusTileDescriptionLight(bonusTile.get("name").getAsString(),
                                             bonusTile.get("description").getAsString());
    }

    public void parseCardsInformation() throws IOException {
        JsonArray developmentCardsLight, excommunicationTilesLight,  leaderCardsLight;
        developmentCardsLight = parserSettingsClient.extractJsonArray("DevelopmentCardsLight.json");
        excommunicationTilesLight = parserSettingsClient.extractJsonArray("ExcommunicationTileLight.json");
        leaderCardsLight = parserSettingsClient.extractJsonArray("LeaderCardLight.json");
        CardsInformation.initLists();
        parseDevelopmentCardsLight(developmentCardsLight, CardsInformation.developmentCardsLights);
        parseExcommunicationTilesLight(excommunicationTilesLight, CardsInformation.excommunicationTileLights);
        parseLeaderCardsLight(leaderCardsLight, CardsInformation.leaderCardsLights);
    }

    private void parseDevelopmentCardsLight(JsonArray developmentCardsLight,
                                            List<DevelopmentCardsLight> developmentCardsLights) {
        JsonObject devCard;
        DevelopmentCardsLight parsedDevCard;
        for (int i=0; i < developmentCardsLight.size(); i++) {
            devCard = developmentCardsLight.get(i).getAsJsonObject();
            parsedDevCard = parseSingleDevCard(devCard);
            developmentCardsLights.add(parsedDevCard);
        }
    }

    private DevelopmentCardsLight parseSingleDevCard(JsonObject devCard) {
        return new DevelopmentCardsLight(devCard.get("name").getAsString(),
                                         devCard.get("instantEffectDescription").getAsString(),
                                         devCard.get("permanentEffectDescription").getAsJsonArray().get(0).getAsString(),
                                         devCard.get("cost").getAsJsonArray().get(0).getAsString());
    }

    private void parseExcommunicationTilesLight(JsonArray excommunicationTilesLight,
                                                List<ExcommunicationTileLight> excommunicationTileLights) {
        JsonObject excommTile;
        ExcommunicationTileLight parsedExcommTile;
        for (int i=0; i < excommunicationTilesLight.size(); i++) {
            excommTile = excommunicationTilesLight.get(i).getAsJsonObject();
            parsedExcommTile = parseSingleExcommunicationTile(excommTile);
            excommunicationTileLights.add(parsedExcommTile);
        }
    }

    private ExcommunicationTileLight parseSingleExcommunicationTile(JsonObject excommTile) {
        return new ExcommunicationTileLight(excommTile.get("name").getAsString(),
                                            excommTile.get("effectDescription").getAsString());
    }

    private void parseLeaderCardsLight(JsonArray leaderCardsLight, List<LeaderCardLight> leaderCardsLights) {
        JsonObject leaderCard;
        LeaderCardLight parsedLeaderCard;
        for (int i=0; i < leaderCardsLight.size(); i++) {
            leaderCard = leaderCardsLight.get(i).getAsJsonObject();
            parsedLeaderCard = parseSingleLeaderCardLight(leaderCard);
            leaderCardsLights.add(parsedLeaderCard);
        }
    }

    private LeaderCardLight parseSingleLeaderCardLight(JsonObject leaderCard) {
        return new LeaderCardLight( leaderCard.get("name").getAsString(),
                                    leaderCard.get("effectDescription").getAsString(),
                                    leaderCard.get("requirements").getAsJsonArray().get(0).getAsString());
    }

}
