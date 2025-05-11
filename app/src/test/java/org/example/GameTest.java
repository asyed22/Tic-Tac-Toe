package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testHumanVsHumanGameSetup() {
        String input = "1\n2\n3\n4\n5\n6\n7\n8\n9\n"; // Full game inputs
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Game game = new Game(new Scanner(System.in));
        game.start(1);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Human vs Human game started!"));
    }


    @Test
    void testGameWinCondition() {
        // X wins with first row: 1, 2, 3
        String input = "1\n4\n2\n5\n3\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Game game = new Game(new Scanner(System.in));
        game.start(1);
        
        String output = outputStream.toString();
        assertTrue(output.contains("Player 1 (X) wins!"));
    }

    @Test
    void testGameDrawCondition() {
        // Forces a draw
        String input = "1\n2\n3\n5\n4\n6\n8\n7\n9\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        Game game = new Game(new Scanner(System.in));
        game.start(1);
        
        String output = outputStream.toString();
        assertTrue(output.contains("It's a draw!"));
    }
}