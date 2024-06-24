package org.cappuccino;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;

@Getter
public class Game {
    private int totalKills;
    private final Set<String> players;
    private final Map<String, Integer> kills;

    public Game() {
        this.totalKills = 0;
        this.players = new HashSet<>();
        this.kills = new HashMap<>();
    }

    public void addKill() {
        totalKills++;
    }

    public void addPlayer(String player) {
        players.add(player);
    }

    public void addKillToPlayer(String player) {
        kills.merge(player, 1, Integer::sum);
    }

    public void removeKillFromPlayer(String player) {
        kills.merge(player, -1, Integer::sum);
    }

}
