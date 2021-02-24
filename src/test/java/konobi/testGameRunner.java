package konobi;


import konobi.ConsoleVersion.GameRunner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testGameRunner {

    @ParameterizedTest
    @CsvSource({"match.txt", "match2.txt", "match3.txt", "match4_crosscut.txt", "match_pass.txt"})
    public void simulateGameAndCheckWinner(String fileName) throws IOException, URISyntaxException {
        URL filePath = testGameRunner.class.getClassLoader().getResource(fileName);
        assert filePath != null;
        List<String> fileLines = Files.readAllLines(Paths.get(filePath.toURI()));
        String expectedWinnerName = fileLines.get(fileLines.size()-1);
        FileInputStream fileInputStream = new FileInputStream(Path.of(filePath.toURI()).toString());
        ByteArrayOutputStream fakeStandardOutput = new ByteArrayOutputStream();
        System.setIn(fileInputStream);
        System.setOut(new PrintStream(fakeStandardOutput));

        GameRunner.main(null);

        String outputString = fakeStandardOutput.toString();
        List<String> outputLines = outputString.lines().collect(Collectors.toList());
        String winnerOutputString = outputLines.get(outputLines.size()-1);
        assertEquals("Congratulations " + expectedWinnerName + ", you won!", winnerOutputString);
    }

}
