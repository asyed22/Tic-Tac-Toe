package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class HumanPlayerTest {
    private final InputStream systemIn = System.in;

    @Test
    void testValidMove() {
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        HumanPlayer player = new HumanPlayer('X');
        Board board = new Board();
        int move = player.makeMove(board);
        
        assertEquals(5, move);
        System.setIn(systemIn);
    }

    @Test
    void testInvalidThenValidMove() {
        String input = "10\n5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        HumanPlayer player = new HumanPlayer('X');
        Board board = new Board();
        int move = player.makeMove(board);
        
        assertEquals(5, move);
        System.setIn(systemIn);
    }
}