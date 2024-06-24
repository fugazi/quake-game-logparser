package org.cappuccino;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReportGenerator {
    public void generateGameReports(List<Game> games) {
        log.info("Generating game reports");
        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);
            System.out.println("game_" + (i + 1) + ": {");
            System.out.println("  total_kills: " + game.getTotalKills() + ",");
            System.out.println("  players: " + game.getPlayers() + ",");
            System.out.println("  kills: {");
            for (Map.Entry<String, Integer> entry : game.getKills().entrySet()) {
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("  }");
            System.out.println("}");
            System.out.println();
        }
    }
}
