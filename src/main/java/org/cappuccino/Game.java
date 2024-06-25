package org.cappuccino;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

/**
 * The Game class represents a single game session with its associated statistics.
 * This class keeps track of kills, players, and death causes for a game.
 */
@Getter
public class Game {
    private int totalKills;
    private final Set<String> players;
    private final Map<String, Integer> kills;
    private final Map<String, Integer> deathsByMeans;

    /**
     * Constructs a new Game instance with initialized collections for tracking game statistics.
     */
    public Game() {
        this.totalKills = 0;
        this.players = new HashSet<>();
        this.kills = new HashMap<>();
        this.deathsByMeans = new HashMap<>();
    }

    /**
     * Increments the total kill count by one.
     */
    public void addKill() {
        totalKills++;
    }

    /**
     * Adds a player to the game.
     *
     * @param player the player's name.
     */
    public void addPlayer(String player) {
        players.add(player);
    }

    /**
     * Adds a kill to the specified player's count.
     *
     * @param player the player's name.
     */
    public void addKillToPlayer(String player) {
        kills.merge(player, 1, Integer::sum);
    }

    /**
     * Removes a kill from the specified player's count.
     *
     * @param player the player's name.
     */
    public void removeKillFromPlayer(String player) {
        kills.merge(player, -1, Integer::sum);
    }

    /**
     * Adds a death cause to the game's statistics.
     *
     * @param cause the cause of death.
     */
    public void addDeathCause(String cause) {
        deathsByMeans.merge(cause, 1, Integer::sum);
    }
}
