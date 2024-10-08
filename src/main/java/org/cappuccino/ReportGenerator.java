package org.cappuccino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * The ReportGenerator class generates various reports based on the parsed game data.
 * This class is responsible for creating game reports, player rankings, and death cause reports.
 */
@Slf4j
public class ReportGenerator {
    /**
     * Generates detailed reports for each game.
     *
     * @param games the list of games to generate reports for.
     */
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

    /**
     * Generates and prints a player ranking report based on the total kills across all games.
     *
     * @param games A list of Game objects to generate the player ranking from.
     */
    public void generatePlayerRanking(List<Game> games) {
        log.info("Generating player ranking");
        Map<String, Integer> totalKills = new HashMap<>();

        for (Game game : games) {
            for (Map.Entry<String, Integer> entry : game.getKills().entrySet()) {
                totalKills.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> sortedKills = new ArrayList<>(totalKills.entrySet());
        sortedKills.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        System.out.println("Player Ranking:");
        for (int i = 0; i < sortedKills.size(); i++) {
            Map.Entry<String, Integer> entry = sortedKills.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + ": " + entry.getValue() + " kills");
        }
    }

    /**
     * Generates and prints a death cause report, ranking the causes of death by frequency.
     *
     * @param games A list of Game objects to generate the death cause report from.
     */
    public void generateDeathCauseReport(List<Game> games) {
        log.info("Generating death cause report");
        Map<String, Integer> totalDeathsByMeans = new HashMap<>();

        for (Game game : games) {
            for (Map.Entry<String, Integer> entry : game.getDeathsByMeans().entrySet()) {
                totalDeathsByMeans.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> sortedDeathsByMeans = new ArrayList<>(totalDeathsByMeans.entrySet());
        sortedDeathsByMeans.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        System.out.println("Death Cause Ranking:");
        for (Map.Entry<String, Integer> entry : sortedDeathsByMeans) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " deaths");
        }
    }
}
