package it.polimi.ingsw.gamelogic.modifiers;

import it.polimi.ingsw.gamelogic.enums.BoardIdentifier;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: JavaDoc
 */
public class AvailableIdentifiers {
    private List<BoardIdentifier> boardIdentifiers;

    public AvailableIdentifiers() {
        boardIdentifiers = new ArrayList<>();
    }

    public AvailableIdentifiers(BoardIdentifier boardIdentifier) {
        boardIdentifiers = new ArrayList<>();
        boardIdentifiers.add(boardIdentifier);
    }

    public AvailableIdentifiers(List<BoardIdentifier> boardIdentifiers) {
        this.boardIdentifiers = boardIdentifiers;
    }

    /**
     * TODO: JavaDoc
     * @param boardIdentifiers
     * @return
     */
    public boolean hasBoardIdentifiers(List<BoardIdentifier> boardIdentifiers) {
        return boardIdentifiers.stream()
                .allMatch(boardIdentifier -> hasBoardIdentifier(boardIdentifier));
    }

    /**
     * TODO: JavaDoc
     * @param boardIdentifierToVerify
     * @return
     */
    public boolean hasBoardIdentifier(BoardIdentifier boardIdentifierToVerify) {
        return boardIdentifiers.stream()
                .anyMatch(boardIdentifier -> boardIdentifier.equals(boardIdentifierToVerify));
    }
}
