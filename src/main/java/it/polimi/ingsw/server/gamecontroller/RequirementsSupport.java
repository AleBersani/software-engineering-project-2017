package it.polimi.ingsw.server.gamecontroller;

import it.polimi.ingsw.server.gamelogic.modifiers.requirements.BoardActionRequirements;
import it.polimi.ingsw.server.gamelogic.modifiers.requirements.TowerActionRequirements;

import java.util.Optional;

public class RequirementsSupport {
    private TowerActionRequirements towerActionRequirements;
    private BoardActionRequirements boardActionRequirements;

    public RequirementsSupport(TowerActionRequirements towerActionRequirements) {
        this.towerActionRequirements = towerActionRequirements;
        boardActionRequirements = null;
    }

    public RequirementsSupport(BoardActionRequirements boardActionRequirements) {
        this.boardActionRequirements = boardActionRequirements;
        towerActionRequirements = null;
    }

    public Optional<TowerActionRequirements> getTowerActionRequirements() {
        if (towerActionRequirements == null) {
            return Optional.empty();
        }
        return Optional.of(towerActionRequirements);
    }

    public void setTowerActionRequirements(TowerActionRequirements towerActionRequirements) {
        this.towerActionRequirements = towerActionRequirements;
    }

    public Optional<BoardActionRequirements> getBoardActionRequirements() {
        if (boardActionRequirements == null) {
            return Optional.empty();
        }
        return Optional.of(boardActionRequirements);
    }

    public void setBoardActionRequirements(BoardActionRequirements boardActionRequirements) {
        this.boardActionRequirements = boardActionRequirements;
    }
}
