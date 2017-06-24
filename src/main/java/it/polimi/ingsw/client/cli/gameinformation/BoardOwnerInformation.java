package it.polimi.ingsw.client.cli.gameinformation;

import it.polimi.ingsw.client.cli.model.BoardSpaceDescriptionLight;
import it.polimi.ingsw.client.cli.model.BonusTileDescriptionLight;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.ArrayList;
import java.util.List;

public class BoardOwnerInformation {
    public static List<BoardSpaceDescriptionLight> boardSpaceDescriptionLights;
    public static List<BonusTileDescriptionLight> possibleBonusTiles;

    public static void initLists() {
        boardSpaceDescriptionLights = new ArrayList<>();
        possibleBonusTiles = new ArrayList<>();
    }

    /**
     * Method that searches for a Board Space Description Light in a static List through stream, filtering by
     * board identifier. The first result with same identifier as "boardIdentifier", is returned.
     * @param boardIdentifier Space identifier
     * @return The BoardSpaceDescriptionLight object to be printed.
     */
    public static BoardSpaceDescriptionLight searchForBoardSpaceLight(BoardIdentifier boardIdentifier) {
        return boardSpaceDescriptionLights.stream()
                                          .filter(T -> boardIdentifier.equals(T.getBoardIdentifier()))
                                          .findFirst()
                                          .get();
    }

    /**
     * Method that searches for a Bonus Tile Description Light in a static List through stream, filtering by
     * bonus tile identifier. The first result with same identifier as "bonusTileIdentifier", is returned.
     * @param bonusTileIdentifier Bonus Tile identifier
     * @return The BonusTileDescriptionLight object to be printed.
     */
    public static BonusTileDescriptionLight searchForBonusTileLight(String bonusTileIdentifier) {
        return possibleBonusTiles.stream()
                                 .filter(T ->  bonusTileIdentifier.equals(T.getBonusTileIdentifier()))
                                 .findFirst()
                                 .get();
    }
}
