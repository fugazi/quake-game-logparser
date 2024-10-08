package org.cappuccino;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Main class for parsing Quake log files and generating game reports.
 * This class orchestrates the parsing of log files, creation of game objects,
 * and handles the main parsing workflow.
 */
@Slf4j
public class QuakeLogParser {
    private static final String LOG_FILE_NAME = "qgames.log.txt";

    private final GameParser gameParser;
    private final ReportGenerator reportGenerator;

    /**
     * Constructs a QuakeLogParser instance, initializing the GameParser and ReportGenerator.
     */
    public QuakeLogParser() {
        this.gameParser = new GameParser();
        this.reportGenerator = new ReportGenerator();
    }

    /**
     * Parses the Quake log file, processes the games, and generates reports.
     *
     * @throws IOException if the log file cannot be read.
     */
    public void parseLogFile() throws IOException {
        log.info("Starting to parse log file: {}", LOG_FILE_NAME);

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(LOG_FILE_NAME);
        if (inputStream == null) {
            throw new IOException("File not found: " + LOG_FILE_NAME);
        }

        List<Game> games = gameParser.parseGames(inputStream);
        log.info("Finished parsing log file. Total games parsed: {}", games.size());

        reportGenerator.generateGameReports(games);
        reportGenerator.generatePlayerRanking(games);
        reportGenerator.generateDeathCauseReport(games);
    }

    /**
     * The main method to run the QuakeLogParser.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        QuakeLogParser parser = new QuakeLogParser();
        try {
            parser.parseLogFile();
        } catch (IOException e) {
            log.error("Error reading log file: {}", e.getMessage(), e);
            System.err.println("Error reading log file: " + e.getMessage());
            System.exit(1);
        }
    }
}