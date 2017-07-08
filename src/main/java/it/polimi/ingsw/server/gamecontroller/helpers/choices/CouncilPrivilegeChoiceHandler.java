package it.polimi.ingsw.server.gamecontroller.helpers.choices;

import it.polimi.ingsw.server.gameelements.BoardInformation;
import it.polimi.ingsw.server.gamelogic.basics.CouncilPrivilege;
import it.polimi.ingsw.server.gamelogic.basics.GameConfiguration;
import it.polimi.ingsw.server.gamelogic.basics.Goods;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.BasicRewards;
import it.polimi.ingsw.server.gamelogic.modifiers.rewards.modifiers.RewardsModifier;
import it.polimi.ingsw.server.gamelogic.player.Player;
import it.polimi.ingsw.shared.model.actionsdescription.BoardAction;

import java.util.List;

public class CouncilPrivilegeChoiceHandler {
    private List<Integer> positionChoices;
    private Player player;
    private BoardAction boardAction;

    public CouncilPrivilegeChoiceHandler(List<Integer> positionChoices, Player player, BoardAction boardAction) {
        this.positionChoices = positionChoices;
        this.player = player;
        this.boardAction = boardAction;
    }

    public void addModifiedCouncilPrivilegeChoices() {
        Goods goods = new Goods();
        for (Integer integer : positionChoices) {
            goods.addAll(CouncilPrivilege.getPossibleChoices().get(integer));
        }

        BasicRewards basicRewards = new BasicRewards(boardAction.getBasicAction().getActionType(), goods);
        for (RewardsModifier rewardsModifier : player.getRewardsModifiers()) {
            rewardsModifier.modifyRewards(basicRewards);
        }
        player.getPlayerGoods().addAll(basicRewards.calculateFinalRewards());
    }

    public List<Integer> getPositionChoices() {
        return positionChoices;
    }

    public void setPositionChoices(List<Integer> positionChoices) {
        this.positionChoices = positionChoices;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
