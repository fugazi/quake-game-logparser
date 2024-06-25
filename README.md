# Quake Game Log Parser

## Project Overview

The Quake Log Parser is a Java application designed to parse and analyze log files from Quake 3 Arena game servers.
It extracts valuable information about game sessions, player statistics, and death causes, providing comprehensive reports and rankings.

## Table of Contents

- [Features](#features)
- [Features](#features)
- [Project Structure](#project-structure)
- [Class Descriptions](#class-descriptions)
- [Prerequisites](#prerequisites)
- [Dependencies](#dependencies)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [Test Reports](#test-reports)
- [Contact](#contact)

## Features

- Parse Quake 3 Arena log files
- Generate detailed reports for each game session
- Create player rankings based on kill counts
- Analyze and report on death causes across all games
- Utilizes efficient data structures and processing techniques
- Contains unit tests to verify the correct parsing of Quake log files and exercise the game statistics

## Project Structure

```
cloudwalk-quake-game/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── cappuccino/
│   │   │           ├── Game.java
│   │   │           ├── GameParser.java
│   │   │           ├── KillProcessor.java
│   │   │           ├── QuakeLogParser.java
│   │   │           └── ReportGenerator.java
│   │   └── resources/
│   │       └── logback.xml
│   │       └── qgames.log.txt
│   ├── test/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── cappuccino/
│   │   │           └── QuakeLogParserTest.java
│   │   └── resources/
│   │       └── test_parsed_games.json
├── pom.xml
└── README.md
```
## Class Descriptions

- `QuakeLogParser`: The main class that orchestrates the parsing process and report generation.
- `GameParser`: Responsible for parsing the log file and creating `Game` objects.
- `Game`: Represents a single game session with its statistics.
- `ReportGenerator`: Generates various reports based on the parsed game data.
- `KillProcessor`: Processes individual kill events within a game.

## Prerequisites

- Java Development Kit (JDK) 22 or later
- Apache Maven 3.6.0 or later

## Dependencies

The project uses the following main dependencies:

- JUnit Jupiter 5.11.0-M2 (for testing)
- Lombok 1.18.32 (for reducing boilerplate code)
- SLF4J 2.1.0-alpha1 and Logback 1.5.6 (for logging)
- Jackson 2.17.1 (for JSON processing in tests)
- AssertJ 3.26.0 (for fluent assertions in tests)

For a complete list of dependencies, please refer to the `pom.xml` file.

## Building the Project

To build the project, run the following command in the project root directory:

```
mvn clean install
```

This command will compile the source code, run the tests, and package the application.

## Running the Application

To run the Quake Log Parser, use the following command:

```
java -cp target/cloudwalk-quake-game-1.0-SNAPSHOT.jar org.cappuccino.QuakeLogParser
```
also
```
Open the IDE editor and run the test class `QuakeLogParser` 
```

Make sure the `qgames.log.txt` file is present in the `resources` directory before running the application.

## Running Tests

To run the tests, execute the following command:

```
mvn test
```

Make sure the `test_parsed_games.json` file is present in the `resources` directory before running the tests.

## Test Reports
After running the tests, a report will be generated in the directory:
```
target/surefire-reports
```

## Contact

For any questions or concerns, please drop an email to `itmteleco@gmail.com`.
```
Developed by Douglas Urrea Ocampo
https://www.douglasfugazi.co
```
