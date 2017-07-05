package it.polimi.ingsw.client.cli.gameinformation;

import it.polimi.ingsw.client.cli.model.BoardSpaceDescriptionLight;
import it.polimi.ingsw.client.cli.model.BonusTileDescriptionLight;
import it.polimi.ingsw.shared.model.BoardIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardOwnerInformation {
    private static List<BoardSpaceDescriptionLight> boardSpaceDescriptionLights;
    private static List<BonusTileDescriptionLight> possibleBonusTiles;

    public static void initLists() {
        boardSpaceDescriptionLights = new ArrayList<>();
        possibleBonusTiles = new ArrayList<>();
    }

    private BoardOwnerInformation() {
    }

    /**
     * Method that searches for a Board Space Description Light in a static List through stream, filtering by
     * board identifier. The first result with same identifier as "boardIdentifier", is returned.
     * @param boardIdentifier Space identifier
     * @return The BoardSpaceDescriptionLight object to be printed.
     */
    public static Optional<BoardSpaceDescriptionLight> searchForBoardSpaceLight(BoardIdentifier boardIdentifier) {
        return boardSpaceDescriptionLights.stream()
                                          .filter(T -> boardIdentifier.equals(T.getBoardIdentifier()))
                                          .findFirst();
    }

    /**
     * Method that searches for a Bonus Tile Description Light in a static List through stream, filtering by
     * bonus tile identifier. The first result with same identifier as "bonusTileIdentifier", is returned.
     * @param bonusTileIdentifier Bonus Tile identifier
     * @return The BonusTileDescriptionLight object to be printed.
     */
    public static Optional<BonusTileDescriptionLight> searchForBonusTileLight(String bonusTileIdentifier) {
        return possibleBonusTiles.stream()
                                 .filter(T ->  bonusTileIdentifier.equals(T.getBonusTileIdentifier()))
                                 .findFirst();
    }

    public static List<BoardSpaceDescriptionLight> getBoardSpaceDescriptionLights() {
        return boardSpaceDescriptionLights;
    }

    public static void setBoardSpaceDescriptionLights(List<BoardSpaceDescriptionLight> boardSpaceDescriptionLights) {
        BoardOwnerInformation.boardSpaceDescriptionLights = boardSpaceDescriptionLights;
    }

    public static List<BonusTileDescriptionLight> getPossibleBonusTiles() {
        return possibleBonusTiles;
    }

    public static void setPossibleBonusTiles(List<BonusTileDescriptionLight> possibleBonusTiles) {
        BoardOwnerInformation.possibleBonusTiles = possibleBonusTiles;
    }
}
