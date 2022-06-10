package com.adaptionsoft.games.trivia.runner;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class GameRunnerTest {

  @Test
  void main() {

    String outputFile = "output.txt";
    try(FileOutputStream outputStream = new FileOutputStream(outputFile)) {
      System.setOut(new PrintStream(outputStream));
      GameRunner.main(new String[]{});
      compareFile(outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void compareFile(String outputFile) {
    try (BufferedReader bf1 = Files.newBufferedReader(Path.of(outputFile));
        BufferedReader bf2 = Files.newBufferedReader(
            Path.of("src/test/java/com/adaptionsoft/games/trivia/runner/GoldenMaster.txt")))
    {
      String outputLine = "", expectedLine = "";
      while ((outputLine = bf1.readLine()) != null) {
        expectedLine = bf2.readLine();
        assertEquals( expectedLine, outputLine);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}