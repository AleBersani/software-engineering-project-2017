package it.polimi.ingsw.server;

import it.polimi.ingsw.server.gamecontroller.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActiveGames {
    private List<Game> games;

    private ActiveGames() {
        games = new ArrayList<>();
    }

    private static class ActiveGamesHolder {
        private static final ActiveGames INSTANCE = new ActiveGames();
    }

    public static ActiveGames getInstance() {
        return ActiveGamesHolder.INSTANCE;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public Optional<Game> getGameById(int gameId) {
        for (Game game : games) {
            if (game.getGameId() == gameId) {
                return Optional.of(game);
            }
        }
        return Optional.empty();
    }
}
