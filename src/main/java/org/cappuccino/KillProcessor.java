package org.cappuccino;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * The KillProcessor class is processing kill events from the log file.
 * This class is responsible for parsing kill lines and recording the relevant information.
 */
@Slf4j
public class KillProcessor {
    private static final Pattern KILL_REGEX = Pattern.compile("Kill: \\d+ \\d+ \\d+: (.+) killed (.+) by (.+)");

    /**
     * Processes a kill event line from the log file and updating the game statistics
     *
     * @param killLine The log line containing kill event information.
     * @param game     The Game object to update with the processed information.
     */
    public void processKill(String killLine, Game game) {
        Matcher matcher = KILL_REGEX.matcher(killLine);
        if (matcher.find()) {
            String killer = matcher.group(1);
            String victim = matcher.group(2);
            String deaths = matcher.group(3);

            game.addKill();
            game.addPlayer(victim);
            game.addDeathCause(deaths);

            if (!"<world>".equals(killer)) {
                game.addPlayer(killer);
                game.addKillToPlayer(killer);
                log.debug("Kill: {} killed {} by {}", killer, victim, deaths);
            } else {
                game.removeKillFromPlayer(victim);
                log.debug("World kill: {} died by {}", victim, deaths);
            }
        } else {
            log.warn("Failed to process kill line: {}", killLine);
        }
    }
}
