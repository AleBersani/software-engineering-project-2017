package it.polimi.ingsw.client.cli.gameinformation;

import it.polimi.ingsw.client.cli.model.BoardSpaceDescriptionLight;
import it.polimi.ingsw.client.cli.model.BonusTileDescriptionLight;
import it.polimi.ingsw.shared.model.BoardIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardOwnerInformationTest {
    @BeforeEach
    void setUp() {
        BoardOwnerInformation.initLists();
        BoardSpaceDescriptionLight boardSpaceDescriptionLight1 = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_4,
                                    "4C", 1,2);
        BoardSpaceDescriptionLight boardSpaceDescriptionLight2 = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_2,
                "4C", 2,1);
        BoardOwnerInformation.getBoardSpaceDescriptionLights().add(boardSpaceDescriptionLight1);
        BoardOwnerInformation.getBoardSpaceDescriptionLights().add(boardSpaceDescriptionLight2);
        BonusTileDescriptionLight bonusTileDescriptionLight1 = new BonusTileDescriptionLight("B_TILE_1",
                "4C");
        BonusTileDescriptionLight bonusTileDescriptionLight2 =  new BonusTileDescriptionLight("B_TILE_2",
                "4Se");
        BoardOwnerInformation.getPossibleBonusTiles().add(bonusTileDescriptionLight1);
        BoardOwnerInformation.getPossibleBonusTiles().add(bonusTileDescriptionLight2);
     }

    @Test
    void testSearchForBoardSpaceLight() {
        Optional<BoardSpaceDescriptionLight> result;
        BoardSpaceDescriptionLight resultExpected = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_2,
                "4C", 2,1);
        result = BoardOwnerInformation.searchForBoardSpaceLight(BoardIdentifier.T_G_2);
        assertTrue(result.isPresent());
        result.ifPresent(boardSpaceDescriptionLight -> assertEquals(resultExpected, boardSpaceDescriptionLight));
    }

    @Test
    void testSearchForBonusTileLight() {
        Optional<BonusTileDescriptionLight> result;
        BonusTileDescriptionLight resultExpected = new BonusTileDescriptionLight("B_TILE_2",
                "4Se");
        result = BoardOwnerInformation.searchForBonusTileLight("B_TILE_2");
        assertTrue(result.isPresent());
        result.ifPresent(bonusTileDescriptionLight -> assertEquals(resultExpected, bonusTileDescriptionLight));
    }

    @Test
    void testGetBoardSpaceDescriptionLights() {
        BoardSpaceDescriptionLight boardSpaceDescriptionLight1 = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_2,
                "4C", 1,2);
        BoardSpaceDescriptionLight boardSpaceDescriptionLight2 = new BoardSpaceDescriptionLight(BoardIdentifier.T_G_3,
                "4C", 2,1);
        List<BoardSpaceDescriptionLight> boardSpaceDescriptionLights = new ArrayList<>();
        boardSpaceDescriptionLights.add(boardSpaceDescriptionLight1);
        boardSpaceDescriptionLights.add(boardSpaceDescriptionLight2);
        BoardOwnerInformation.setBoardSpaceDescriptionLights(boardSpaceDescriptionLights);
        assertEquals(boardSpaceDescriptionLights, BoardOwnerInformation.getBoardSpaceDescriptionLights());
    }

    @Test
    void testGetPossibleBonusTiles() {
        BonusTileDescriptionLight bonusTileDescriptionLight1 = new BonusTileDescriptionLight("B_TILE_3",
                "4Mp");
        BonusTileDescriptionLight bonusTileDescriptionLight2 =  new BonusTileDescriptionLight("B_TILE_4",
                "4S");
        List<BonusTileDescriptionLight> bonusTileDescriptionLights = new ArrayList<>();
        bonusTileDescriptionLights.add(bonusTileDescriptionLight1);
        bonusTileDescriptionLights.add(bonusTileDescriptionLight2);
        BoardOwnerInformation.setPossibleBonusTiles(bonusTileDescriptionLights);
        assertEquals(bonusTileDescriptionLights, BoardOwnerInformation.getPossibleBonusTiles());
    }
}