package org.cappuccino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameParser {
    private static final String INIT_GAME_PATTERN = "InitGame:";
    private static final String KILL_PATTERN = "Kill:";
    private static final String SHUTDOWN_GAME_PATTERN = "ShutdownGame:";

    public List<Game> parseGames(InputStream inputStream) throws IOException {
        List<Game> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Game currentGame = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains(INIT_GAME_PATTERN)) {
                    currentGame = new Game();
                    games.add(currentGame);
                    log.debug("New game initialized");
                } else if (line.contains(KILL_PATTERN) && currentGame != null) {
                    killProcessor.processKill(line, currentGame);
                } else if (line.contains(SHUTDOWN_GAME_PATTERN)) {
                    log.debug("Game shut down");
                    currentGame = null;
                }
            }
        }
        return games;
    }
}
