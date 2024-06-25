import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test class for the QuakeLogParser.
 * This class contains unit tests to verify the correct parsing of Quake log files
 * and the generation of game statistics.
 */
class QuakeLogParserTest {
    private static final String PARSED_GAMES_FILE = "test_parsed_games.json";
    private ObjectMapper objectMapper;

    /**
     * Set up method to initialize the ObjectMapper before each test.
     */
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * Test method to verify the correct parsing of games from a pre-parsed JSON file.
     * This test checks various aspects of the parsed games, including total kills,
     * players, and individual player kills for each game.
     *
     * @throws IOException if there's an error reading the JSON file
     */
    @Test
    void testParsedGames() throws IOException {
        // Load the pre-parsed games from JSON
        InputStream parsedGamesStream = getClass().getClassLoader().getResourceAsStream(PARSED_GAMES_FILE);
        assertNotNull(parsedGamesStream, "Parsed games file not found");
        List<Map<String, Object>> parsedGames = objectMapper.readValue(parsedGamesStream, new TypeReference<>() {
        });

        // Verify the parsed games
        assertEquals(5, parsedGames.size(), "Expected 3 games");

        // Verify Game 1
        Map<String, Object> game1 = parsedGames.getFirst();
        assertEquals(0, game1.get("total_kills"), "Game 1: Zero total kills");
        assertTrue(((List<?>) game1.get("players")).isEmpty(), "Game 1: Players should be empty");
        assertTrue(((Map<?, ?>) game1.get("kills")).isEmpty(), "Game 1: Kills should be empty");

        // Verify Game 2
        Map<String, Object> game2 = parsedGames.get(1);
        assertEquals(11, game2.get("total_kills"), "Game 2: Incorrect total kills");
        assertEquals(2, ((List<?>) game2.get("players")).size(), "Game 2: Incorrect number of players");
        assertTrue(((List<?>) game2.get("players")).containsAll(List.of("Mocinha", "Isgalamido")),
                "Game 2: Incorrect players");

        Map<String, Integer> game2Kills = (Map<String, Integer>) game2.get("kills");
        assertEquals(-5, game2Kills.get("Isgalamido"), "Game 2: Incorrect kills for Isgalamido");
        assertEquals(0, game2Kills.get("Mocinha"), "Game 2: Incorrect kills for Mocinha");

        // Verify Game 3
        Map<String, Object> game3 = parsedGames.get(2);
        assertEquals(4, game3.get("total_kills"), "Game 3: Incorrect total kills");
        assertEquals(4, ((List<?>) game3.get("players")).size(), "Game 3: Incorrect number of players");
        assertTrue(
                ((List<?>) game3.get("players")).containsAll(List.of("Dono da Bola", "Mocinha", "Isgalamido", "Zeh")),
                "Game 3: Incorrect players");

        Map<String, Integer> game3Kills = (Map<String, Integer>) game3.get("kills");
        assertEquals(-1, game3Kills.get("Dono da Bola"), "Game 3: Incorrect kills for Dono da Bola");
        assertEquals(0, game3Kills.get("Mocinha"), "Game 3: Incorrect kills for Mocinha");
        assertEquals(1, game3Kills.get("Isgalamido"), "Game 3: Incorrect kills for Isgalamido");
        assertEquals(-2, game3Kills.get("Zeh"), "Game 3: Incorrect kills for Zeh");

        // Verify Game 4
        Map<String, Object> game4 = parsedGames.get(3);
        assertEquals(105, game4.get("total_kills"), "Game 4: Incorrect total kills");
        assertEquals(4, ((List<?>) game4.get("players")).size(), "Game 4: Incorrect number of players");
        assertTrue(((List<?>) game4.get("players")).containsAll(
                List.of("Dono da Bola", "Assasinu Credi", "Isgalamido", "Zeh")), "Game 4: Incorrect players");

        Map<String, Integer> game4Kills = (Map<String, Integer>) game4.get("kills");
        assertEquals(13, game4Kills.get("Dono da Bola"), "Game 4: Incorrect kills for Dono da Bola");
        assertEquals(13, game4Kills.get("Assasinu Credi"), "Game 4: Incorrect kills for Assasinu Credi");
        assertEquals(19, game4Kills.get("Isgalamido"), "Game 4: Incorrect kills for Isgalamido");
        assertEquals(20, game4Kills.get("Zeh"), "Game 4: Incorrect kills for Zeh");

        // Verify Game 5
        Map<String, Object> game5 = parsedGames.getLast();
        assertEquals(14, game5.get("total_kills"), "Game 5: Incorrect total kills");
        assertEquals(4, ((List<?>) game5.get("players")).size(), "Game 5: Incorrect number of players");
        assertTrue(((List<?>) game5.get("players")).containsAll(
                List.of("Dono da Bola", "Assasinu Credi", "Isgalamido", "Zeh")), "Game 5: Incorrect players");

        Map<String, Integer> game5Kills = (Map<String, Integer>) game5.get("kills");
        assertEquals(0, game5Kills.get("Dono da Bola"), "Game 5: Incorrect kills for Dono da Bola");
        assertEquals(1, game5Kills.get("Assasinu Credi"), "Game 5: Incorrect kills for Assasinu Credi");
        assertEquals(2, game5Kills.get("Isgalamido"), "Game 5: Incorrect kills for Isgalamido");
        assertEquals(1, game5Kills.get("Zeh"), "Game 5: Incorrect kills for Zeh");
    }
}
