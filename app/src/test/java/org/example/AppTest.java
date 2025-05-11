package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testMainMenu_HumanVsHuman() {
        // Simulate: Choose option 1, make moves 1-9 sequentially, then quit
        String input = "1\n1\n2\n3\n4\n5\n6\n7\n8\n9\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue(output.contains("Human vs. Human"));
        assertTrue(output.contains("Goodbye!"));
    }

    @Test
    void testMainMenu_ComputerFirst() {
        // Simulate: Choose option 3, accept computer moves, make human moves 1,2,3, then quit
        String input = "3\n1\n2\n3\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue(output.contains("Computer vs. Human"));
        assertTrue(output.contains("Computer first"));
    }

    @Test
    void testInvalidMenuSelection() {
        // Simulate: Invalid option 5, then valid option 1, then quit
        String input = "5\n1\n1\n2\n3\n4\n5\n6\n7\n8\n9\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue(output.contains("Invalid input"));
        assertTrue(output.contains("Human vs. Human"));
    }

    @Test
    void testPlayAgainFlow() {
        // Simulate: Play HvH, then play HvC, then quit
        String input = "1\n1\n2\n3\n4\n5\n6\n7\n8\n9\nyes\n2\n1\n2\n3\n4\n5\n6\n7\n8\n9\nno\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        App.main(new String[]{});
        
        String output = outContent.toString();
        assertTrue(output.contains("Human vs. Human"));
        assertTrue(output.contains("Human vs. Computer"));
    }
}