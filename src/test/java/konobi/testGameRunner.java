package konobi;

import konobi.InputOutput.InputHandler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testGameRunner {
    @ParameterizedTest
    @CsvSource({"match.txt", "match2.txt", "match3.txt", "match4_crosscut.txt"})
    public void simulateGameAndCheckWinner(String fileName) throws FileNotFoundException, URISyntaxException {
        URL filePath = testGameRunner.class.getClassLoader().getResource(fileName);
        FileInputStream fileInputStream = new FileInputStream(Path.of(filePath.toURI()).toString());
        Scanner scanner = new Scanner(fileInputStream);
        InputHandler.setStdIn(scanner);
        ByteArrayOutputStream fakeStandardOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(fakeStandardOutput));

        GameRunner.main(null);

        String outputString = fakeStandardOutput.toString();
        String expectedWinnerName="";
        while (scanner.hasNextLine()) {
            expectedWinnerName= scanner.nextLine();
        }
        List<String> outputLines = outputString.lines().collect(Collectors.toList());
        String winnerOutputString = outputLines.get(outputLines.size()-1);
        assertEquals("Congratulations " + expectedWinnerName + ", you won!", winnerOutputString);
    }


}
